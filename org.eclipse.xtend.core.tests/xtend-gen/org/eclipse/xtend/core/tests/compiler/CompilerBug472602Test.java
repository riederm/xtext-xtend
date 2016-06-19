/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtend.core.tests.compiler;

import org.eclipse.xtend.core.tests.compiler.AbstractXtendCompilerTest;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@SuppressWarnings("all")
public class CompilerBug472602Test extends AbstractXtendCompilerTest {
  @Ignore
  @Test
  public void test_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import com.google.common.base.Function");
    _builder.newLine();
    _builder.newLine();
    _builder.append("abstract class C<A> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def A get();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <B> C<B> map() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("[|");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("val A a = get");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("null");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public abstract class C<A extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public abstract A get();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <B extends Object> C<B> map() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C<B> _function = new C<B>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("@Override");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public B get() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("Object _xblockexpression = null;");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("{");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("final A a = C.this.get();");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("_xblockexpression = null;");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return ((B)_xblockexpression);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return _function;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import com.google.common.base.Function");
    _builder.newLine();
    _builder.newLine();
    _builder.append("abstract class C<A> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def A get();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <B> C<B> map() {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val A a = this.get");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("null");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public abstract class C<A extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public abstract A get();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <B extends Object> C<B> map() {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("Object _xblockexpression = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("{");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final A a = this.get();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_xblockexpression = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return ((C<B>)_xblockexpression);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Ignore
  @Test
  public void test_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import com.google.common.base.Function");
    _builder.newLine();
    _builder.newLine();
    _builder.append("abstract class C<A> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def A get();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <B> C<B> map(Function<A, B> f) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("[|");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("val A a = get");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("val B b = f.apply(a)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return b");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Function;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public abstract class C<A extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public abstract A get();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <B extends Object> C<B> map(final Function<A, B> f) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C<B> _function = new C<B>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("@Override");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public B get() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("final A a = C.this.get();");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("final B b = f.apply(a);");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return b;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return _function;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Ignore
  @Test
  public void test_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import com.google.common.base.Function");
    _builder.newLine();
    _builder.newLine();
    _builder.append("abstract class C<A> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def A get();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <B> C<B> map(Function<A, B> f) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("[|");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("get // must compile to C.this.gt");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return self.get // must compile to this.get");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Function;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public abstract class C<A extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public abstract A get();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <B extends Object> C<B> map(final Function<A, B> f) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C<B> _function = new C<B>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("@Override");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public B get() {");
    _builder_1.newLine();
    _builder_1.append("      \t");
    _builder_1.append("C.this.get();");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return this.get();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return _function;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Ignore
  @Test
  public void test_05() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import com.google.common.base.Function");
    _builder.newLine();
    _builder.newLine();
    _builder.append("abstract class C<A> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def A get();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <B> C<B> map(Function<A, B> f) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new C<B>() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("override get() {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("val A a = C.this.get");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("val B b = f.apply(a)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return b");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Function;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public abstract class C<A extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public abstract A get();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <B extends Object> C<B> map(final Function<A, B> f) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C<B> _function = new C<B>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("@Override");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public B get() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("final A a = C.this.get();");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("final B b = f.apply(a);");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("B _elvis = null;");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("if (b != null) {");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("_elvis = b;");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("} else {");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("B _get = C.this.get();");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("_elvis = _get;");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return _elvis;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return _function;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_06() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import com.google.common.base.Function");
    _builder.newLine();
    _builder.newLine();
    _builder.append("abstract class C<A> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def A get();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <B> C<B> map(Function<A, B> f) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new MapResult(this, f)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("static class MapResult<A, B> extends C<B> {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("C<A> in");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("Function<A, B> f");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new (C<A> in, Function<A, B> f) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("this.in = in");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("this.f = f");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("override get() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("val A a = in.get");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("val B b = f.apply(a)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return b");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Function;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public abstract class C<A extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public static class MapResult<A extends Object, B extends Object> extends C<B> {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("private C<A> in;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("private Function<A, B> f;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public MapResult(final C<A> in, final Function<A, B> f) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("this.in = in;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("this.f = f;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("public B get() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final A a = this.in.get();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final B b = this.f.apply(a);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return b;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public abstract A get();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <B extends Object> C<B> map(final Function<A, B> f) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return new C.MapResult<A, B>(this, f);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_07() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import com.google.common.base.Function");
    _builder.newLine();
    _builder.newLine();
    _builder.append("abstract class C<A> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def A get();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <B> C<B> map(Function<A, B> f) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val thiz = this");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("new C<B>() {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("override get() {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("val A a = thiz.get");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("val B b = f.apply(a)");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return b");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Function;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public abstract class C<A extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public abstract A get();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <B extends Object> C<B> map(final Function<A, B> f) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("C<B> _xblockexpression = null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("{");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("final C<A> thiz = this;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_xblockexpression = new C<B>() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("public B get() {");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("final A a = thiz.get();");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("final B b = f.apply(a);");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("return b;");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return _xblockexpression;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
  
  @Test
  public void test_08() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("import com.google.common.base.Function");
    _builder.newLine();
    _builder.newLine();
    _builder.append("abstract class C<A> {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def A get();");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def <B> C<B> map(Function<A, B> f) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val thiz = this");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("return [");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("val A a = thiz.get");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("val B b = f.apply(a)");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return b");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("]");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Function;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public abstract class C<A extends Object> {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public abstract A get();");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public <B extends Object> C<B> map(final Function<A, B> f) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C<A> thiz = this;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final C<B> _function = new C<B>() {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("@Override");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("public B get() {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("final A a = thiz.get();");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("final B b = f.apply(a);");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return b;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return _function;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}