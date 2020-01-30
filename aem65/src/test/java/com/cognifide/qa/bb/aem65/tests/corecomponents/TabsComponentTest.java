package com.cognifide.qa.bb.aem65.tests.corecomponents;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.aem.core.component.actions.ConfigureComponentData;
import com.cognifide.qa.bb.aem.core.component.configuration.ResourceFileLocation;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingDataXMLBuilder;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingPageData;
import com.cognifide.qa.bb.aem65.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.TabsComponent;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

/**
 * These tests verify if Bobcat can handle the configuration of the Tabs Component
 * https://opensource.adobe.com/aem-core-wcm-components/library/tabs.html
 */
@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("Tabs Component configuration")
@DisplayName("Author can configure for Tabs Component the...")
public class TabsComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH =
      "/content/core-components-examples/library/tabs-component-test-page";

  private TestPage page;
  private TabsComponent component;

  @BeforeEach
  public void setup() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH,
        SlingDataXMLBuilder.buildFromFile("testpages/core-components/tabsComponentTestPage.xml")));
    page = bobcatPageFactory.create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    page.open();
  }

  @Test
  @DisplayName("items")
  @Disabled
  // TODO: Enable this test after handling the multifield with a pop-up dialog with a list selection is handled in Bobcat (new DialogField to be developed by Katarzyna Jaśkowska)
  public void configureItems() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Tabs (v1)", 0,
            new ResourceFileLocation("component-configs/core-components/tabs/items.yaml")));
    component = page.getContent(TabsComponent.class, 0);
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(component.getTabCount()).as("Check if the number of tabs is correct")
        .isEqualTo(3);
    softly.assertThat(component.getTabNames().get(0))
        .as("Check if the name of the first tab is correct")
        .matches("Tab 1");
    softly.assertThat(component.getTabNames().get(2))
        .as("Check if the name of the last tab is correct")
        .matches("Tab 3");
    softly.assertAll();
  }

  @Test
  @DisplayName("default item")
  public void configureDefaultItem() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Tabs (v1)", 0,
            new ResourceFileLocation("component-configs/core-components/tabs/default-item.yaml")));
    component = page.getContent(TabsComponent.class, 0);
    assertThat(component.getDefaultTabName())
        .as("Check if the default tab is configured properly")
        .matches("Tab 2");
  }

  @Test
  @DisplayName("aria label")
  public void configureAriaLabel() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Tabs (v1)", 0,
            new ResourceFileLocation("component-configs/core-components/tabs/aria-label.yaml")));
    component = page.getContent(TabsComponent.class, 0);
    assertThat(component.getAriaLabel())
        .as("Check if the aria label is configured properly")
        .matches("This is an aria label");
  }

  @AfterEach
  public void cleanup() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
  }
}
