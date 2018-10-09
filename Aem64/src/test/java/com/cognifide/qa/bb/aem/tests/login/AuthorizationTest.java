package com.cognifide.qa.bb.aem.tests.login;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.cognifide.qa.bb.RunWithJunit5;
import com.cognifide.qa.bb.aem.core.login.AemAuthenticationController;
import com.cognifide.qa.bb.aem.tests.GuiceModule;
import com.cognifide.qa.bb.aem.tests.pages.TestPage;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.page.BobcatPageFactory;
import com.google.inject.Inject;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

@RunWithJunit5
@Modules(GuiceModule.class)
@Epic("AEM 6.4 Base Tests")
@Feature("Login to AEM")
public class AuthorizationTest {

  @Inject
  private AemAuthenticationController aemAuthenticationController;

  @Inject
  private BobcatPageFactory bobcatPageFactory;


  @Test
  @Story("Login to AEM and open Test page")
  @Description("Login to author instance and open test page")
  public void loginTest() {
    TestPage testpage = bobcatPageFactory.create("/content/we-retail/us/en.html", TestPage.class);
    aemAuthenticationController.login();
    assertTrue(((TestPage) testpage.open()).isDisplayed());
  }

  @Test
  @Story("Login to AEM and open Test page")
  @Description("Login to author instance and open test page in one step")
  public void loginAndOpenTest() {
    TestPage testpage = bobcatPageFactory.create("/content/we-retail/us/en.html", TestPage.class);
    aemAuthenticationController.login(testpage);
    assertTrue(testpage.isDisplayed());
  }
}
