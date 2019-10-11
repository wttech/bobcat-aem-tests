package com.cognifide.qa.bb.aem65.tests.corecomponents;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.aem.core.component.actions.ConfigureComponentData;
import com.cognifide.qa.bb.aem.core.component.configuration.ResourceFileLocation;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingDataXMLBuilder;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingPageData;
import com.cognifide.qa.bb.aem65.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.teaser.TeaserComponent;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.teaser.TeaserComponentImpl;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;
import com.cognifide.qa.bb.wait.BobcatWait;
import com.google.inject.Inject;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("Teaser Component configuration")
@DisplayName("Author can configure for Teaser Component the...")


public class TeaserComponentTest extends AbstractAemAuthorTest {

    private static final String TEST_PAGE_PATH = "/content/we-retail/us/en/teaserComponentTestPage";

    private TestPage page;
    private TeaserComponentImpl component;

    @Inject
    private BobcatWait bobcatWait;

    @BeforeEach
    public void createAndOpenTestPage() throws ActionException {
        controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH,
                SlingDataXMLBuilder.buildFromFile("testpages/core-components/teaserComponentTestPage.xml")));
        page = bobcatPageFactory.create("/editor.html" + TEST_PAGE_PATH + ".html", TestPage.class);
        page.open();
    }

    @Test
    @DisplayName("content - check if content class is visible")
    public void configureAnyContent() throws ActionException {
        controller.execute(AemActions.CONFIGURE_COMPONENT,
                new ConfigureComponentData("container", "Teaser", 0,
                        new ResourceFileLocation("component-configs/core-components/teaser/title.yaml")));
        component = (TeaserComponentImpl) page.getContent(TeaserComponent.class,0);
        component.checkIfContentClassIsVisible();
    }

    @Test
    @DisplayName("image")
    public void configureImageTeaser() throws ActionException {
        controller.execute(AemActions.CONFIGURE_COMPONENT,
                new ConfigureComponentData("container", "Teaser", 0,
                        new ResourceFileLocation("component-configs/core-components/teaser/image.yaml")));
        component = (TeaserComponentImpl) page.getContent(TeaserComponent.class, 0);
        assertThat(component.getTeaserImage())
                .as("Check if image is configured")
                .matches("Gray lava rock formation");
    }

    @Test
    @DisplayName("title")
    public void configureTitle() throws ActionException {
        controller.execute(AemActions.CONFIGURE_COMPONENT,
                new ConfigureComponentData("container", "Teaser", 0,
                        new ResourceFileLocation("component-configs/core-components/teaser/title.yaml")));
        component = (TeaserComponentImpl) page.getContent(TeaserComponent.class,0);
        assertThat(component.getTeaserTitle().replaceAll("[\r\n]", "").replaceAll("^\\s+","").replaceFirst("\\s++$", ""))
                .as("Check if the title is configured")
                .matches("This is teaser title");
    }

    @Test
    @DisplayName("link on title")
    public void configureTitleLink() throws ActionException {
        controller.execute(AemActions.CONFIGURE_COMPONENT,
                new ConfigureComponentData("container", "Teaser", 0,
                        new ResourceFileLocation("component-configs/core-components/teaser/titleLink.yaml")));
        component = (TeaserComponentImpl) page.getContent(TeaserComponent.class, 0);
        assertThat(component.getTeaserTitleLink()).as("Check if the link is configured")
                .matches("https://cognifide.github.io/bobcat/");
    }


    @Test
    @DisplayName("title taken from linked page")
    public void configureTitleFromLinkedPage() throws ActionException {
        controller.execute(AemActions.CONFIGURE_COMPONENT,
                new ConfigureComponentData("container", "Teaser", 0,
                        new ResourceFileLocation("component-configs/core-components/teaser/titleFromLinkedPage.yaml")));
        component = (TeaserComponentImpl) page.getContent(TeaserComponent.class,0);
        assertThat(component.getTeaserTitleFromLinkedPage().replaceAll("[\r\n]", "").replaceAll("^\\s+","").replaceFirst("\\s++$", ""))
                .as("Check if the title is taken from linked page")
                .matches("Women");
    }

    @Test
    @DisplayName("description")
    public void configureDescription() throws ActionException {
        controller.execute(AemActions.CONFIGURE_COMPONENT,
                new ConfigureComponentData("container", "Teaser", 0,
                        new ResourceFileLocation("component-configs/core-components/teaser/description.yaml")));
        component = (TeaserComponentImpl) page.getContent(TeaserComponent.class,0);
        assertThat(component.getTeaserDescription().replaceAll("[\r\n]", "").replaceAll("^\\s+","").replaceFirst("\\s++$", ""))
                .as("Check if the descriotion is configured")
                .matches("This is teaser description<br>");
    }

    @Test
    @DisplayName("empty description taken from linked page")
    public void configureDescriptionFromLinkedPage() throws ActionException {
        controller.execute(AemActions.CONFIGURE_COMPONENT,
                new ConfigureComponentData("container", "Teaser", 0,
                        new ResourceFileLocation("component-configs/core-components/teaser/descriptionEmptyFromLinkedPage.yaml")));
        component = (TeaserComponentImpl) page.getContent(TeaserComponent.class,0);
        component.checkDescriptionVisibility();
    }

    @Test
    @DisplayName("description taken from linked page")
    public void configureDescriptionFromLinkedPage2() throws ActionException {
        controller.execute(AemActions.CONFIGURE_COMPONENT,
                new ConfigureComponentData("container", "Teaser", 0,
                        new ResourceFileLocation("component-configs/core-components/teaser/descriptionFromLinkedPage.yaml")));
        component = (TeaserComponentImpl) page.getContent(TeaserComponent.class,0);
        assertThat(component.getTeaserDescriptionFromLinkedPage())
                .as("Check if the description is taken from linked page")
                .matches("Test description from Properties");
    }

    @Test
    @DisplayName("one call to action button")
    public void configureOneCallToActionButton() throws ActionException {
        controller.execute(AemActions.CONFIGURE_COMPONENT,
                new ConfigureComponentData("container", "Teaser", 0,
                        new ResourceFileLocation("component-configs/core-components/teaser/oneCallToActionButton.yaml")));
    }

    @AfterEach
    public void deleteTestPage() throws ActionException {
        controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(TEST_PAGE_PATH));
    }
}
