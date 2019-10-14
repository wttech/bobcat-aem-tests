package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents;

import org.openqa.selenium.WebElement;

import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

@PageObject(css = ".cmp-contentfragment")
public class ContentFragmentComponent {

  @Inject
  @CurrentScope
  private WebElement component;

  public String getDataPath() {
    return component.getAttribute("data-path");
  }

  public String getText() {
    return component.getText();
  }

}
