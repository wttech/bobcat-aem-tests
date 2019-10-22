package com.cognifide.qa.bb.aem65.tests.corecomponents;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.aem.core.component.actions.ConfigureComponentData;
import com.cognifide.qa.bb.aem.core.component.configuration.ResourceFileLocation;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingDataXMLBuilder;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingPageData;
import com.cognifide.qa.bb.aem65.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.TeaserComponent;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

/**
 * These tests verify if Bobcat can handle the configuration of the Teaser Component
 * https://opensource.adobe.com/aem-core-wcm-components/library/teaser.html
 */

@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("Teaser Component configuration")
@DisplayName("Author can configure for Teaser Component the...")

public class TeaserComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH = "/content/core-components-examples/library/teaserComponentTestPage";

  private TestPage page;
  private TeaserComponent component;

  @BeforeEach
  public void createAndOpenTestPage() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH,
        SlingDataXMLBuilder
            .buildFromFile("testpages/core-components/teaserComponentTestPage.xml")));
    page = bobcatPageFactory.create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    page.open();
  }

  @Test
  @DisplayName("image")
  public void configureImageTeaser() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Teaser (v1)", 0,
            new ResourceFileLocation("component-configs/core-components/teaser/image.yaml")));
    component = page.getContent(TeaserComponent.class, 0);
    assertThat(component.getTeaserImage())
        .as("Check if image is configured")
        .matches("Gray lava rock formation");
  }

  @Test
  @DisplayName("title")
  public void configureTitle() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Teaser (v1)", 0,
            new ResourceFileLocation("component-configs/core-components/teaser/title.yaml")));
    component = page.getContent(TeaserComponent.class, 0);
    assertThat(component.getTeaserTitle())
        .as("Check if the title is configured")
        .matches("This is teaser title");
  }

  @Test
  @DisplayName("link on title")
  public void configureTitleLink() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Teaser (v1)", 0,
            new ResourceFileLocation("component-configs/core-components/teaser/titleLink.yaml")));
    component = page.getContent(TeaserComponent.class, 0);
    assertThat(component.getTeaserTitleLink())
        .as("Check if the link is configured")
        .endsWith("/content/core-components-examples/library/teaser.html");
  }


  @Test
  @DisplayName("title taken from linked page")
  public void configureTitleFromLinkedPage() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Teaser (v1)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/teaser/titleFromLinkedPage.yaml")));
    component = page.getContent(TeaserComponent.class, 0);
    assertThat(component.getTeaserTitleFromLinkedPage())
        .as("Check if the title is taken from linked page")
        .matches("Women");
  }

  @Test
  @DisplayName("description")
  public void configureDescription() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Teaser (v1)", 0,
            new ResourceFileLocation("component-configs/core-components/teaser/description.yaml")));
    component = page.getContent(TeaserComponent.class, 0);
    assertThat(component.getTeaserDescription())
        .as("Check if the description is configured")
        .matches("This is teaser description");
  }

  @Test
  @DisplayName("empty description taken from linked page")
  public void configureDescriptionFromLinkedPage() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Teaser (v1)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/teaser/descriptionEmptyFromLinkedPage.yaml")));
    component = page.getContent(TeaserComponent.class, 0);
    assertThat(component.isTeaserDescriptionEmpty())
        .as("Check if empty description isn't added into component")
        .isTrue();
  }

  @Test
  @DisplayName("description taken from linked page")
  public void configureDescriptionFromLinkedPage2() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Teaser (v1)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/teaser/descriptionFromLinkedPage.yaml")));
    component = page.getContent(TeaserComponent.class, 0);
    assertThat(component.getTeaserDescriptionFromLinkedPage())
        .as("Check if the description is taken from linked page")
        .matches("Test description from Properties");
  }

  @Test
  @DisplayName("Call-To-Actions")
  public void configureCallToActions() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Teaser (v1)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/teaser/callToActions.yaml")));
    component = page.getContent(TeaserComponent.class, 0);
    assertThat(component.getActionLinkTexts()).as("Check if CTAs are configured properly")
        .containsSequence("English", "Switzerland");
  }

  @AfterEach
  public void deleteTestPage() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
  }
}
