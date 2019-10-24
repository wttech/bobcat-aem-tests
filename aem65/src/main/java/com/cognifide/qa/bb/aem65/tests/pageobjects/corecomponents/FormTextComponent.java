package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

@PageObject(css = "input.cmp-form-text__text")
public class FormTextComponent {

  @CurrentScope
  @Inject
  private WebElement component;

  @FindBy(xpath = "./..")
  private WebElement wrapper;

  public String getConstraint() {
    return component.getAttribute("type");
  }

  public String getAriaLabel() {
    return component.getAttribute("aria-label");
  }

  public String getElementName() {
    return component.getAttribute("name");
  }

  public String getValue() {
    return component.getAttribute("value");
  }

  public String getPlaceholder() {
    return component.getAttribute("placeholder");
  }

  public boolean isReadOnly() {
    return Boolean.parseBoolean(component.getAttribute("readonly"));
  }

  public String getConstraintMessage() {
    return wrapper.getAttribute("data-cmp-constraint-message");
  }

  public String getRequiredMessage() {
    return wrapper.getAttribute("data-cmp-required-message");
  }
}
