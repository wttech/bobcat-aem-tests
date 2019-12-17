package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.formcomponents;

import org.openqa.selenium.WebElement;

import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

@PageObject(css = "button.cmp-form-button")
public class FormButtonComponent {

  @CurrentScope
  @Inject
  private WebElement component;

  public String getType() {
    return component.getAttribute("type");
  }

  public String getTitle() {
    return component.getText();
  }

  public String getName() {
    return component.getAttribute("name");
  }

  public String getValue() {
    return component.getAttribute("value");
  }

}
