package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.constants.HtmlTags;
import com.cognifide.qa.bb.qualifier.PageObject;

@PageObject(css = ".cmp-teaser")
public class TeaserComponent {

  @FindBy(css = ".cmp-image__image")
  private WebElement image;

  @FindBy(css = ".cmp-teaser__title")
  private WebElement title;

  @FindBy(css = ".cmp-teaser__title-link")
  private WebElement titleLink;

  @FindBy(css = ".cmp-teaser__description > p")
  private WebElement description;

  @FindBy(css = ".cmp-teaser__description")
  private List<WebElement> elements;

  @FindBy(css = ".cmp-teaser__description")
  private WebElement isDescriptionVisible;

  @FindBy(css = ".cmp-teaser__action-link")
  private List<WebElement> actionLinks;

  public boolean isTeaserDescriptionEmpty() {
    return elements.isEmpty();
  }

  public String getTeaserDescriptionFromLinkedPage() {
    return isDescriptionVisible.getText();
  }

  public String getTeaserImage() {
    return image.getAttribute(HtmlTags.Attributes.TITLE);
  }

  public String getTeaserTitle() {
    return title.getText();
  }

  public String getTeaserTitleFromLinkedPage() {
    return titleLink.getText();
  }

  public String getTeaserTitleLink() {
    return titleLink.getAttribute(HtmlTags.Attributes.HREF);
  }

  public String getTeaserDescription() {
    return description.getText();
  }

  public List<String> getActionLinkTexts() {
    return actionLinks.stream().map(WebElement::getText)
        .collect(Collectors.toList());
  }
}
