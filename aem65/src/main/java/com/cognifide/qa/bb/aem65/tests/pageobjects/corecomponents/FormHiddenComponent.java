package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents;

import org.openqa.selenium.WebElement;

import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

@PageObject(css = ".hidden > input")
public class FormHiddenComponent {

  @CurrentScope
  @Inject
  private WebElement component;

  public String getId() {
    return component.getAttribute("id");
  }

  public String getName() {
    return component.getAttribute("name");
  }

  public String getValue() {
    return component.getAttribute("value");
  }

}
