package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.teaser;

import com.cognifide.qa.bb.qualifier.PageObjectInterface;

@PageObjectInterface
public interface TeaserComponent {

    String getTeaserImage();

    String getTeaserTitle();

    String getTeaserTitleLink();

    String getTeaserDescription();

    String getTeaserTitleFromLinkedPage();

    String getTeaserActionContainer();

    String getTeaserActionLink();

    String getTeaserDescriptionFromLinkedPage();

    void checkDescriptionVisibility();
}
