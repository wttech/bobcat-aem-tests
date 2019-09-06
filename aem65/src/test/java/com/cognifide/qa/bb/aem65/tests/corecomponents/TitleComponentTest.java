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
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.title.TitleComponent;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.title.TitleComponentImpl;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("Title Component configuration")
@DisplayName("Author can configure for Title Component the...")
public class TitleComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH = "/content/we-retail/us/en/titlecomponenttestpage";

  private TestPage page;
  private TitleComponentImpl component;

  @BeforeEach
  public void createAndOpenTestPage() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH,
        SlingDataXMLBuilder.buildFromFile("testpages/core-components/titleComponentTestPage.xml")));
    page = bobcatPageFactory.create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    page.open();
  }

  @Test
  @DisplayName("text")
  public void configureText() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Title", 0,
            new ResourceFileLocation("component-configs/core-components/title/text.yaml")));
    component = (TitleComponentImpl) page.getContent(TitleComponent.class, 0);
    assertThat(component.getInnerHtml().replaceAll("[\r\n]", ""))
        .as("Check if the text is configured")
        .matches(".*Checking if the text field can be configured.*");
  }

  @Test
  @DisplayName("heading type")
  public void configureHeadingType() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Title", 0,
            new ResourceFileLocation("component-configs/core-components/title/heading-type.yaml")));
    component = (TitleComponentImpl) page.getContent(TitleComponent.class, 0);
    assertThat(component.getTextHtmlTag()).as("Check if the heading type is configured")
        .matches("h3");
  }

  @Test
  @DisplayName("link")
  public void configureLink() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Title", 0,
            new ResourceFileLocation("component-configs/core-components/title/link.yaml")));
    component = (TitleComponentImpl) page.getContent(TitleComponent.class, 0);
    assertThat(component.getLinkHref()).as("Check if the link is configured")
        .matches("https://cognifide.github.io/bobcat/");
  }

  @AfterEach
  public void deleteTestPage() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
  }

}
