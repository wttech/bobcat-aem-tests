package com.cognifide.qa.bb.aem.tests.pages;

import com.cognifide.qa.bb.aem.core.api.ActionException;
import com.cognifide.qa.bb.aem.core.api.Actions;
import com.cognifide.qa.bb.aem.core.api.Controller;
import com.cognifide.qa.bb.aem.core.pages.AemAuthorPage;
import com.cognifide.qa.bb.aem.core.siteadmin.actions.CreatePageActionData;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

@PageObject
public class SitesPage extends AemAuthorPage<SitesPage> {

  @Inject
  private Controller controller;

  public void createPage(String template, String title, String name) throws ActionException {
    controller
        .execute(Actions.Siteadmin.CREATE_PAGE, new CreatePageActionData(template, title, name));
  }

}
