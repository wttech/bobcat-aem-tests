package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.formcomponents;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.qualifier.FindPageObject;
import com.cognifide.qa.bb.qualifier.PageObject;

@PageObject(css = "fieldset.cmp-form-options")
public class FormOptionsComponent {

  @FindBy(css = "legend")
  private WebElement title;

  @FindBy(css = "p.cmp-form-options__help-message")
  private WebElement helpMessage;

  @FindPageObject
  private List<FormOptionsComponentField> fields;

  public String getTitle() {
    return title.getText();
  }

  public String getHelpMessage() {
    return helpMessage.getText();
  }

  public List<String> getFieldTypes() {
    return fields.stream().map(FormOptionsComponentField::getType).collect(Collectors.toList());
  }

  public List<String> getFieldNames() {
    return fields.stream().map(FormOptionsComponentField::getName).collect(Collectors.toList());
  }

  public List<String> getFieldValues() {
    return fields.stream().map(FormOptionsComponentField::getValue).collect(Collectors.toList());
  }

  public List<String> getFieldTexts() {
    return fields.stream().map(FormOptionsComponentField::getText).collect(Collectors.toList());
  }

  public boolean isFieldSelected(int fieldNumber) {
    return fields.get(fieldNumber).isSelected();
  }

  public boolean isFieldEnabled(int fieldNumber) {
    return fields.get(fieldNumber).isEnabled();
  }
}
