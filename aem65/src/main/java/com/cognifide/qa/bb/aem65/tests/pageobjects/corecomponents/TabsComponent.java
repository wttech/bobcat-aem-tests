package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

@PageObject(css = ".cmp-tabs")
public class TabsComponent {

  @Inject
  @CurrentScope
  private WebElement component;

  @FindBy(css = ".cmp-tabs__tab")
  private List<WebElement> tabs;

  @FindBy(css = ".cmp-tabs__tab--active")
  private WebElement activeTab;

  public int getTabCount() {
    return tabs.size();
  }

  public List<String> getTabNames() {
    return tabs.stream().map((WebElement::getText)).collect(Collectors.toList());
  }

  public String getAriaLabel() {
    return component.findElement(By.cssSelector("ol")).getAttribute("aria-label");
  }

  public String getDefaultTabName() {
    return activeTab.getText();
  }

}
