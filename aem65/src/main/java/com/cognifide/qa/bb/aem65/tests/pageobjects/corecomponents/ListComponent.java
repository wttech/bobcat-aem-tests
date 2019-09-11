package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents;

import java.util.ArrayList;
import java.util.List;

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
    List<String> links = new ArrayList<>();
    for (WebElement element :
        linkElements) {
      links.add(element.getAttribute(Attributes.HREF));
    }
    return links;
  }

}
