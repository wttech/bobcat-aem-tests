package com.cognifide.qa.bb.aem65.tests.corecomponents;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.ContentFragmentComponent;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("Content Fragment Component configuration")
@DisplayName("Author can configure for Content Fragment Component the...")
public class ContentFragmentComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH = "/content/we-retail/us/en/content-fragment-component-test-page";
  private static final String TEST_ASSET_PATH = "/content/dam/we-retail/en/experiences/fly-fishing-the-amazon/fly-fishing-the-amazon";

  private TestPage page;
  private ContentFragmentComponent component;

  @BeforeEach
  public void setup() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH,
        SlingDataXMLBuilder
            .buildFromFile("testpages/core-components/contentFragmentComponentTestPage.xml")));
    page = bobcatPageFactory.create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    page.open();
  }

  @Test
  @DisplayName("asset")
  public void configureAsset() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Content Fragment", 0,
            new ResourceFileLocation(
                "component-configs/core-components/contentfragment/asset.yaml")));
    component = page.getContent(ContentFragmentComponent.class, 0);
    assertThat(component.getDataPath()).as("Check if the asset data path is configured properly")
        .matches(TEST_ASSET_PATH);
  }

  @Test
  @DisplayName("display mode for a single content fragment element")
  public void configureDisplayModeForSingleElement() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Content Fragment", 1,
            new ResourceFileLocation(
                "component-configs/core-components/contentfragment/display-mode-single.yaml")));
    component = page.getContent(ContentFragmentComponent.class, 1);
    assertThat(component.getText())
        .as("Check if the display mode is configured to display a single element")
        .matches("^No, We(\\n|.)*Manager.$");
  }

  @Test
  @DisplayName("display mode for multiple content fragment elements")
  public void configureDisplayModeForMultipleElements() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Content Fragment", 1,
            new ResourceFileLocation(
                "component-configs/core-components/contentfragment/display-mode-multi.yaml")));
    component = page.getContent(ContentFragmentComponent.class, 1);
    assertThat(component.getText())
        .as("Check if the display mode is configured to display multiple elements")
        .matches("^Question(\\n|.)*Answer(\\n|.)*Tags(\\n|.)*company$");
  }

  @Test
  @DisplayName("variation")
  public void configureVariation() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Content Fragment", 0,
            new ResourceFileLocation(
                "component-configs/core-components/contentfragment/variation.yaml")));
    component = page.getContent(ContentFragmentComponent.class, 0);
    assertThat(component.getText())
        .as("Check if the variation is configured properly")
        .matches("^Back into the mountains$");
  }

  @Test
  @DisplayName("paragraphs")
  public void configureParagraphs() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Content Fragment", 0,
            new ResourceFileLocation(
                "component-configs/core-components/contentfragment/paragraphs.yaml")));
    component = page.getContent(ContentFragmentComponent.class, 0);
    assertThat(component.getText())
        .as("Check if the paragraphs are configured properly")
        .matches("^48 Hours(\\n|.)*or short.$");
  }

  @AfterEach
  public void cleanup() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
  }
}
