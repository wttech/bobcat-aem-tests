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
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.formcomponents.FormOptionsComponent;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;
import com.google.inject.Inject;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

/**
 * These tests verify if Bobcat can handle the configuration of the Form Options Component
 * https://docs.adobe.com/content/help/en/experience-manager-core-components/using/components/forms/form-options.html
 */
@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("Form Options Component configuration")
@DisplayName("Author can configure for Form Options Component the...")
public class FormOptionsComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH =
      "/content/core-components-examples/library/form-components-test-page";

  private TestPage page;
  private FormOptionsComponent component;

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
  public void configureBasic() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("Layout Container/Form Container (v2)", "Form Options (v2)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/form-components/form-options/basic.yaml")));
    component = page.getContent(FormOptionsComponent.class, 0);
    softly.assertThat(component.getTitle())
        .as("Check if the title is correct")
        .isEqualTo("The title");
    softly.assertThat(component.getHelpMessage())
        .as("Check if the help message is correct")
        .isEqualTo("The help message");
    softly.assertThat(component.getFieldNames())
        .as("Check if the field names are correct")
        .containsOnly("The name");
    softly.assertAll();
  }

  @Test
  @DisplayName("options")
  public void configureOptions() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("Layout Container/Form Container (v2)", "Form Options (v2)", 0,
            new ResourceFileLocation(
                "component-configs/core-components/form-components/form-options/options.yaml")));
    component = page.getContent(FormOptionsComponent.class, 0);
    softly.assertThat(component.getFieldTypes())
        .as("Check if the field types are correct")
        .containsOnly("checkbox");
    softly.assertThat(component.getFieldTexts())
        .as("Check if the field texts are correct")
        .containsExactly("Text 1", "Text 2", "Text 3");
    softly.assertThat(component.getFieldValues())
        .as("Check if the field values are correct")
        .containsExactly("Value 1", "Value 2", "Value 3");
//    TODO: These two tests below keep failing because BB keeps selecting/deselecting checkboxes for the first multifield item instead of editing different multifield item checkboxes
    softly.assertThat(component.isFieldSelected(0))
        .as("Check if the first field is selected")
        .isTrue();
    softly.assertThat(component.isFieldEnabled(1))
        .as("Check if the second field is disabled")
        .isFalse();
    softly.assertAll();
  }

  @AfterEach
  public void deleteTestPage() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
  }
}
