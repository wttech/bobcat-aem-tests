package com.cognifide.qa.bb.aem65.tests.corecomponents;

import org.assertj.core.api.SoftAssertions;
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
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.FormContainerComponent;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

/**
 * These tests verify if Bobcat can handle the configuration of the Form Container Component
 * https://docs.adobe.com/content/help/en/experience-manager-core-components/using/components/forms/form-container.html
 * These tests don't do assertions against anything else than the form's visibility and "thank you"
 * page path due to the fact that it's possible to see via DOM if the Form Container is configured
 * properly, which isn't the point of the tests anyway. The tests check if Bobcat can configure them
 * - and this is verified by attempting to configure the component and then checking if it's visible
 * on the page - should any issues with the configuration occur the tests will surely fail.
 */
@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("Form Container Component configuration")
@DisplayName("Author can configure for Form Container Component the...")
public class FormContainerComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH =
      "/content/core-components-examples/library/form-components-test-page";
  private static final String THANK_YOU_PAGE_PATH =
      "/content/core-components-examples/library/button";

  private TestPage page;
  private FormContainerComponent component;
  private SoftAssertions softly;

  @BeforeEach
  public void setup() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH,
        SlingDataXMLBuilder
            .buildFromFile("testpages/core-components/formComponentsTestPage.xml")));
    page = bobcatPageFactory.create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    page.open();
  }

  @Test
  @DisplayName("\"Mail\" action type and it's properties")
  public void configureMailActionType() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Form Container (v2)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/form-container/mail.yaml")));
    component = page.getContent(FormContainerComponent.class, 0);
    softly = new SoftAssertions();
    softly.assertThat(component.isDisplayed()).as("Check if the form is displayed").isTrue();
    softly.assertThat(component.getThankYouPagePath())
        .as("Check if the \"Thank You\" page path is configured properly")
        .endsWith(THANK_YOU_PAGE_PATH + ".html");
    softly.assertAll();
  }

  @Test
  @DisplayName("\"Store Content\" action type and it's properties")
  public void configureStoreContentActionType() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Form Container (v2)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/form-container/store-content.yaml")));
    component = page.getContent(FormContainerComponent.class, 0);
    softly = new SoftAssertions();
    softly.assertThat(component.isDisplayed()).as("Check if the form is displayed").isTrue();
    softly.assertThat(component.getThankYouPagePath())
        .as("Check if the \"Thank You\" page path is configured properly")
        .endsWith(THANK_YOU_PAGE_PATH + ".html");
    softly.assertAll();

  }

  @AfterEach
  public void deleteTestPage() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
  }

}
