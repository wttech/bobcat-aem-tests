package com.cognifide.qa.bb.aem.tests.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cognifide.qa.bb.RunWithJunit5;
import com.cognifide.qa.bb.aem.core.component.action.ComponentController;
import com.cognifide.qa.bb.aem.core.component.actions.ConfigureComponentAction;
import com.cognifide.qa.bb.aem.core.component.actions.ConfigureComponentActonData;
import com.cognifide.qa.bb.aem.core.component.configuration.ResourceFileLocation;
import com.cognifide.qa.bb.aem.core.pages.AemPageManipulationException;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingTestDataXMLBuilder;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingTestPageData;
import com.cognifide.qa.bb.aem.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.aem.tests.GuiceModule;
import com.cognifide.qa.bb.aem.tests.pageobjects.TextComponent;
import com.cognifide.qa.bb.aem.tests.pageobjects.TextComponentImpl;
import com.cognifide.qa.bb.aem.tests.pages.TestPage;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.google.inject.Inject;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@RunWithJunit5
@Modules(GuiceModule.class)
@Epic("AEM 6.4 Base Tests")
@Feature("TextComponent Tests")
public class ConfigureComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH = "/content/we-retail/us/en/editcomponenttestpage";

  private final static String PAGE_TO_CREATE_TITLE = "testPage";
  private static final String TEXT_COMPONENT_HTML = "<p>test test test</p>";

  @Inject
  private ComponentController componentController;

  @BeforeEach
  public void createTestPage() throws AemPageManipulationException {
    aemTestPageControler.createTestPage(
        new SlingTestPageData(TEST_PAGE_PATH,
            SlingTestDataXMLBuilder.buildSlingTestData("testpages/editComponentTestPage.xml")));
  }

  @Test
  public void configureTextComponentTest() {
    TestPage testPage = bobcatPageFactory
        .create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isDisplayed());
    componentController.getSidePanelAction(ConfigureComponentAction.CONFIGURE_COMPONENT_ACTION)
        .action(new ConfigureComponentActonData("container", "Text", 0,
            new ResourceFileLocation("component-configs/text.yaml")));
    TextComponentImpl content =(TextComponentImpl) testPage.getContent(TextComponent.class, 0);
    assertEquals(TEXT_COMPONENT_HTML,content.getInnerHTML().trim());
  }

  @Test
  public void configureHeroImageComponentTest() {
    TestPage testPage = bobcatPageFactory
        .create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isDisplayed());
    componentController.getSidePanelAction(ConfigureComponentAction.CONFIGURE_COMPONENT_ACTION)
        .action(new ConfigureComponentActonData("container", "Hero Image", 0,
            new ResourceFileLocation("component-configs/hero.yaml")));
  }

  @AfterEach
  public void deleteTestPage() throws AemPageManipulationException {
    aemTestPageControler.deleteTestPage(new SlingTestPageData(TEST_PAGE_PATH, null));
  }
}

