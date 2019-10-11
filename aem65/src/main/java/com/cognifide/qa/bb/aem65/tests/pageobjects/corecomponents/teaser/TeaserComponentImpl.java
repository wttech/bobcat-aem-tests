package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.teaser;

import com.cognifide.qa.bb.constants.HtmlTags;
import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.cognifide.qa.bb.wait.BobcatWait;
import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.util.Assert;

import java.util.List;

@PageObject (css = ".cmp-teaser")
public class TeaserComponentImpl implements TeaserComponent {

    @Inject
    @CurrentScope
    private WebElement component;

    @FindBy (css = ".cmp-image__image")
    private WebElement image;

    @FindBy (css = ".cmp-teaser__content")
    private WebElement content;

    @FindBy (css = ".cmp-teaser__title")
    private WebElement title;

    @FindBy (css = ".cmp-teaser__title-link")
    private WebElement titleLink;

    @FindBy (css = ".cmp-teaser__description > p")
    private WebElement description;

    @FindBy (css = ".cmp-teaser__description")
    private List<WebElement> elements;

    @FindBy (css = ".cmp-teaser__description")
    private WebElement isDescriptionVisible;

    @Inject
    private BobcatWait bobcatWait;

    public void checkDescriptionVisibility(){
        Assert.isTrue(elements.isEmpty(),"Element exist when it shouldn't");
    }

    public void checkIfContentClassIsVisible(){
        Assert.isTrue(content.isDisplayed(),"Error - Missing class content");
    }

    public String getTeaserDescriptionFromLinkedPage() { return isDescriptionVisible.getAttribute(HtmlTags.Properties.INNER_HTML); }

    public String getTeaserImage() { return image.getAttribute(HtmlTags.Attributes.TITLE); }

    public String getTeaserTitle() { return title.getAttribute(HtmlTags.Properties.INNER_HTML); }

    public String getTeaserTitleFromLinkedPage() { return titleLink.getAttribute(HtmlTags.Properties.INNER_HTML); }

    public String getTeaserTitleLink () { return titleLink.getAttribute(HtmlTags.Properties.OUTER_HTML);}

    public String getTeaserDescription () { return description.getAttribute(HtmlTags.Properties.INNER_HTML);}
}
