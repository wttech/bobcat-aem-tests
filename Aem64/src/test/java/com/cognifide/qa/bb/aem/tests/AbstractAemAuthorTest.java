package com.cognifide.qa.bb.aem.tests;

import com.cognifide.qa.bb.aem.core.login.AemAuthenticationController;
import com.google.inject.Inject;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractAemAuthorTest {

  @Inject
  private AemAuthenticationController aemAuthenticationController;

  @BeforeEach
  void init() {
    aemAuthenticationController.login();
  }

}
