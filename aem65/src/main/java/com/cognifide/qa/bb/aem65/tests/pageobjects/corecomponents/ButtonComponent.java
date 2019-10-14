package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents;

import com.cognifide.qa.bb.constants.HtmlTags;
import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@PageObject(css = ".cmp-button")
public class ButtonComponent {

    @Inject
    @CurrentScope
    private WebElement component;

    @FindBy(css = ".cmp-button__text")
    private WebElement text;

    @FindBy(css = ".cmp-button__icon")
    private WebElement icon;

    private static final String ARIA_LABEL = "aria-label";

    public String getText() {
        return text.getText();
    }

    public String getLink() {
        return component.getAttribute(HtmlTags.Attributes.HREF);
    }

    public String getIconClasses() {
        return icon.getAttribute(HtmlTags.Attributes.CLASS);
    }

    public String getAriaLabel() {
        return component.getAttribute(ARIA_LABEL);
    }
}
