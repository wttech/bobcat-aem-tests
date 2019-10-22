package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

@PageObject(css = ".cmp-contentfragmentlist")
public class ContentFragmentListComponent {

  @Inject
  @CurrentScope
  private WebElement component;

  @FindBy(css = "article")
  private List<WebElement> articles;

  public List<String> getArticleTexts() {
    return articles.stream().map(WebElement::getText).collect(Collectors.toList());
  }

  public String getCollectiveText() {
    return component.getText();
  }

}
