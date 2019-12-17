package com.cognifide.qa.bb.aem65.tests.corecomponents;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.formcomponents.FormTextComponent;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;
import com.google.inject.Inject;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

/**
 * These tests verify if Bobcat can handle the configuration of the Form Text Component
 * https://docs.adobe.com/content/help/en/experience-manager-core-components/using/components/forms/form-text.html
 */
@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("Form Text Component configuration")
@DisplayName("Author can configure for Form Text Component the...")
public class FormTextComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH =
      "/content/core-components-examples/library/form-components-test-page";

  private TestPage page;
  private FormTextComponent component;

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
  @DisplayName("basic properties")
  public void configureBasicProperties() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("Layout Container/Form Container (v2)", "Form Text (v2)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/form-components/form-text/properties.yaml")));
    component = page.getContent(FormTextComponent.class, 0);
    softly.assertThat(component.getConstraint())
        .as("Check if the constraint is correct")
        .isEqualTo("email");
    softly.assertThat(component.getAriaLabel())
        .as("Check if the label is correct")
        .isEqualTo("The label");
    softly.assertThat(component.getElementName())
        .as("Check if the element name is correct")
        .isEqualTo("The element name");
    softly.assertThat(component.getValue())
        .as("Check if the value is correct")
        .isEqualTo("The value");
    softly.assertAll();
  }

  @Test
  @DisplayName("about section")
  public void configureAboutSection() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("Layout Container/Form Container (v2)", "Form Text (v2)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/form-components/form-text/about.yaml")));
    component = page.getContent(FormTextComponent.class, 0);
    assertThat(component.getPlaceholder())
        .as("Check if the placeholder help message is correct")
        .isEqualTo("The help message");
  }

  @Test
  @DisplayName("read-only option")
  public void configureReadOnlyOption() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("Layout Container/Form Container (v2)", "Form Text (v2)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/form-components/form-text/read-only.yaml")));
    component = page.getContent(FormTextComponent.class, 0);
    assertThat(component.isReadOnly())
        .as("Check if the input is disabled")
        .isTrue();
  }

  @Test
  @DisplayName("required option")
  public void configureRequiredOption() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("Layout Container/Form Container (v2)", "Form Text (v2)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/form-components/form-text/required.yaml")));
    component = page.getContent(FormTextComponent.class, 0);
    softly.assertThat(component.getConstraintMessage())
        .as("Check if the constraint message is configured")
        .isEqualTo("The constraint message");
    softly.assertThat(component.getRequiredMessage())
        .as("Check if the \"required\" warning message is configured")
        .isEqualTo("The required message");
    softly.assertAll();
  }

  @AfterEach
  public void deleteTestPage() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
  }
}
