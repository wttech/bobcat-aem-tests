package com.cognifide.qa.bb.aem.tests.pages;

import com.cognifide.qa.bb.aem.core.pages.AemAuthorPage;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.cognifide.qa.bb.utils.WebElementUtils;
import com.google.inject.Inject;
import javax.inject.Named;
import org.openqa.selenium.support.ui.ExpectedConditions;

@PageObject
public class TestPage extends AemAuthorPage<TestPage> {

  @Inject
  private WebElementUtils webElementUtils;

  @Inject
  @Named("page.title.timeout")
  protected int pageTitleTimeout;

  private String title = "English";

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public boolean isDisplayed() {
    return webElementUtils
        .isConditionMet(ExpectedConditions.titleIs(getTitle()), pageTitleTimeout);
  }

  public boolean isNotAvailable() {
    return webElementUtils
        .isConditionMet(ExpectedConditions.titleContains("404 Resource"), pageTitleTimeout);
  }
}
