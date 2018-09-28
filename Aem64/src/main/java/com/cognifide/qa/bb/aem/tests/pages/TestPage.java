package com.cognifide.qa.bb.aem.tests.pages;

import com.cognifide.qa.bb.aem.core.pages.AemAuthorPage;
import com.cognifide.qa.bb.qualifier.PageObject;

@PageObject
public class TestPage extends AemAuthorPage {

  private final static String FULL_URL = "/content/we-retail/us/en.html";

  @Override
  public String getFullUrl() {
    return FULL_URL;
  }

  @Override
  public String getTitle() {
    return "English";
  }
}
