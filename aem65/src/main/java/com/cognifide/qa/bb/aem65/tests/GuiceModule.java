package com.cognifide.qa.bb.aem65.tests;

import com.cognifide.qa.bb.aem65.tests.pageobjects.TextComponent;
import com.cognifide.qa.bb.aem65.tests.pageobjects.TextComponentImpl;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.list.ListComponent;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.list.ListComponentImpl;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.title.TitleComponent;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.title.TitleComponentImpl;
import com.google.inject.AbstractModule;

/**
 * Your Guice module.
 * <p/>
 * The two modules that are installed here are probably the minimum that you'll want to have in your
 * project.
 * <p/>
 * CoreModule -- all core functionality, like scope PageObjects or FrameSwitcher. ReporterModule --
 * reporting functionality, including reporting rule and HTML report.
 */
public class GuiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(TextComponent.class).to(TextComponentImpl.class);
    bind(TitleComponent.class).to(TitleComponentImpl.class);
    bind(ListComponent.class).to(ListComponentImpl.class);
  }
}
