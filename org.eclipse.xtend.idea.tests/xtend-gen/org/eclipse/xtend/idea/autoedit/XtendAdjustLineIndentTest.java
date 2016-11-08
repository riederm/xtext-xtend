/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.idea.autoedit;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import org.eclipse.xtend.core.idea.lang.XtendFileType;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.idea.tests.LibraryUtil;
import org.eclipse.xtext.xbase.idea.formatting.AbstractXbaseAdjustLineIndentTest;

/**
 * @author kosyakov - Initial contribution and API
 */
@SuppressWarnings("all")
public class XtendAdjustLineIndentTest extends AbstractXbaseAdjustLineIndentTest {
  public XtendAdjustLineIndentTest() {
    super(XtendFileType.INSTANCE);
  }
  
  @Override
  protected void configureModule(final Module module, final ModifiableRootModel model, final ContentEntry contentEntry) {
    LibraryUtil.addXtendLibrary(model);
  }
  
  public void testXAssignment_05() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int a");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.a = 3");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class Foo {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("int a");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def foo() {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("this.a = 3");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertAdjustLineIndent(_builder, _builder_1.toString(), false);
  }
  
  public void testXAssignment_06() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int a");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("this.a");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("=");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("3");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class Foo {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("int a");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def foo() {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("this.a");
    _builder_1.newLine();
    _builder_1.append("\t\t\t\t");
    _builder_1.append("=");
    _builder_1.newLine();
    _builder_1.append("\t\t\t\t");
    _builder_1.append("3");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertAdjustLineIndent(_builder, _builder_1.toString(), false);
  }
  
  public void testXFeatureCall_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("values.head");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def java.util.List<String> values() { null }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class Foo {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def foo() {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("values.head");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def java.util.List<String> values() { null }");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertAdjustLineIndent(_builder, _builder_1.toString(), false);
  }
  
  public void testXFeatureCall_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("values");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(".");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("head");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def java.util.List<String> values() { null }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class Foo {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def foo() {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("values");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append(".");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("head");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def java.util.List<String> values() { null }");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertAdjustLineIndent(_builder, _builder_1.toString(), false);
  }
  
  public void testXFeatureCall_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("values.get(0)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def java.util.List<String> values() { null }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class Foo {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def foo() {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("values.get(0)");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def java.util.List<String> values() { null }");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertAdjustLineIndent(_builder, _builder_1.toString(), false);
  }
  
  public void testXFeatureCall_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("values.get(");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def java.util.List<String> values() { null }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class Foo {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def foo() {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("values.get(");
    _builder_1.newLine();
    _builder_1.append("\t\t\t\t");
    _builder_1.append("0");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append(")");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def java.util.List<String> values() { null }");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertAdjustLineIndent(_builder, _builder_1.toString(), false);
  }
  
  public void testXFeatureCall_05() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("values");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(".");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("get");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("(");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def java.util.List<String> values() { null }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class Foo {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def foo() {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("values");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append(".");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("get");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("(");
    _builder_1.newLine();
    _builder_1.append("\t\t\t\t");
    _builder_1.append("0");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append(")");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def java.util.List<String> values() { null }");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertAdjustLineIndent(_builder, _builder_1.toString(), false);
  }
  
  public void testXFeatureCall_06() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("values");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(".");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("get(");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def java.util.List<String> values() { null }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class Foo {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def foo() {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("values");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append(".");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("get(");
    _builder_1.newLine();
    _builder_1.append("\t\t\t\t");
    _builder_1.append("0");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append(")");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def java.util.List<String> values() { null }");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertAdjustLineIndent(_builder, _builder_1.toString(), false);
  }
  
  public void testXConstructorCall_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Foo");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class Foo {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def foo() {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("new");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("Foo");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertAdjustLineIndent(_builder, _builder_1.toString(), false);
  }
  
  public void testXConstructorCall_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new Foo(0)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(int x) {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class Foo {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def foo() {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("new Foo(0)");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("new(int x) {}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertAdjustLineIndent(_builder, _builder_1.toString(), false);
  }
  
  public void testXConstructorCall_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new Foo(");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(int x) {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class Foo {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def foo() {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("new Foo(");
    _builder_1.newLine();
    _builder_1.append("\t\t\t\t");
    _builder_1.append("0");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append(")");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("new(int x) {}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertAdjustLineIndent(_builder, _builder_1.toString(), false);
  }
  
  public void testXConstructorCall_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Foo");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("(");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(int x) {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class Foo {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def foo() {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("new");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("Foo");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("(");
    _builder_1.newLine();
    _builder_1.append("\t\t\t\t");
    _builder_1.append("0");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append(")");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("new(int x) {}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertAdjustLineIndent(_builder, _builder_1.toString(), false);
  }
  
  public void testXConstructorCall_05() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class Foo {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def foo() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Foo(");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("0");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append(")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\t");
    _builder.append("new(int x) {}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("class Foo {");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("def foo() {");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("new");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append("Foo(");
    _builder_1.newLine();
    _builder_1.append("\t\t\t\t");
    _builder_1.append("0");
    _builder_1.newLine();
    _builder_1.append("\t\t");
    _builder_1.append(")");
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("\t");
    _builder_1.append("new(int x) {}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertAdjustLineIndent(_builder, _builder_1.toString(), false);
  }
  
  protected void assertAdjustLineIndent(final CharSequence model, final String expectedAdjustedModel, final boolean wrap) {
    if (wrap) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("def foo() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append(model, "\t\t");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("class Foo {");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("def foo() {");
      _builder_1.newLine();
      _builder_1.append("\t\t");
      _builder_1.append(expectedAdjustedModel, "\t\t");
      _builder_1.newLineIfNotEmpty();
      _builder_1.append("\t");
      _builder_1.append("}");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      super.assertAdjustLineIndent(_builder, _builder_1.toString());
    } else {
      super.assertAdjustLineIndent(model, expectedAdjustedModel);
    }
  }
  
  @Override
  protected void assertAdjustLineIndent(final CharSequence model, final String expectedAdjustedModel) {
    this.assertAdjustLineIndent(model, expectedAdjustedModel, true);
  }
}