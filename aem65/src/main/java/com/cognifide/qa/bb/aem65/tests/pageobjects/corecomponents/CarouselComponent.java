package com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents;

import com.cognifide.qa.bb.constants.HtmlTags;
import com.cognifide.qa.bb.constants.HtmlTags.Attributes;
import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@PageObject(css = ".cmp-carousel")
public class CarouselComponent {

    @Inject
    @CurrentScope
    private WebElement component;

    @FindBy(css = ".cmp-carousel__item")
    private List<WebElement> carouselItems;

    @FindBy(css = ".cmp-carousel__action--pause")
    private WebElement autoplay;

    public boolean isAutoplayActive() {
      return autoplay.isDisplayed();
    }


    public String getCarouselAutoplayTransitionDelay() {
        return component.getAttribute("data-cmp-delay");
    }

    public String isCarouselAutopauseDisabled() {
      return component.getAttribute("data-cmp-autopause-disabled");
    }

    public String getCarouselAccessibilityLabel() {
      return component.getAttribute("aria-label");
    }
}
