package com.cognifide.qa.bb.aem65.tests.components;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import com.cognifide.qa.bb.aem65.tests.pageobjects.TextComponent;
import com.cognifide.qa.bb.aem65.tests.pageobjects.TextComponentImpl;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Modules(BobcatRunModule.class)
@Epic("AEM 6.5 Base Tests")
@Feature("Components")
class ConfigureComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH = "/content/we-retail/us/en/editcomponenttestpage";

  private static final String PAGE_TO_CREATE_TITLE = "testPage";
  private static final String EXPECTED_LIST_PATTERN = "<ul><li>test test test.*</li></ul>";
  private static final String EXPECTED_TEXT_PATTERN = ".*<b>test test test.*</b>.*";

  @BeforeEach
  void createTestPage() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING,
        new SlingPageData(TEST_PAGE_PATH,
            SlingDataXMLBuilder.buildFromFile("testpages/editComponentTestPage.xml")));
  }

  @Test
  @DisplayName("Text component is configured correctly")
  void textComponentIsConfiguredCorrectly() throws ActionException {
    TestPage testPage = bobcatPageFactory
        .create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isDisplayed());
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Text", 0,
            new ResourceFileLocation("component-configs/text.yaml")));
    TextComponentImpl content = (TextComponentImpl) testPage.getContent(TextComponent.class, 0);

    assertThat(content.getInnerHTML().trim().replaceAll("\\r|\\n", ""))
        .matches(EXPECTED_TEXT_PATTERN);
  }

  @Test
  @DisplayName("List in the Text component is configured correctly")
  void listInTextComponentIsConfiguredCorrectly() throws ActionException {
    TestPage testPage = bobcatPageFactory
        .create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isDisplayed());
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Text", 0,
            new ResourceFileLocation("component-configs/textlist.yaml")));
    TextComponentImpl content = (TextComponentImpl) testPage.getContent(TextComponent.class, 0);
    assertThat(content.getInnerHTML().trim().replaceAll("\\r|\\n", ""))
        .matches(EXPECTED_LIST_PATTERN);
  }

  @Test
  @DisplayName("Hero Image component is configured correctly")
  void heroImageComponentIsConfiguredCorrectly() throws ActionException {
    TestPage testPage = bobcatPageFactory
        .create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isDisplayed());
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Hero Image", 0,
            new ResourceFileLocation("component-configs/hero.yaml")));
  }

  @Test
  @DisplayName("List component is configured correctly")
  void listComponentIsConfiguredCorrectly() throws ActionException {
    TestPage testPage = bobcatPageFactory
        .create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isDisplayed());
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container/container", "List", 0,
            new ResourceFileLocation("component-configs/list.yaml")));
  }

  @Test
  @DisplayName("Form Options component is configured correctly")
  void formOptionsComponentIsConfiguredCorrectly() throws ActionException {
    TestPage testPage = bobcatPageFactory
        .create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isDisplayed());
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container/container", "Form Options", 0,
            new ResourceFileLocation("component-configs/formoptions.yaml")));
  }

  @Test
  @DisplayName("Content Fragment component is configured correctly")
  void contentFragmentComponentIsConfiguredCorrectly() throws ActionException {
    TestPage testPage = bobcatPageFactory
        .create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isDisplayed());
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container/container", "Content Fragment", 0,
            new ResourceFileLocation("component-configs/contentfragment.yaml")));
  }

  @AfterEach
  void deleteTestPage() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
  }
}

