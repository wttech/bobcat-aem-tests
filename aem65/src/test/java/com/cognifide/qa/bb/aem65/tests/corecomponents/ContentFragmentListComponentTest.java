package com.cognifide.qa.bb.aem65.tests.corecomponents;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.aem.core.component.actions.ConfigureComponentData;
import com.cognifide.qa.bb.aem.core.component.configuration.ResourceFileLocation;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingDataXMLBuilder;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingPageData;
import com.cognifide.qa.bb.aem65.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.ContentFragmentListComponent;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;
import com.google.inject.Inject;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("Content Fragment List Component configuration")
@DisplayName("Author can configure for Content Fragment List the...")
public class ContentFragmentListComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH =
      "/content/core-components-examples/library/content-fragment-list-component-test-page";

  private TestPage page;
  private ContentFragmentListComponent component;

  @Inject
  private SoftAssertions softly;

  @BeforeEach
  public void setup() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH,
        SlingDataXMLBuilder
            .buildFromFile("testpages/core-components/contentFragmentListComponentTestPage.xml")));
    page = bobcatPageFactory.create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    page.open();
  }

  @Test
  @DisplayName("model")
  public void configureModel() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Content Fragment List (v1)", 2,
            new ResourceFileLocation(
                "component-configs/core-components/content-fragment-list/model.yaml")));
    component = page.getContent(ContentFragmentListComponent.class, 2);
    assertThat(component.getArticleTexts().get(0)).as("Check if the model is configured properly")
        .matches("^48 hours of Wilderness(.*|\\n)*move on to the next\\.$");
  }

  @Test
  @DisplayName("parent path")
  public void configureParentPath() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Content Fragment List (v1)", 1,
            new ResourceFileLocation(
                "component-configs/core-components/content-fragment-list/parent-path.yaml")));
    component = page.getContent(ContentFragmentListComponent.class, 1);
    softly.assertThat(component.getArticleTexts().get(0))
        .as("Check if the first article from the parent path is displayed")
        .matches("^Accepted Currency(.*|\\n)*processing$");
    softly.assertThat(component.getArticleTexts().get(3))
        .as("Check if the last article from the parent path is displayed")
        .matches("^Cancellations(.*|\\n)*processing$");
    softly.assertAll();
  }

  @Test
  @DisplayName("max items")
  public void configureMaxItems() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Content Fragment List (v1)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/content-fragment-list/max-items.yaml")));
    component = page.getContent(ContentFragmentListComponent.class, 0);
    assertThat(component.getCollectiveText())
        .as("Check if the max items limit is configured properly")
        .matches("^Adobe Research Schweiz AG(.*|\\n)*europe-middleeast-africa$");
  }

  @Test
  @DisplayName("elements to display")
  public void configureElementsToDisplay() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Content Fragment List (v1)", 1,
            new ResourceFileLocation(
                "component-configs/core-components/content-fragment-list/elements.yaml")));
    component = page.getContent(ContentFragmentListComponent.class, 1);
    softly.assertThat(component.getArticleTexts().get(0))
        .as("Check if the element selection is configured properly for the first article")
        .matches("^The Company Name(.*|\\n)*\"We.Retail\"\\?");
    softly.assertThat(component.getArticleTexts().get(2))
        .as("Check if the element selection is configured properly for the last article")
        .matches("^Real Company(.*|\\n)*company\\?");
    softly.assertAll();
  }

  @AfterEach
  public void cleanup() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
  }

}
