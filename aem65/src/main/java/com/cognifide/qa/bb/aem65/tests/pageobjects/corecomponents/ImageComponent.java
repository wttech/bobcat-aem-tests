package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.constants.HtmlTags.Attributes;
import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

@PageObject(css = ".cmp-image__image")
public class ImageComponent {

  @Inject
  @CurrentScope
  private WebElement component;

  @FindBy(xpath = "../span[contains(@class,'cmp-image__title')]")
  private WebElement caption;

  @FindBy(xpath = "..")
  private WebElement link;

  public String getSrc() {
    return component.getAttribute(Attributes.SRC);
  }

  public String getAlt() {
    return component.getAttribute(Attributes.ALT);
  }

  public String getCaption() {
    return caption.getText();
  }

  public String getLink() {
    return link.getAttribute(Attributes.HREF);
  }
}
