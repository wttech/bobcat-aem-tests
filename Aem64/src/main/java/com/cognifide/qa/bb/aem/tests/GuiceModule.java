package com.cognifide.qa.bb.aem.tests;

import com.cognifide.qa.bb.aem.core.modules.Aem64FullModule;
import com.cognifide.qa.bb.aem.tests.pageobjects.TextComponent;
import com.cognifide.qa.bb.aem.tests.pageobjects.TextComponentImpl;
import com.cognifide.qa.bb.modules.CoreModule;
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
    install(new CoreModule());
    install(new Aem64FullModule());
    bind(TextComponent.class).to(TextComponentImpl.class);
  }
}
