package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents;

import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import org.openqa.selenium.WebElement;

import com.google.inject.Inject;

@PageObject(css = ".cmp-breadcrumb")
public class BreadcrumbComponent {

    @Inject
    @CurrentScope
    private WebElement component;

    public String getText() {
        return component.getText();
    }
}
