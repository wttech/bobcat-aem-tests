package com.cognifide.qa.bb.aem65.tests;

import com.cognifide.qa.bb.aem65.tests.pageobjects.TextComponent;
import com.cognifide.qa.bb.aem65.tests.pageobjects.TextComponentImpl;
<<<<<<< HEAD
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.teaser.TeaserComponent;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.teaser.TeaserComponentImpl;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.title.TitleComponent;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.title.TitleComponentImpl;
=======
>>>>>>> master
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
    bind(TeaserComponent.class).to(TeaserComponentImpl.class);
  }
}
