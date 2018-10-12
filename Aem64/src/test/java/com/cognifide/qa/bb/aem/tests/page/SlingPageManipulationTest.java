package com.cognifide.qa.bb.aem.tests.page;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cognifide.qa.bb.RunWithJunit5;
import com.cognifide.qa.bb.aem.core.pages.AemPageManipulationException;
import com.cognifide.qa.bb.aem.core.pages.AemTestPageControler;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingTestDataXMLBuilder;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingTestPageData;
import com.cognifide.qa.bb.aem.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.aem.tests.GuiceModule;
import com.cognifide.qa.bb.aem.tests.pages.TestPage;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.page.BobcatPageFactory;
import com.google.inject.Inject;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import java.io.IOException;
import org.junit.jupiter.api.Test;

@RunWithJunit5
@Modules(GuiceModule.class)
@Epic("AEM 6.4 Base Tests")
@Feature("Page Manipulation")
public class SlingPageManipulationTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH = "/content/we-retail/us/en/test";

  @Inject
  private AemTestPageControler aemTestPageControler;

  @Inject
  private BobcatPageFactory bobcatPageFactory;

  @Test
  @Story("Create and delete test page")
  @Description("Create and delete test page using sling controller")
  public void pageManipulationTest() throws IOException, AemPageManipulationException {

    aemTestPageControler.createTestPage(
        new SlingTestPageData(TEST_PAGE_PATH,
            SlingTestDataXMLBuilder.buildSlingTestData("testpages/pageTest.xml")));
    TestPage testPage = bobcatPageFactory.create(TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.open();
    assertTrue(testPage.isDisplayed());
    aemTestPageControler
        .deleteTestPage(new SlingTestPageData(TEST_PAGE_PATH, null));
    testPage.open();
    assertTrue(testPage.isNotAvailable());
  }

}
