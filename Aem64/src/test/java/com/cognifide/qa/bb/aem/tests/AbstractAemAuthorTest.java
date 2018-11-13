package com.cognifide.qa.bb.aem.tests;

import org.junit.jupiter.api.BeforeEach;

import com.cognifide.qa.bb.aem.core.api.ActionException;
import com.cognifide.qa.bb.aem.core.api.Actions;
import com.cognifide.qa.bb.aem.core.api.BobcatController;
import com.cognifide.qa.bb.page.BobcatPageFactory;
import com.google.inject.Inject;

public abstract class AbstractAemAuthorTest {

  @Inject
  protected BobcatController bobcatController;

  @Inject
  protected BobcatPageFactory bobcatPageFactory;

  @BeforeEach
  void init() throws ActionException {
    bobcatController.execute(Actions.Login.LOG_IN);
  }

}
