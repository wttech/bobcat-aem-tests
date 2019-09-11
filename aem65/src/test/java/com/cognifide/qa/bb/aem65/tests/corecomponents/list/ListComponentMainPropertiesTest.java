package com.cognifide.qa.bb.aem65.tests.corecomponents.list;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingDataXMLBuilder;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingPageData;
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
public class ListComponentMainPropertiesTest extends AbstractListComponentTest {

  private static final String COMPONENT_PAGE_PATH = "/content/we-retail/us/en/blank-list-component-test-page";

  @BeforeEach
  public void createAndOpenComponentTestPage() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(COMPONENT_PAGE_PATH,
        SlingDataXMLBuilder.buildFromFile(
            "testpages/core-components/list/main-properties/blankListComponentTestPage.xml")));

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

  @AfterEach
  public void deleteComponentTestPage() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(COMPONENT_PAGE_PATH));
  }
}
