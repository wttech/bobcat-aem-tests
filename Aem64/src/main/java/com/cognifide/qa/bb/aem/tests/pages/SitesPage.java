package com.cognifide.qa.bb.aem.tests.pages;

import com.cognifide.qa.bb.aem.core.pages.AemAuthorPage;
import com.cognifide.qa.bb.aem.core.siteadmin.CreatePageAction;
import com.cognifide.qa.bb.aem.core.siteadmin.CreatePageActionData;
import com.cognifide.qa.bb.aem.core.siteadmin.SiteAdminController;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

@PageObject
public class SitesPage extends AemAuthorPage<SitesPage> {

  @Inject
  private SiteAdminController siteAdminController;

  public void createPage(String name) {
    siteAdminController.getSiteAdminAction(CreatePageAction.PAGE_CREATE)
        .action(new CreatePageActionData(name));
  }

}
