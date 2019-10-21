package com.cognifide.qa.bb.aem65.tests.corecomponents;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.aem.core.component.actions.ConfigureComponentData;
import com.cognifide.qa.bb.aem.core.component.configuration.ResourceFileLocation;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingDataXMLBuilder;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingPageData;
import com.cognifide.qa.bb.aem65.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.CarouselComponent;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import java.util.Objects;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.filter;

/**
 * These tests verify if Bobcat can handle the configuration of the Teaser Component
 * https://opensource.adobe.com/aem-core-wcm-components/library/teaser.html
 */

@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("Carousel Component configuration")
@DisplayName("Author can configure for Carousel Component the...")

public class CarouselComponentTest extends AbstractAemAuthorTest {

    private static final String TEST_PAGE_PATH = "/content/core-components-examples/library/carouselComponentTestPage";

    private TestPage page;
    private CarouselComponent component;

    @BeforeEach
    public void createAndOpenTestPage() throws ActionException {
      controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH,
          SlingDataXMLBuilder.buildFromFile("testpages/core-components/carouselComponentTestPage.xml")));
      page = bobcatPageFactory.create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
      page.open();
    }

    @Test
    @DisplayName("items")
    public void configureCarouselItems() throws ActionException {
      controller.execute(AemActions.CONFIGURE_COMPONENT,
          new ConfigureComponentData("container","Carousel (v1)",2,
              new ResourceFileLocation("component-configs/core-components/carousel/newItem.yaml")));
    }

    @Test
    @DisplayName("autoplay")
    public void configureCarouselAutoplay() throws ActionException {
      controller.execute(AemActions.CONFIGURE_COMPONENT,
          new ConfigureComponentData("container", "Carousel (v1)",0,
              new ResourceFileLocation("component-configs/core-components/carousel/autoplay.yaml")));
      component = page.getContent(CarouselComponent.class,0);
      assertThat(component.isAutoplayActive())
          .as("Check if autoplay is active")
          .isTrue();
    }

    @Test
    @DisplayName("autoplay transition delay")
    public void configureCarouselAutplayTransitionDelay() throws ActionException {
      controller.execute(AemActions.CONFIGURE_COMPONENT,
          new ConfigureComponentData("container", "Carousel (v1)", 0,
              new ResourceFileLocation("component-configs/core-components/carousel/autoplayTransitionDelay.yaml")));
      component = page.getContent(CarouselComponent.class,0);
      assertThat(component.getCarouselAutoplayTransitionDelay())
          .as("Check if transition delay is changed")
          .matches("1000");
    }

    @Test
    @DisplayName("disable automatic pause on hover")
    public void configureCarouselAutopauseDisabled() throws ActionException {
      controller.execute(AemActions.CONFIGURE_COMPONENT,
          new ConfigureComponentData("container", "Carousel (v1)", 1,
              new ResourceFileLocation("component-configs/core-components/carousel/autopauseDisabled.yaml")));
      component = page.getContent(CarouselComponent.class, 0);
      assertThat(component.isCarouselAutopauseDisabled())
          .as("Check if automatic pause on hover is disabled")
          .matches(Objects::isNull);
    }

    @Test
    @DisplayName("accessibility label")
    public void configureCarouselAccessibilityLabel() throws ActionException {
      controller.execute(AemActions.CONFIGURE_COMPONENT,
          new ConfigureComponentData("container", "Carousel (v1)", 0,
              new ResourceFileLocation("component-configs/core-components/carousel/accessibilityLabel.yaml")));
      component = page.getContent(CarouselComponent.class, 0);
      assertThat(component.getCarouselAccessibilityLabel())
          .as("Check if accessibility label is set")
          .matches("Accessibility test label text");
    }

    @AfterEach
  public void deleteTestPage() throws ActionException {
      controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
    }
}
