package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.constants.HtmlTags.Attributes;
import com.cognifide.qa.bb.constants.HtmlTags.Properties;
import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

@PageObject(css = ".cmp-title")
public class TitleComponent {

  @Inject
  @CurrentScope
  private WebElement component;

  @FindBy(css = ".cmp-title__text")
  private WebElement text;

  @FindBy(css = ".cmp-title__link")
  private WebElement link;

  public String getInnerHtml() {
    return component.getAttribute(Properties.INNER_HTML);
  }

  public String getTextHtmlTag() {
    return text.getTagName();
  }

  public String getLinkHref() {
    return link.getAttribute(Attributes.HREF);
  }

}
