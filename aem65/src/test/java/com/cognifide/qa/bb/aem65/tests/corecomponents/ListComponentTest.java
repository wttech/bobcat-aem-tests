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
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.ListComponent;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

/**
 * These tests verify if Bobcat can handle the configuration of the List Component
 * https://opensource.adobe.com/aem-core-wcm-components/library/list.html
 */
@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("List Component configuration")
@DisplayName("Author can configure for the List Component the...")
public class ListComponentTest extends AbstractAemAuthorTest {

  private static final String COMPONENT_PAGE_PATH = "/content/core-components-examples/library/list-component-test-page";
  private static final String LIST_ITEM_ROOT_PATH = "/content/we-retail/us/en/list-item-root";
  private static final String[] LIST_ITEM_PATHS = {
      "/content/we-retail/us/en/list-item-root/list-item-one",
      "/content/we-retail/us/en/list-item-root/list-item-two",
      "/content/we-retail/us/en/list-item-root/list-item-three",
      "/content/we-retail/us/en/list-item-root/list-item-three/list-item-four"};

  private TestPage page;
  private ListComponent component;

  @BeforeEach
  public void createAndOpenComponentTestPage() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(LIST_ITEM_ROOT_PATH,
        SlingDataXMLBuilder
            .buildFromFile("testpages/core-components/list/listItemRootTestPage.xml")));
    for (int i = 0; i < LIST_ITEM_PATHS.length; i++) {
      controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(LIST_ITEM_PATHS[i],
          SlingDataXMLBuilder.buildFromFile(
              String.format("testpages/core-components/list/listItemTestPage%s.xml", i + 1))));
    }

    controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(COMPONENT_PAGE_PATH,
        SlingDataXMLBuilder.buildFromFile(
            "testpages/core-components/list/listComponentTestPage.xml")));

    page = bobcatPageFactory.create("/editor.html" + COMPONENT_PAGE_PATH + ".html", TestPage.class);
    page.open();
  }

  @Test
  @DisplayName("creation of list with static items")
  public void configureToUseStaticItems() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "List (v2)", 0,
            new ResourceFileLocation("component-configs/core-components/list/fixed-items.yaml")));
    component = page.getContent(ListComponent.class, 0);
    assertThat(component.getText()).as("Check if the list items are filtered correctly")
        .matches("List Item One\n"
            + "List Item Two\n"
            + "List Item Three");
  }

  @Test
  @DisplayName("creation of list with search")
  public void configureToUseSearch() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "List (v2)", 0,
            new ResourceFileLocation("component-configs/core-components/list/search-items.yaml")));
    component = page.getContent(ListComponent.class, 0);
    assertThat(component.getText()).as("Check if the list items are filtered correctly")
        .matches("List Item Four");
  }

  @Test
  @DisplayName("creation of list with tags ")
  public void configureToUseTags() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "List (v2)", 0,
            new ResourceFileLocation("component-configs/core-components/list/tagged-items.yaml")));
    component = page.getContent(ListComponent.class, 0);
    assertThat(component.getText()).as("Check if the list items are filtered correctly")
        .matches("List Item Four\n"
            + "List Item Three");
  }

  @Test
  @DisplayName("creation of list with children")
  public void configureToUseChildren() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "List (v2)", 0,
            new ResourceFileLocation("component-configs/core-components/list/child-items.yaml")));
    component = page.getContent(ListComponent.class, 0);
    assertThat(component.getText()).as("Check if the list items are filtered correctly")
        .matches("List Item One\n"
            + "List Item Two\n"
            + "List Item Three\n"
            + "List Item Four");
  }

  @Test
  @DisplayName("ordering and sorting schema")
  public void configureOrderingAndSortingSchema() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "List (v2)", 0, new ResourceFileLocation(
            "component-configs/core-components/list/sort-and-order.yaml")));
    component = page.getContent(ListComponent.class, 0);
    assertThat(component.getText()).as("Check if the list items are ordered correctly")
        .matches("List Item Two\n"
            + "List Item Three\n"
            + "List Item One\n"
            + "List Item Four");
  }

  @Test
  @DisplayName("max items number")
  public void configureMaxItemsNumber() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "List (v2)", 0, new ResourceFileLocation(
            "component-configs/core-components/list/max-items.yaml")));
    component = page.getContent(ListComponent.class, 0);
    assertThat(component.getText()).as("Check if the list items is limited to two items")
        .matches("List Item One\n"
            + "List Item Two");
  }

  @Test
  @DisplayName("item settings")
  public void configureItemSettings() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "List (v2)", 0, new ResourceFileLocation(
            "component-configs/core-components/list/item-settings.yaml")));
    component = page.getContent(ListComponent.class, 0);
    for (int i = 0; i < LIST_ITEM_PATHS.length; i++) {
      assertThat(component.getLinks().get(i))
          .as(String.format("Check if the list item nr %d links to it's page", i))
          .endsWith(LIST_ITEM_PATHS[i] + ".html");
    }
  }

  @AfterEach
  public void deleteComponentTestPage() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(LIST_ITEM_ROOT_PATH));
    for (String path : LIST_ITEM_PATHS) {
      controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(path));
    }

    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(COMPONENT_PAGE_PATH));
  }
}
