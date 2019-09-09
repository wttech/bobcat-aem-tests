package com.cognifide.qa.bb.aem65.tests.corecomponents;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingDataXMLBuilder;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingPageData;
import com.cognifide.qa.bb.aem65.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("List Component configuration")
@DisplayName("Author can configure for the List Component the...")
public class ListComponentTest extends AbstractAemAuthorTest {

  private static final String COMPONENT_PAGE_PATH = "/content/we-retail/us/en/list-component-test-page";
  private static final String[] LIST_ITEM_PATHS = {
      "/content/we-retail/us/en/list-component-test-page/list-item-01",
      "/content/we-retail/us/en/list-component-test-page/list-item-02",
      "/content/we-retail/us/en/list-component-test-page/list-item-03",
      "/content/we-retail/us/en/list-component-test-page/list-item-03/list-item-04"};

  private TestPage page;

  @BeforeEach
  public void prepareTestDataAndOpenTestPage() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(COMPONENT_PAGE_PATH,
        SlingDataXMLBuilder.buildFromFile(
            "testpages/core-components/list/listComponentTestPage.xml")));

    for (int i = 0; i < LIST_ITEM_PATHS.length; i++) {
      controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(LIST_ITEM_PATHS[i],
          SlingDataXMLBuilder.buildFromFile(
              String.format("testpages/core-components/list/listItemTestPage%s.xml", i + 1))));
    }

    page = bobcatPageFactory.create("/editor.html" + COMPONENT_PAGE_PATH + ".html", TestPage.class);
    page.open();
  }

  @Test
  @DisplayName("creation of list with static items")
  public void configureToUseStaticItems() {
  }

  @Test
  @DisplayName("creation of list with search")
  public void configureToUseSearch() {
  }

  @Test
  @DisplayName("creation of list with tags ")
  public void configureToUseTags() {
  }

  @Test
  @DisplayName("creation of list with children")
  public void configureToUseChildren() {
  }

  @Test
  @DisplayName("ordering and sorting schema")
  public void configureOrderingAndSortingSchema() {

  }

  @Test
  @DisplayName("max items number")
  public void configureMaxItemsNumber() {
  }

  @Test
  @DisplayName("item settings")
  public void configureItemSettings() {

  }

  @AfterEach
  public void deleteTestData() {

  }
}
