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
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.ImageComponent;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

/**
 * These tests verify if Bobcat can handle the configuration of the Image Component
 * https://opensource.adobe.com/aem-core-wcm-components/library/image.html
 */
@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("Image Component configuration")
@DisplayName("Author can configure for Image Component the...")
public class ImageComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH = "/content/core-components-examples/library/image-component-test-page";

  private TestPage page;
  private ImageComponent component;

  @BeforeEach
  public void setup() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH,
        SlingDataXMLBuilder.buildFromFile("testpages/core-components/imageComponentTestPage.xml")));
    page = bobcatPageFactory.create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    page.open();
  }

  @Test
  @DisplayName("asset")
  public void configureAsset() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Image (v2)", 1,
            new ResourceFileLocation("component-configs/core-components/image/asset.yaml")));
    component = page.getContent(ImageComponent.class, 3);
    assertThat(component.getSrc()).as("Check if the img src is configured")
        .matches(String.format(".*%s.*majestic-rainbow.jpeg", TEST_PAGE_PATH));
  }

  @Test
  @DisplayName("decorative image metadata")
  public void configureDecorativeImage() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Image (v2)", 0, new ResourceFileLocation(
            "component-configs/core-components/image/decorative-image.yaml")));
    component = page.getContent(ImageComponent.class, 2);
    assertThat(component.getAlt()).as("Check if the alt attribute is empty").isEmpty();
  }

  @Test
  @DisplayName("alternative text metadata")
  public void configureAlternativeText() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Image (v2)", 0, new ResourceFileLocation(
            "component-configs/core-components/image/alt-text.yaml")));
    component = page.getContent(ImageComponent.class, 2);
    assertThat(component.getAlt()).as("Check if the alt text is configured")
        .matches("Custom alt text");
  }

  @Test
  @DisplayName("caption metadata")
  public void configureCaption() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Image (v2)", 0,
            new ResourceFileLocation("component-configs/core-components/image/caption.yaml")));
    component = page.getContent(ImageComponent.class, 2);
    assertThat(component.getCaption()).as("Check if the caption text is configured")
        .matches("Custom caption");
  }

  @Test
  @DisplayName("link metadata")
  public void configureLink() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Image (v2)", 0,
            new ResourceFileLocation("component-configs/core-components/image/link.yaml")));
    component = page.getContent(ImageComponent.class, 2);
    assertThat(component.getLink()).as("Check if the link is configured")
        .endsWith("https://cognifide.github.io/bobcat/");
  }

  @AfterEach
  public void cleanup() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
  }

}
