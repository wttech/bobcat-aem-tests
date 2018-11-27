package com.cognifide.qa.bb.aem.tests.siteadmin;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingPageData;
import com.cognifide.qa.bb.aem.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.aem.tests.GuiceModule;
import com.cognifide.qa.bb.aem.tests.pages.SitesPage;
import com.cognifide.qa.bb.aem.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.page.BobcatPageFactory;
import com.google.inject.Inject;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Modules(GuiceModule.class)
@Epic("AEM 6.4 Base Tests")
@Feature("Site Admin Tests")
public class SiteAdminControllerTest extends AbstractAemAuthorTest {

  private final static String PAGE_TO_CREATE_NAME = "siteadmintestpage";

  private final static String PAGE_TO_CREATE_TEMPLATE = "Content Page";

  private final static String PAGE_TO_CREATE_TITLE = "Site Admin Test Page";

  private static final String FULL_PAGE_PATH = "/content/we-retail/us/en/" + PAGE_TO_CREATE_NAME;

  private static final String SITES_PAGE_PATH = "/sites.html/content/we-retail/us/en";

  @Inject
  private BobcatPageFactory bobcatPageFactory;

  @Test
  @Story("Create test page from sites.html")
  @Description("Create test page using create action from site admin")
  public void createPageActionTest() throws ActionException {

    TestPage testPage = bobcatPageFactory.create(FULL_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isNotAvailable());

    SitesPage sitesPage = bobcatPageFactory.create(SITES_PAGE_PATH, SitesPage.class);
    sitesPage.open().createPage(PAGE_TO_CREATE_TEMPLATE, PAGE_TO_CREATE_TITLE, PAGE_TO_CREATE_NAME);

    assertTrue(testPage.open().isDisplayed());
  }

  @AfterEach
  public void deleteTestPage() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(FULL_PAGE_PATH));
  }
}
