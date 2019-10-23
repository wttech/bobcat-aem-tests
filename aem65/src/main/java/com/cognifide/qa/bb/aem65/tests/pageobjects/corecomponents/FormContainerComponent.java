package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.constants.HtmlTags.Attributes;
import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

@PageObject(css = "form.cmp-form")
public class FormContainerComponent {

  @CurrentScope
  @Inject
  private WebElement component;

  @FindBy(css = "input[name=':redirect']")
  private WebElement thankYouPagePathPropertyElement;

  public boolean isDisplayed() {
    return component.isDisplayed();
  }

  public String getThankYouPagePath() {
    return thankYouPagePathPropertyElement.getAttribute(Attributes.VALUE);
  }

}
