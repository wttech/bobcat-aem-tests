package com.cognifide.qa.bb.aem.tests.components;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.aem.core.component.actions.ConfigureComponentData;
import com.cognifide.qa.bb.aem.core.component.configuration.ResourceFileLocation;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingDataXMLBuilder;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingPageData;
import com.cognifide.qa.bb.aem.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.aem.tests.GuiceModule;
import com.cognifide.qa.bb.aem.tests.pageobjects.TextComponent;
import com.cognifide.qa.bb.aem.tests.pageobjects.TextComponentImpl;
import com.cognifide.qa.bb.aem.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Modules(GuiceModule.class)
@Epic("AEM 6.4 Base Tests")
@Feature("TextComponent Tests")
public class ConfigureComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH = "/content/we-retail/us/en/editcomponenttestpage";

  private final static String PAGE_TO_CREATE_TITLE = "testPage";
  private static final String EXPECTED_LIST_PATTERN = "<ul><li>test test test.*</li></ul>";
  public static final String EXPECTED_TEXT_PATTERN = ".*<b>test test test.*</b>.*";

  @BeforeEach
  public void createTestPage() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING,
        new SlingPageData(TEST_PAGE_PATH,
            SlingDataXMLBuilder.buildFromFile("testpages/editComponentTestPage.xml")));
  }

  @Test
  public void configureTextComponentTest() throws ActionException {
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
  public void configureTextListComponentTest() throws ActionException {
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
  public void configureHeroImageComponentTest() throws ActionException {
    TestPage testPage = bobcatPageFactory
        .create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isDisplayed());
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Hero Image", 0,
            new ResourceFileLocation("component-configs/hero.yaml")));
  }

  @Test
  public void configureListComponentTest() throws ActionException {
    TestPage testPage = bobcatPageFactory
        .create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isDisplayed());
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container/container", "List", 0,
            new ResourceFileLocation("component-configs/list.yaml")));
  }

  @Test
  public void configureFormOptionsComponentTest() throws ActionException {
    TestPage testPage = bobcatPageFactory
        .create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isDisplayed());
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container/container", "Form Options", 0,
            new ResourceFileLocation("component-configs/formoptions.yaml")));
  }

  @Test
  public void configureContentFragmentComponentTest() throws ActionException {
    TestPage testPage = bobcatPageFactory
        .create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isDisplayed());
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container/container", "Content Fragment", 0,
            new ResourceFileLocation("component-configs/contentfragment.yaml")));
  }

  @AfterEach
  public void deleteTestPage() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
  }
}

