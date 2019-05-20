package com.cognifide.qa.bb.aem65.tests;

import org.junit.jupiter.api.BeforeEach;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.api.actions.ActionsController;
import com.cognifide.qa.bb.page.BobcatPageFactory;
import com.google.inject.Inject;

public abstract class AbstractAemAuthorTest {

  @Inject
  protected ActionsController controller;

  @Inject
  protected BobcatPageFactory bobcatPageFactory;

  @BeforeEach
  void init() throws ActionException {
    controller.execute(AemActions.LOG_IN);
  }

}
