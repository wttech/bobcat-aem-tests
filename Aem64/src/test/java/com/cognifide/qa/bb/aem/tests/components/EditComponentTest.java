package com.cognifide.qa.bb.aem.tests.components;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cognifide.qa.bb.RunWithJunit5;
import com.cognifide.qa.bb.aem.core.component.action.ComponentController;
import com.cognifide.qa.bb.aem.core.component.actions.ConfigureComponentAction;
import com.cognifide.qa.bb.aem.core.component.actions.ConfigureComponentActonData;
import com.cognifide.qa.bb.aem.core.component.actions.EditComponentAction;
import com.cognifide.qa.bb.aem.core.component.actions.EditComponentActonData;
import com.cognifide.qa.bb.aem.core.component.configuration.ResourceFileLocation;
import com.cognifide.qa.bb.aem.core.pages.AemPageManipulationException;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingTestDataXMLBuilder;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingTestPageData;
import com.cognifide.qa.bb.aem.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.aem.tests.GuiceModule;
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
@Feature("Component Tests")
public class EditComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH = "/content/we-retail/us/en/editcomponenttestpage";

  private final static String PAGE_TO_CREATE_TITLE = "testPage";

  @Inject
  private ComponentController componentController;

  @BeforeEach
  public void createTestPage() throws AemPageManipulationException {
    aemTestPageControler.createTestPage(
        new SlingTestPageData(TEST_PAGE_PATH,
            SlingTestDataXMLBuilder.buildSlingTestData("testpages/editComponentTestPage.xml")));
  }

  @Test
  public void configureComponentTest() {
    TestPage testPage = bobcatPageFactory
        .create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isDisplayed());
    componentController.getSidePanelAction(ConfigureComponentAction.CONFIGURE_COMPONENT_ACTION)
        .action(new ConfigureComponentActonData("container", "Text", 0, new ResourceFileLocation("component-configs/text.yaml")));
    System.out.println("gg");
  }

  @AfterEach
  public void deleteTestPage() throws AemPageManipulationException {
    aemTestPageControler.deleteTestPage(new SlingTestPageData(TEST_PAGE_PATH, null));
  }
}

