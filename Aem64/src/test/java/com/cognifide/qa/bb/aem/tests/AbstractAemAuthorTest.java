package com.cognifide.qa.bb.aem.tests;

import com.cognifide.qa.bb.aem.core.login.AemAuthenticationController;
import com.cognifide.qa.bb.aem.core.pages.AemTestPageControler;
import com.cognifide.qa.bb.page.BobcatPageFactory;
import com.google.inject.Inject;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractAemAuthorTest {

  @Inject
  private AemAuthenticationController aemAuthenticationController;

  @Inject
  protected AemTestPageControler aemTestPageControler;

  @Inject
  protected BobcatPageFactory bobcatPageFactory;

  @BeforeEach
  void init() {
    aemAuthenticationController.login();
  }

}
