package com.cognifide.qa.bb.aem65.tests.corecomponents.list;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingDataXMLBuilder;
import com.cognifide.qa.bb.aem.core.pages.sling.SlingPageData;
import com.cognifide.qa.bb.aem65.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.aem65.tests.pageobjects.corecomponents.ListComponent;
import com.cognifide.qa.bb.aem65.tests.pages.TestPage;
import com.cognifide.qa.bb.api.actions.ActionException;

public abstract class AbstractListComponentTest extends AbstractAemAuthorTest {

  protected static final String LIST_ITEM_ROOT_PATH = "/content/we-retail/us/en/list-item-root/";
  protected static final String[] LIST_ITEM_PATHS = {
      "/content/we-retail/us/en/list-item-root/list-item-one",
      "/content/we-retail/us/en/list-item-root/list-item-two",
      "/content/we-retail/us/en/list-item-root/list-item-three",
      "/content/we-retail/us/en/list-item-root/list-item-three/list-item-four"};


  protected TestPage page;
  protected ListComponent component;

  @BeforeAll
  public void createTestPagesForListItems() throws ActionException {
    controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(LIST_ITEM_ROOT_PATH,
        SlingDataXMLBuilder
            .buildFromFile("testpages/core-components/list/listItemRootTestPage.xml")));
    for (int i = 0; i < LIST_ITEM_PATHS.length; i++) {
      controller.execute(AemActions.CREATE_PAGE_VIA_SLING, new SlingPageData(LIST_ITEM_PATHS[i],
          SlingDataXMLBuilder.buildFromFile(
              String.format("testpages/core-components/list/listItemTestPage%s.xml", i + 1))));
    }
  }

  @AfterAll
  public void deleteTestPagesForListItems() throws ActionException {
    controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(LIST_ITEM_ROOT_PATH));
    for (String path : LIST_ITEM_PATHS) {
      controller.execute(AemActions.DELETE_PAGE_VIA_SLING, new SlingPageData(path));
    }
  }
}
