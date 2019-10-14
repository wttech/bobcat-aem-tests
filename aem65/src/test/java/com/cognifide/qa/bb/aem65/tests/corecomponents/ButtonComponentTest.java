package com.cognifide.qa.bb.aem65.tests.corecomponents;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.aem.core.component.actions.ConfigureComponentData;
import com.cognifide.qa.bb.aem.core.component.configuration.ResourceFileLocation;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingDataXMLBuilder;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingPageData;
import com.cognifide.qa.bb.aem65.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.ButtonComponent;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * These tests verify if Bobcat can handle the configuration of the Button Component
 * https://opensource.adobe.com/aem-core-wcm-components/library/button.html
 */
@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("Button Component configuration")
@DisplayName("Author can configure for Button Component the...")
public class ButtonComponentTest extends AbstractAemAuthorTest {

    private static final String TEST_PAGE_PATH = "/content/core-components-examples/library/button-component-test-page";

    private TestPage page;
    private ButtonComponent component;

    @BeforeEach
    public void createAndOpenTestPage() throws ActionException {
        controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH,
                SlingDataXMLBuilder.buildFromFile(
                        "testpages/core-components/buttonComponentTestPage.xml")));
        page = bobcatPageFactory.create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
        page.open();
    }

    @Test
    @DisplayName("text")
    public void configureText() throws ActionException {
        controller.execute(AemActions.CONFIGURE_COMPONENT,
                new ConfigureComponentData("container", "Button (v1)", 0,
                        new ResourceFileLocation("component-configs/core-components/button/text.yaml")));
        component = page.getContent(ButtonComponent.class, 0);
        assertThat(component.getText())
                .as("Check if the text is configured")
                .isEqualTo("Custom button text");
    }

    @Test
    @DisplayName("link")
    public void configureLink() throws ActionException {
        controller.execute(AemActions.CONFIGURE_COMPONENT,
                new ConfigureComponentData("container", "Button (v1)", 0,
                        new ResourceFileLocation("component-configs/core-components/button/link.yaml")));
        component = page.getContent(ButtonComponent.class, 0);
        assertThat(component.getLink())
                .as("Check if the link is configured")
                .endsWith("/content/we-retail/us/en.html");
    }

    @Test
    @DisplayName("icon")
    public void configureIcon() throws ActionException {
        controller.execute(AemActions.CONFIGURE_COMPONENT,
                new ConfigureComponentData("container", "Button (v1)", 0,
                        new ResourceFileLocation("component-configs/core-components/button/icon.yaml")));
        component = page.getContent(ButtonComponent.class, 0);
        assertThat(component.getIconClasses())
                .as("Check if the icon is configured")
                .matches(".*cmp-button__icon--email.*");
    }

    @Test
    @DisplayName("aria label")
    public void configureLabel() throws ActionException {
        controller.execute(AemActions.CONFIGURE_COMPONENT,
                new ConfigureComponentData("container", "Button (v1)", 0,
                        new ResourceFileLocation("component-configs/core-components/button/label.yaml")));
        component = page.getContent(ButtonComponent.class, 0);
        assertThat(component.getAriaLabel())
                .as("Check if the label is configured")
                .isEqualTo("Custom aria label");
    }

    @AfterEach
    public void deleteTestPage() throws ActionException {
        controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
    }
}
