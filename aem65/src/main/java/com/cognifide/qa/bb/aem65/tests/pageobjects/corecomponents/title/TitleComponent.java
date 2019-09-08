package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.title;

import com.cognifide.qa.bb.qualifier.PageObjectInterface;

@PageObjectInterface
public interface TitleComponent {

  String getInnerHtml();

  String getTextHtmlTag();

  String getLinkHref();
}
