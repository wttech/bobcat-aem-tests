package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.formcomponents;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.qualifier.PageObject;

@PageObject(css = "label.cmp-form-options__field-label")
public class FormOptionsComponentField {

  @FindBy(css = "input")
  private WebElement input;

  @FindBy(css = "span")
  private WebElement label;

  public String getType() {
    return input.getAttribute("type");
  }

  public String getName() {
    return input.getAttribute("name");
  }

  public String getValue() {
    return input.getAttribute("value");
  }

  public String getText() {
    return label.getText();
  }

  public boolean isSelected() {
    return input.isSelected();
  }

  public boolean isEnabled() {
    return input.isEnabled();
  }
}
