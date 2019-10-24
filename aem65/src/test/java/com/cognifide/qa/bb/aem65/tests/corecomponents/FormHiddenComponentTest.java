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
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.formcomponents.FormHiddenComponent;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;
import com.google.inject.Inject;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

/**
 * These tests verify if Bobcat can handle the configuration of the Form Hidden Component
 * https://docs.adobe.com/content/help/en/experience-manager-core-components/using/components/forms/form-hidden.html
 */
@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("Form Hidden Component configuration")
@DisplayName("Author can configure for Form Hidden Component the...")
public class FormHiddenComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH =
      "/content/core-components-examples/library/form-components-test-page";

  private TestPage page;
  private FormHiddenComponent component;

  @Inject
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
  @DisplayName("all properties")
  public void configureAllProperties() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("Layout Container/Form Container (v2)", "Form Hidden (v2)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/form-components/form-hidden.yaml")));
    component = page.getContent(FormHiddenComponent.class, 0);
    softly.assertThat(component.getId())
        .as("Check if the id is correct")
        .isEqualTo("The identifier");
    softly.assertThat(component.getName())
        .as("Check if the name is correct")
        .isEqualTo("The name");
    softly.assertThat(component.getValue())
        .as("Check if the value is correct")
        .isEqualTo("The value");
    softly.assertAll();
  }

  @AfterEach
  public void deleteTestPage() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
  }
}
