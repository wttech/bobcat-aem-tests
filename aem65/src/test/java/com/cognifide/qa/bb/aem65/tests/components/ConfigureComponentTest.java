package com.cognifide.qa.bb.aem65.tests.components;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import com.cognifide.qa.bb.aem65.tests.pageobjects.TextComponent;
import com.cognifide.qa.bb.aem65.tests.pageobjects.TextComponentImpl;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Modules(BobcatRunModule.class)
@Epic("AEM 6.5 authoring tests")
@Feature("Component configuration")
@DisplayName("Author can...")
class ConfigureComponentTest extends AbstractAemAuthorTest {

  private static final String TEST_PAGE_PATH = "/content/we-retail/us/en/editcomponenttestpage";

  private static final String PAGE_TO_CREATE_TITLE = "testPage";

  private TestPage testPage;

  @BeforeEach
  void createAndOpenTestPage() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING,
        new SlingPageData(TEST_PAGE_PATH,
            SlingDataXMLBuilder.buildFromFile("testpages/editComponentTestPage.xml")));

    testPage = bobcatPageFactory.create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
    testPage.setTitle(PAGE_TO_CREATE_TITLE);
    assertTrue(testPage.open().isDisplayed());
  }

  @Test
  @DisplayName("configure image field")
  void configureImage() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Hero Image", 0,
            new ResourceFileLocation("component-configs/hero/image.yaml")));
  }

  @Test
  @DisplayName("configure checkbox field")
  void configureCheckbox() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container/container", "List", 0,
            new ResourceFileLocation("component-configs/list/checkbox.yaml")));
  }

  @Test
  @DisplayName("configure textfield field")
  void configureTextfield() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container/container", "Link Button", 0,
            new ResourceFileLocation("component-configs/button/textfield.yaml")));
  }

  @Test
  @DisplayName("configure multifield")
  void configureMultifield() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container/container", "Form Options", 0,
            new ResourceFileLocation("component-configs/formoptions/multifield.yaml")));
  }

  @Test
  @DisplayName("configure path browser field")
  void configurePathBrowser() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container/container", "Content Fragment", 0,
            new ResourceFileLocation("component-configs/contentfragment/pathbrowser.yaml")));
  }

  @Test
  @DisplayName("configure radio group field")
  void configureRadioGroup() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container/container", "Content Fragment", 0,
            new ResourceFileLocation("component-configs/contentfragment/radio-group.yaml")));
  }

  @Test
  @DisplayName("configure select field")
  void configureSelect() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container/container", "List", 0,
            new ResourceFileLocation("component-configs/list/select.yaml")));
  }

  @Test
  @DisplayName("configure rich text field")
  void configureRte() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Text", 0,
            new ResourceFileLocation("component-configs/text/rte.yaml")));
    TextComponentImpl content = (TextComponentImpl) testPage.getContent(TextComponent.class, 0);

    assertThat(content.getInnerHTML().trim().replaceAll("\\r|\\n", ""))
        .matches("<p>test test test<br></p>");
  }

  @Test
  @DisplayName("configure rich text options: bold")
  void configureRteBold() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Text", 0,
            new ResourceFileLocation("component-configs/text/rte-bold.yaml")));
    TextComponentImpl content = (TextComponentImpl) testPage.getContent(TextComponent.class, 0);
    assertThat(content.getInnerHTML().trim().replaceAll("\\r|\\n", ""))
        .matches("<p><b>test test test<br></b></p>");
  }

  @Test
  @DisplayName("configure rich text options: italic")
  void configureRteItalic() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Text", 0,
            new ResourceFileLocation("component-configs/text/rte-italic.yaml")));
    TextComponentImpl content = (TextComponentImpl) testPage.getContent(TextComponent.class, 0);
    assertThat(content.getInnerHTML().trim().replaceAll("\\r|\\n", ""))
        .matches("<p><i>test test test<br></i></p>");
  }

  @Test
  @DisplayName("configure rich text options: lists - bullet list")
  void configureRteListBullet() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Text", 0,
            new ResourceFileLocation("component-configs/text/rte-list-bullet.yaml")));
    TextComponentImpl content = (TextComponentImpl) testPage.getContent(TextComponent.class, 0);
    assertThat(content.getInnerHTML().trim().replaceAll("\\r|\\n", ""))
        .matches("<ul><li>test test test.*</li></ul>");
  }

  @Test
  @DisplayName("configure rich text options: lists - numbered list")
  void configureRteListNumbered() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Text", 0,
            new ResourceFileLocation("component-configs/text/rte-list-numbered.yaml")));
    TextComponentImpl content = (TextComponentImpl) testPage.getContent(TextComponent.class, 0);
    assertThat(content.getInnerHTML().trim().replaceAll("\\r|\\n", ""))
        .matches("<ol><li>test test test.*</li></ol>");
  }

  @Test
  @DisplayName("configure rich text options: lists - indent")
  void configureRteListIndent() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Text", 0,
            new ResourceFileLocation("component-configs/text/rte-list-indent.yaml")));
    TextComponentImpl content = (TextComponentImpl) testPage.getContent(TextComponent.class, 0);
    assertThat(content.getInnerHTML().trim().replaceAll("\\r|\\n", ""))
        .matches("<p style=\"margin-left: 40.0px;\">test test test<br></p>");
  }

  @Test
  @DisplayName("configure rich text options: lists - outdent")
  void configureRteListOutdent() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container/container", "Text", 0,
            new ResourceFileLocation("component-configs/text/rte-list-outdent.yaml")));
    TextComponentImpl content = (TextComponentImpl) testPage.getContent(TextComponent.class, 1);
    assertThat(content.getInnerHTML().trim().replaceAll("\\r|\\n", ""))
        .matches("<p>test test test.*<br></p>");
  }

  @Test
  @DisplayName("configure rich text options: hyperlink")
  void configureRteHyperlink() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Text", 0,
            new ResourceFileLocation("component-configs/text/rte-hyperlink.yaml")));
    TextComponentImpl content = (TextComponentImpl) testPage.getContent(TextComponent.class, 0);
    assertThat(content.getInnerHTML().trim().replaceAll("\\r|\\n", ""))
        .matches(
            "<p><a title=\"alternative text\" href=\"#\" target=\"_blank\">test test test<br></a></p>");
  }

  @Test
  @DisplayName("configure rich text options: unlink")
  void configureRteUnlink() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container/container", "Text", 1,
            new ResourceFileLocation("component-configs/text/rte-unlink.yaml")));
    TextComponentImpl content = (TextComponentImpl) testPage.getContent(TextComponent.class, 2);
    assertThat(content.getInnerHTML().trim().replaceAll("\\r|\\n", ""))
        .matches("<p>test test test<br></p>");
  }

  @Test
  @DisplayName("configure rich text options: paragraph formats")
  void configureRteParagraphFormats() throws ActionException {
    controller.execute(AemActions.CONFIGURE_COMPONENT,
        new ConfigureComponentData("container", "Text", 0,
            new ResourceFileLocation("component-configs/text/rte-paraformats.yaml")));
    TextComponentImpl content = (TextComponentImpl) testPage.getContent(TextComponent.class, 0);
    assertThat(content.getInnerHTML().trim().replaceAll("\\r|\\n", ""))
        .matches("<h1>test test test<br></h1>");
  }

  @AfterEach
  void deleteTestPage() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
  }
}

