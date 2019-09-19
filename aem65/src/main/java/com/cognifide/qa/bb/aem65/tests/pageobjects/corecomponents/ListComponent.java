package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.constants.HtmlTags.Attributes;
import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

@PageObject(css = ".cmp-list")
public class ListComponent {

  @Inject
  @CurrentScope
  private WebElement component;

  @FindBy(css = ".cmp-list__item-link")
  private List<WebElement> linkElements;

  public String getText() {
    return component.getText();
  }

  public List<String> getLinks() {
    return linkElements.stream().map(element -> element.getAttribute(Attributes.HREF)).collect(
        Collectors.toList());
  }

}
