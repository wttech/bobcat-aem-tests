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
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.BreadcrumbComponent;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

/**
 * These tests verify if Bobcat can handle the configuration of the Breadcrumb Component
 * https://opensource.adobe.com/aem-core-wcm-components/library/breadcrumb.html
 */
@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("Breadcrumb Component configuration")
@DisplayName("Author can configure for Breadcrumb Component the...")
public class BreadcrumbComponentTest extends AbstractAemAuthorTest {

  private static final String HIDDEN_PAGE_PATH = "/content/core-components-examples/breadcrumbhiddentestpage";
  private static final String COMPONENT_PAGE_PATH =
      "/content/core-components-examples/breadcrumbhiddentestpage/breadcrumbtestpage";

  private TestPage testPage;
  private BreadcrumbComponent component;

  @BeforeEach
  public void createAndOpenTestPage() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(HIDDEN_PAGE_PATH,
        SlingDataXMLBuilder.buildFromFile(
            "testpages/core-components/breadcrumb/breadcrumbComponentHiddenTestPage.xml")));

    controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(COMPONENT_PAGE_PATH,
        SlingDataXMLBuilder.buildFromFile(
            "testpages/core-components/breadcrumb/breadcrumbComponentTestPage.xml")));

    testPage = bobcatPageFactory
        .create("/editor.html" + COMPONENT_PAGE_PATH + ".html", TestPage.class);
    testPage.open();
  }

  @Test
  @DisplayName("navigation start level - decrease default value")
  public void decreaseNavigationStartLevel() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Breadcrumb (v2)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/breadcrumb/navigation-start-level.yaml")));
    component = testPage.getContent(BreadcrumbComponent.class, 0);
    assertThat(component.getText()).as("Check if breadcrumb value contains first level page")
        .contains("Core Components");
  }

  @Test
  @DisplayName("checkbox values change to true")
  public void changeCheckboxesToTrue() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Breadcrumb (v2)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/breadcrumb/checkbox-configuration.yaml")));
    component = testPage.getContent(BreadcrumbComponent.class, 0);
    assertThat(component.getText()).as("Check if current page is not present in breadcrumb")
        .doesNotContain("breadcrumbTestPage").contains("breadcrumbHiddenTestPage");
  }

  @AfterEach
  public void deleteTestPage() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(COMPONENT_PAGE_PATH));
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(HIDDEN_PAGE_PATH));
  }
}
