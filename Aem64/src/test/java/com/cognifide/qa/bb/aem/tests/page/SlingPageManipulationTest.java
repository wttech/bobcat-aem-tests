package com.cognifide.qa.bb.aem.tests.page;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cognifide.qa.bb.aem.core.api.ActionException;
import com.cognifide.qa.bb.aem.core.api.Actions;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingTestDataXMLBuilder;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingTestPageData;
import com.cognifide.qa.bb.aem.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.aem.tests.GuiceModule;
import com.cognifide.qa.bb.aem.tests.pages.TestPage;
import com.cognifide.qa.bb.junit5.guice.Modules;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Modules(GuiceModule.class)
@Epic("AEM 6.4 Base Tests")
@Feature("Page Manipulation")
public class SlingPageManipulationTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH = "/content/we-retail/us/en/test";

  @Test
  @Story("Create and delete test page")
  @Description("Create and delete test page using sling controller")
  public void pageManipulationTest() throws ActionException {

    bobcatController.execute(Actions.Page.CREATE,
        new SlingTestPageData(TEST_PAGE_PATH,
            SlingTestDataXMLBuilder.buildSlingTestData("testpages/pageTest.xml")));
    TestPage testPage = bobcatPageFactory.create(TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.open();
    assertTrue(testPage.isDisplayed());
    bobcatController.execute(Actions.Page.DELETE, new SlingTestPageData(TEST_PAGE_PATH, null));
    testPage.open();
    assertTrue(testPage.isNotAvailable());
  }

}
