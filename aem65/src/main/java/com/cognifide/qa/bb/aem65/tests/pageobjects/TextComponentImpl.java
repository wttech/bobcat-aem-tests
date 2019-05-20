/*-
 * #%L
 * Bobcat
 * %%
 * Copyright (C) 2016 Cognifide Ltd.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.cognifide.qa.bb.aem65.tests.pageobjects;

import com.cognifide.qa.bb.constants.HtmlTags;
import com.cognifide.qa.bb.constants.HtmlTags.Properties;
import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;
import org.openqa.selenium.WebElement;

@PageObject(css = ".cmp-text")
public class TextComponentImpl implements TextComponent {

  @Inject
  @CurrentScope
  private WebElement component;

  public String getInnerHTML() {
    return component.getAttribute(Properties.INNER_HTML);
  }

  public String getCssClassNameProperty() {
    return component.getAttribute(HtmlTags.Properties.CLASS_NAME);
  }

}
