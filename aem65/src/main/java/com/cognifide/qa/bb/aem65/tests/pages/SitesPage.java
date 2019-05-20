package com.cognifide.qa.bb.aem65.tests.pages;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.aem.core.pages.AemAuthorPage;
import com.cognifide.qa.bb.aem.core.siteadmin.actions.CreatePageActionData;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.api.actions.ActionsController;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

@PageObject
public class SitesPage extends AemAuthorPage<SitesPage> {

  @Inject
  private ActionsController controller;

  public void createPage(String template, String title, String name) throws ActionException {
    controller
        .execute(AemActions.CREATE_PAGE_VIA_SITEADMIN, new CreatePageActionData(template, title, name));
  }

}
