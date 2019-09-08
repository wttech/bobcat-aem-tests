package com.cognifide.qa.bb.aem65.tests.corecomponents;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cognifide.qa.bb.aem65.tests.AbstractAemAuthorTest;
import com.cognifide.qa.bb.junit5.guice.Modules;
import com.cognifide.qa.bb.modules.BobcatRunModule;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;

@Modules(BobcatRunModule.class)
@Epic("Core Components authoring tests")
@Feature("List Component configuration")
@DisplayName("Author can configure for the List Component the...")
public class ListComponentTest extends AbstractAemAuthorTest {

  @BeforeEach
  public void prepareTestDataAndOpenTestPage() {

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

  @Test
  @DisplayName("ordering and sorting schema")
  public void configureOrderingAndSortingSchema() {

  }

  @Test
  @DisplayName("max items number")
  public void configureMaxItemsNumber() {
  }

  @Test
  @DisplayName("item settings")
  public void configureItemSettings() {

  }

  @AfterEach
  public void deleteTestData() {

  }
}
