/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests.typesystem

import com.google.inject.Inject
import java.io.Serializable
import java.util.Collection
import java.util.Collections
import java.util.List
import org.eclipse.xtend.core.jvmmodel.IXtendJvmAssociations
import org.eclipse.xtext.xbase.lib.Pair
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference
import org.junit.Test

import static org.junit.Assert.*

/**
 * @author Sebastian Zarnekow
 */
abstract class AbstractSuperTypeTest extends AbstractTestingTypeReferenceOwner {
	
	@Inject
	extension IXtendJvmAssociations
	
	def assertSuperTypes(Class<?> type, String... superTypes) {
		(type.canonicalName->null).assertSuperTypes(superTypes)
	}
	
	def assertSuperTypes(String type, String... superTypes) {
		(type->null).assertSuperTypes(superTypes)
	}
	
	def assertSuperTypes(Pair<String, String> type, String... superTypes) {
		// TODO synthesize unique variable names as soon as the function should be validated
		val signature = '''def �IF !type.value.nullOrEmpty�<�type.value�> �ENDIF�void method(�type.key� type) {}'''
		val function = function(signature.toString)
		val operation = function.directlyInferredOperation
		val subtype = operation.parameters.head.parameterType.toLightweightReference
		assertEquals(superTypes.toList, subtype.collectSuperTypes.map[ simpleName ].toList as Object)
	}
	
	def Iterable<LightweightTypeReference> collectSuperTypes(LightweightTypeReference reference)
	
	@Test
	def void testObject() {
		"Object".assertSuperTypes()
	}
	
	@Test
	def void testSerializable() {
		typeof(Serializable).assertSuperTypes("Object")
	}
	
	@Test
	def void testString()
	
	@Test
	def void testCollections() {
		typeof(Collections).assertSuperTypes("Object")
	}
	
	@Test
	def void testRawCollection()
	
	@Test
	def void testStringCollection()
	
	@Test
	def void testStringArray()
	
	@Test
	def void testObjectArray()
	
	@Test
	def void testPrimitiveArray()
	
	@Test
	def void testRawList()
	
	@Test
	def void testStringList()
	
	@Test
	def void testStringArrayArrayList()
	
	@Test
	def void testTypeParameters()
	
	@Test
	def void testTypeParameterArray()
	
	@Test
	def void testDependentTypeParameters()
	
	@Test
	def void testDependentTypeParametersWithBounds()
	
	@Test
	def testTypeParametersWithoutUpperBound() {
		("T"->"T").assertSuperTypes("Object")
	}
	
}

/**
 * @author Sebastian Zarnekow
 */
class SuperTypeTest extends AbstractSuperTypeTest {
	
	override collectSuperTypes(LightweightTypeReference reference) {
		reference.superTypes
	}
	
	@Test
	override void testString() {
		typeof(String).assertSuperTypes("Serializable", "Comparable<String>", "CharSequence")
	}
	
	@Test
	override void testRawCollection() {
		typeof(Collection).assertSuperTypes("Iterable")
	}
	
	@Test
	override void testStringCollection() {
		"java.util.Collection<String>".assertSuperTypes("Iterable<String>")
	}
	
	@Test
	override void testStringArray() {
		"String[]".assertSuperTypes("Serializable[]", "Comparable<String>[]", "CharSequence[]")
	}
	
	@Test
	override void testObjectArray() {
		"Object[]".assertSuperTypes("Cloneable", "Serializable")
	}
	
	@Test
	override void testPrimitiveArray() {
		"int[]".assertSuperTypes("Cloneable", "Serializable")
	}
	
	@Test
	override void testRawList() {
		typeof(List).assertSuperTypes("Collection")
	}
	
	@Test
	override void testStringList() {
		"java.util.List<String>".assertSuperTypes("Collection<String>")
	}
	
	@Test
	override void testStringArrayArrayList() {
		"java.util.ArrayList<? extends String[]>".assertSuperTypes(
			"AbstractList<? extends String[]>", 
			"List<? extends String[]>",
			"RandomAccess",
			"Cloneable",
			"Serializable" )
	}
	
	override testTypeParameters() {
		("T"->"T extends CharSequence").assertSuperTypes("CharSequence")
	}
	
	override testTypeParameterArray() {
		("T"->"T extends CharSequence[]").assertSuperTypes("CharSequence[]")
	}
	
	override testDependentTypeParameters() {
		("T"->"V, T extends V").assertSuperTypes("V")
	}
	
	override testDependentTypeParametersWithBounds() {
		("T"->"V extends CharSequence, T extends V").assertSuperTypes("V")
	}
	
}

/**
 * @author Sebastian Zarnekow
 */
class AllSuperTypeTest extends AbstractSuperTypeTest {
	
	override collectSuperTypes(LightweightTypeReference reference) {
		reference.allSuperTypes
	}
	
	@Test
	override void testString() {
		typeof(String).assertSuperTypes("Serializable", "Comparable<String>", "CharSequence", "Object")
	}
	
	@Test
	override void testRawCollection() {
		typeof(Collection).assertSuperTypes("Iterable", "Object")
	}
	
	@Test
	override void testStringCollection() {
		"java.util.Collection<String>".assertSuperTypes("Iterable<String>", "Object")
	}
	
	@Test
	override void testStringArray() {
		"String[]".assertSuperTypes("Serializable[]", "Comparable<String>[]", "CharSequence[]", "Object[]", "Cloneable", "Serializable", "Object")
	}
	
	@Test
	override void testObjectArray() {
		"Object[]".assertSuperTypes("Cloneable", "Serializable", "Object")
	}
	
	@Test
	override void testPrimitiveArray() {
		"int[]".assertSuperTypes("Cloneable", "Serializable", "Object")
	}
	
	@Test
	override void testRawList() {
		typeof(List).assertSuperTypes("Collection", "Iterable", "Object")
	}
	
	@Test
	override void testStringList() {
		"java.util.List<String>".assertSuperTypes("Collection<String>", "Iterable<String>", "Object")
	}
	
	@Test
	override void testStringArrayArrayList() {
		"java.util.ArrayList<? extends String[]>".assertSuperTypes(
			"AbstractList<? extends String[]>", 
			"RandomAccess",
			"Cloneable",
			"Serializable",
			"AbstractCollection<? extends String[]>", 
			"List<? extends String[]>",
			"Collection<? extends String[]>",
			"Iterable<? extends String[]>",
			"Object")
	}
	
	override testTypeParameters() {
		("T"->"T extends CharSequence").assertSuperTypes("CharSequence", "Object")
	}
	
	override testDependentTypeParameters() {
		("T"->"V, T extends V").assertSuperTypes("V", "Object")
	}
	
	override testDependentTypeParametersWithBounds() {
		("T"->"V extends CharSequence, T extends V").assertSuperTypes("V", "CharSequence", "Object")
	}

	override testTypeParameterArray() {
		("T"->"T extends Object[]").assertSuperTypes("Object[]", "Cloneable", "Serializable", "Object")
	}
	
}

/* 
package org.eclipse.xtext.common.types.util;

	@Before
	public void setUp() throws Exception {
		Injector injector = Guice.createInjector(getModule());
		injector.injectMembers(this);
		typeProviderFactory.findOrCreateTypeProvider(resourceSet);
		syntheticResource = new XMLResourceImpl(URI.createURI("http://synthetic.resource"));
		resourceSet.getResources().add(syntheticResource);
		final TypeConformanceComputer delegate = computer;
		computer = new TypeConformanceComputer() {
			@Override
			public boolean isConformant(JvmTypeReference left, JvmTypeReference right, boolean ignoreGenerics) {
				assertTrue(delegate.isConformant(left, left, true));
				assertTrue(delegate.isConformant(left, left, false));
				assertTrue(delegate.isConformant(right, right, true));
				assertTrue(delegate.isConformant(right, right, false));
				assertTrue(delegate.isConformant(left, EcoreUtil2.clone(left), true));
				assertTrue(delegate.isConformant(left, EcoreUtil2.clone(left), false));
				assertTrue(delegate.isConformant(right, EcoreUtil2.clone(right), true));
				assertTrue(delegate.isConformant(right, EcoreUtil2.clone(right), false));
				return delegate.isConformant(left, right, ignoreGenerics);
			}
			
			@Override
			public JvmTypeReference getCommonSuperType(List<JvmTypeReference> types) {
				return delegate.getCommonSuperType(types);
			}
		};
	}
	
	@Test public void testPrimitiveConversion_00() throws Exception {
		JvmTypeReference left = ref(Long.TYPE);
		JvmTypeReference right = ref(Integer.TYPE);
		assertTrue(getComputer().isConformant(left, right, true));
		assertTrue(getComputer().isConformant(left, right, false));
	}
	@Test public void testPrimitiveConversion_01() throws Exception {
		JvmTypeReference left = ref(Integer.TYPE);
		JvmTypeReference right = ref(Long.TYPE);
		assertFalse(getComputer().isConformant(left, right, true));
		assertFalse(getComputer().isConformant(left, right, false));
	}
	@Test public void testPrimitiveConversion_02() throws Exception {
		JvmTypeReference left = ref(Long.TYPE);
		JvmTypeReference right = ref(Byte.TYPE);
		assertTrue(getComputer().isConformant(left, right, true));
		assertTrue(getComputer().isConformant(left, right, false));
	}
	@Test public void testPrimitiveConversion_03() throws Exception {
		JvmTypeReference left = ref(Character.TYPE);
		JvmTypeReference right = ref(Byte.TYPE);
		assertTrue(getComputer().isConformant(left, right, true));
		assertTrue(getComputer().isConformant(left, right, false));
	}
	@Test public void testPrimitiveConversion_04() throws Exception {
		JvmTypeReference left = ref(Integer.TYPE);
		JvmTypeReference right = ref(Integer.TYPE);
		assertTrue(getComputer().isConformant(left, right, true));
		assertTrue(getComputer().isConformant(left, right, false));
	}
	@Test public void testPrimitiveConversion_05() throws Exception {
		JvmTypeReference left = ref(Long.TYPE);
		JvmTypeReference right = ref(Float.TYPE);
		assertFalse(getComputer().isConformant(left, right, true));
		assertFalse(getComputer().isConformant(left, right, false));
	}
	@Test public void testPrimitiveConversion_06() throws Exception {
		JvmTypeReference left = ref(Double.TYPE);
		JvmTypeReference right = ref(Float.TYPE);
		assertTrue(getComputer().isConformant(left, right, true));
		assertTrue(getComputer().isConformant(left, right, false));
	}
	
	@Test public void testWildCardWithDefaultUpper() throws Exception {
//		Iterable<? extends Object> iterableOfObject = null;
//		List<?> list = null;
//		iterableOfObject = list;
		JvmTypeReference left = ref(Iterable.class, wc_extends(ref(Object.class)));
		JvmTypeReference right = ref(List.class, wc());
		assertTrue(getComputer().isConformant(left, right, true));
		assertTrue(getComputer().isConformant(left, right, false));
	}

	@Test public void testIgnoreGenrics_00() throws Exception {
		JvmTypeReference list = ref(List.class, wc_super(ref(Integer.class)));
		JvmTypeReference iterable = ref(Iterable.class, wc_super(ref(String.class)));
		assertTrue(getComputer().isConformant(iterable, list, true));
		assertFalse(getComputer().isConformant(iterable, list, false));
	}
	@Test public void testIgnoreGenrics_01() throws Exception {
		JvmTypeReference list = ref(List.class, wc_super(ref(Integer.class)));
		JvmTypeReference list2 = ref(List.class, wc_super(ref(String.class)));
		assertTrue(getComputer().isConformant(list2, list, true));
		assertFalse(getComputer().isConformant(list2, list, false));
	}
	@Test public void testIgnoreGenrics_02() throws Exception {
		JvmTypeReference left = ref(CharSequence.class);
		JvmTypeReference right = ref(List.class, wc_super(ref(Integer.class)));
		assertFalse(getComputer().isConformant(left, right, true));
		assertFalse(getComputer().isConformant(left, right, false));
	}
	@Test public void testIgnoreGenrics_03() throws Exception {
		JvmTypeReference left = ref(Integer.TYPE);
		JvmTypeReference right = ref(Long.TYPE);
		assertFalse(getComputer().isConformant(left, right, false));
		assertFalse(getComputer().isConformant(left, right, true));
	}
	
	/**
	 * List <= List<? super CharSequence>
	 * and
	 * List<? super CharSequence> <= List
	 
	@Test public void testRawTypes_super() throws Exception {
		JvmTypeReference rawList = ref(List.class);
		JvmTypeReference List_of_super_String = ref(List.class, wc_super(ref(String.class)));
		assertTrue(getComputer().isConformant(rawList, List_of_super_String));
		assertTrue(getComputer().isConformant(List_of_super_String, rawList));
	}
	
	/**
	 * List<String> <= List
	 * and
	 * List <= List<String>
	 
	@Test public void testRawtype_generics() throws Exception {
		JvmTypeReference List_String = ref(List.class, ref(String.class));
		JvmTypeReference List_rawtype = ref(List.class);
		assertTrue(getComputer().isConformant(List_String, List_rawtype));
		assertTrue(getComputer().isConformant(List_rawtype, List_String));
	}
	
	/**
	 * List<? extends String> <= List
	 * and
	 * List <= List<String>
	 
	@Test public void testRawType_extends() throws Exception {
		JvmTypeReference List_String = ref(List.class, wc_extends(ref(String.class)));
		JvmTypeReference List_rawtype = ref(List.class);
		assertTrue(getComputer().isConformant(List_String, List_rawtype));
		assertTrue(getComputer().isConformant(List_rawtype, List_String));
	}
	
	@Test public void testInterfacesConformToObject() throws Exception {
		JvmTypeReference interfaceType = ref(CharSequence.class);
		JvmTypeReference objectType = ref(Object.class);
		assertTrue(getComputer().isConformant(objectType, interfaceType));
	}
	
	@Test public void testStringAndInteger() throws Exception {
		JvmTypeReference stringType = ref(String.class);
		JvmTypeReference integerType = ref(Integer.class);
		assertFalse(getComputer().isConformant(integerType, stringType));
		assertFalse(getComputer().isConformant(integerType, stringType));
	}
	
	/**
	 * List<? super String> <= List<? super CharSequence> (but not vice versa)
	 
	@Test public void testGenerics_super_1() throws Exception {
		JvmTypeReference List_of_super_CharSequence = ref(List.class, wc_super(ref(CharSequence.class)));
		JvmTypeReference List_of_super_String = ref(List.class, wc_super(ref(String.class)));
		assertTrue(getComputer().isConformant(List_of_super_String, List_of_super_CharSequence));
		assertFalse(getComputer().isConformant(List_of_super_CharSequence, List_of_super_String));
	}
	
	/**
	 * Iterable<? super String> <= Iterable<Object> (but not vice versa) 
	 
	@Test public void testGenerics_super_2() throws Exception {
		JvmTypeReference iterable_of_super_string = ref(Iterable.class, wc_super(ref(String.class)));
		JvmTypeReference iterable_of_object = ref(Iterable.class, ref(Object.class));
		assertTrue(getComputer().isConformant(iterable_of_super_string, iterable_of_object));
		assertFalse(getComputer().isConformant(iterable_of_object, iterable_of_super_string));
		assertTrue(getComputer().isConformant(iterable_of_super_string, iterable_of_object, true));
		assertTrue(getComputer().isConformant(iterable_of_object, iterable_of_super_string, true));
	}
	
	/**
	 * Iterable<? super String> <= List<Object> (but not vice versa) 
	 
	@Test public void testGenerics_super_3() throws Exception {
		JvmTypeReference iterable_of_super_string = ref(Iterable.class, wc_super(ref(String.class)));
		JvmTypeReference list_of_object = ref(List.class, ref(Object.class));
		assertTrue(getComputer().isConformant(iterable_of_super_string, list_of_object));
		assertFalse(getComputer().isConformant(list_of_object, iterable_of_super_string));
		assertTrue(getComputer().isConformant(iterable_of_super_string, list_of_object, true));
		assertFalse(getComputer().isConformant(list_of_object, iterable_of_super_string, true));
	}

	/**
	 * List<? super List> =/= List<? extends ArrayList> 
	 
	@Test public void testGenerics_super_4() throws Exception {
//		List<? super List> superList = null;
//		List<? extends ArrayList> extendsArrayList = null;
//		superList = extendsArrayList;
//		extendsArrayList = superList;
		JvmTypeReference list_super_list = ref(List.class, wc_super(ref(List.class)));
		JvmTypeReference list_extends_arraylist = ref(List.class, wc_extends(ref(ArrayList.class)));
		assertFalse(getComputer().isConformant(list_super_list, list_extends_arraylist));
		assertFalse(getComputer().isConformant(list_extends_arraylist, list_super_list));
		assertTrue(getComputer().isConformant(list_super_list, list_extends_arraylist, true));
		assertTrue(getComputer().isConformant(list_extends_arraylist, list_super_list, true));
	}
	
	/**
	 * List<?> <= List<CharSequence>
	 
	@Test public void testGenerics_UnconstraintWildcard_1() throws Exception {
		JvmTypeReference List_CharSequence = ref(List.class, ref(CharSequence.class));
		JvmTypeReference List_of_wildcard = ref(List.class, wc());
		assertTrue(getComputer().isConformant(List_of_wildcard, List_CharSequence));
		assertFalse(getComputer().isConformant(List_CharSequence, List_of_wildcard));
	}
	
	/**
	 * List<?> <= List<? super CharSequence> 
	 
	@Test public void testGenerics_UnconstraintWildcard_2() throws Exception {
		JvmTypeReference List_of_super_CharSequence = ref(List.class, wc_super(ref(CharSequence.class)));
		JvmTypeReference List_of_wildcard = ref(List.class, wc());
		assertTrue(getComputer().isConformant(List_of_wildcard, List_of_super_CharSequence));
		assertFalse(getComputer().isConformant(List_of_super_CharSequence, List_of_wildcard));
	}
	
	/**
	 * List<?> <= List<? extends CharSequence> 
	 
	@Test public void testGenerics_UnconstraintWildcard_3() throws Exception {
		JvmTypeReference List_of_extends_CharSequence = ref(List.class, wc_extends(ref(CharSequence.class)));
		JvmTypeReference List_of_wildcard = ref(List.class, wc());
		assertTrue(getComputer().isConformant(List_of_wildcard, List_of_extends_CharSequence));
		assertFalse(getComputer().isConformant(List_of_extends_CharSequence, List_of_wildcard));
	}
	
	/**
	 * List<? extends Object> <= List<CharSequence>
	 
	@Test public void testGenerics_UnconstraintWildcard_4() throws Exception {
		JvmTypeReference List_CharSequence = ref(List.class, ref(CharSequence.class));
		JvmTypeReference List_extends_Object = ref(List.class, wc_extends(ref(Object.class)));
		assertTrue(getComputer().isConformant(List_extends_Object, List_CharSequence));
		assertFalse(getComputer().isConformant(List_CharSequence, List_extends_Object));
	}
	
	/**
	 * List<? extends Object> <= List<? super CharSequence> 
	 
	@Test public void testGenerics_UnconstraintWildcard_5() throws Exception {
		JvmTypeReference List_of_super_CharSequence = ref(List.class, wc_super(ref(CharSequence.class)));
		JvmTypeReference List_extends_Object = ref(List.class, wc_extends(ref(Object.class)));
		assertTrue(getComputer().isConformant(List_extends_Object, List_of_super_CharSequence));
		assertFalse(getComputer().isConformant(List_of_super_CharSequence, List_extends_Object));
	}
	
	/**
	 * List<? extends Object> <= List<? extends CharSequence> 
	 
	@Test public void testGenerics_UnconstraintWildcard_6() throws Exception {
		JvmTypeReference List_of_extends_CharSequence = ref(List.class, wc_extends(ref(CharSequence.class)));
		JvmTypeReference List_extends_Object = ref(List.class, wc_extends(ref(Object.class)));
		assertTrue(getComputer().isConformant(List_extends_Object, List_of_extends_CharSequence));
		assertFalse(getComputer().isConformant(List_of_extends_CharSequence, List_extends_Object));
	}

	@Test public void testGenerics_1() throws Exception {
		JvmTypeReference List_of_String = ref(List.class, ref(String.class));
		JvmTypeReference List_of_extends_String = ref(List.class, wc_extends(ref(String.class)));
		assertTrue(getComputer().isConformant(List_of_extends_String, List_of_String));
		assertFalse(getComputer().isConformant(List_of_String, List_of_extends_String));
	}
	
	@Test public void testGenerics_2() throws Exception {
		JvmTypeReference List_of_String = ref(List.class, ref(String.class));
		JvmTypeReference Collection_of_String = ref(Collection.class, ref(String.class));
		assertTrue(getComputer().isConformant(Collection_of_String, List_of_String));
		assertFalse(getComputer().isConformant(List_of_String, Collection_of_String));
	}

	@Test public void testGenerics_3() throws Exception {
		JvmTypeReference Func_of_String_String = ref(Map.class, ref(String.class), ref(String.class));
		JvmTypeReference Func_of_extends_String_String = ref(Map.class, wc_extends(ref(String.class)), ref(String.class));
		assertTrue(getComputer().isConformant(Func_of_extends_String_String, Func_of_String_String));
		assertFalse(getComputer().isConformant(Func_of_String_String, Func_of_extends_String_String));
	}

	@Test public void testGenerics_4() throws Exception {
		JvmTypeReference Func_of_String_String = ref(Map.class, ref(String.class), ref(String.class));
		JvmTypeReference Func_of_extends_String_String = ref(Map.class, ref(String.class), ref(String.class));
		assertTrue(getComputer().isConformant(Func_of_extends_String_String, Func_of_String_String));
		assertTrue(getComputer().isConformant(Func_of_String_String, Func_of_extends_String_String));
	}
	
	/**
	 * List<CharSequence> =/= List<String>  
	 
	@Test public void testGenerics_5() throws Exception {
		JvmTypeReference List_of_String = ref(List.class, ref(String.class));
		JvmTypeReference List_of_CharSequence = ref(List.class, ref(CharSequence.class));
		assertFalse(getComputer().isConformant(List_of_CharSequence, List_of_String));
		assertFalse(getComputer().isConformant(List_of_String, List_of_CharSequence));
	}
	
	@Test public void testGenerics_6() throws Exception {
		JvmTypeReference List_of_List_of_String = ref(List.class, ref(List.class, ref(String.class)));
		JvmTypeReference List_of_List_of_CharSequence = ref(List.class, ref(List.class, ref(CharSequence.class)));
		assertFalse(getComputer().isConformant(List_of_List_of_CharSequence, List_of_List_of_String));
		assertFalse(getComputer().isConformant(List_of_List_of_String, List_of_List_of_CharSequence));
	}

	@Test public void testSameType() throws Exception {
		assertTrue(getComputer().isConformant(ref(String.class), ref(String.class)));
	}

	@Test public void testAnyTypeReference_01() {
		assertTrue(getComputer().isConformant(any(), any()));
	}
	
	@Test public void testAnyTypeReference_02() {
		assertTrue(getComputer().isConformant(ref(Object.class), any()));
	}
	
	@Test public void testAnyTypeReference_03() {
		assertTrue(getComputer().isConformant(ref(Void.class), any()));
	}
	
	@Test public void testAnyTypeReference_04() {
		assertFalse(getComputer().isConformant(any(), ref(Object.class)));
	}
	
	@Test public void testAnyTypeReference_05() {
		assertFalse(getComputer().isConformant(any(), ref(int.class)));
	}
	
	@Test public void testVoidReference_01() {
		assertTrue(getComputer().isConformant(ref(void.class), ref(void.class)));
	}
	
	@Test public void testVoidReference_02() {
		assertFalse(getComputer().isConformant(ref(int.class), ref(void.class)));
	}
	
	@Test public void testVoidReference_03() {
		assertFalse(getComputer().isConformant(ref(Object.class), ref(void.class)));
	}
	
	@Test public void testVoidReference_04() {
		assertFalse(getComputer().isConformant(any(), ref(void.class)));
	}
	
	@Test public void testVoidReference_05() {
		assertFalse(getComputer().isConformant(ref(void.class), any()));
	}
	
	@Test public void testVoidReference_06() {
		assertFalse(getComputer().isConformant(ref(void.class), ref(Object.class)));
	}
	
	@Test public void testVoidReference_07() {
		assertFalse(getComputer().isConformant(ref(void.class), ref(Void.class)));
	}
	
	@Test public void testVoidReference_08() {
		assertFalse(getComputer().isConformant(any(), ref(Void.class)));
	}
	
	@Test public void testInheritanceCompatibility() throws Exception {
		assertTrue(getComputer().isConformant(ref(CharSequence.class), ref(String.class)));
		assertFalse(getComputer().isConformant(ref(String.class), ref(CharSequence.class)));
	}

	@Test public void testAutoBoxingInteger_00() throws Exception {
		assertTrue(getComputer().isConformant(ref(Integer.TYPE), ref(Integer.class)));
		assertTrue(getComputer().isConformant(ref(Integer.TYPE), param(upper(ref(Integer.class)))));
		assertTrue(getComputer().isConformant(ref(Integer.TYPE), ref(Byte.class)));
		assertTrue(getComputer().isConformant(ref(Integer.TYPE), ref(Character.class)));
		assertFalse(getComputer().isConformant(ref(Integer.TYPE), ref(Long.class)));
	}
	@Test public void testAutoBoxingInteger_01() throws Exception {
		assertTrue(getComputer().isConformant(ref(Integer.class), ref(Integer.TYPE)));
	}
	@Test public void testAutoBoxingInteger_02() throws Exception {
		assertTrue(getComputer().isConformant(ref(Serializable.class), ref(Integer.TYPE)));
	}
	@Test public void testAutoBoxingInteger_03() throws Exception {
		assertTrue(getComputer().isConformant(ref(Comparable.class), ref(Integer.TYPE)));
	}
	@Test public void testAutoBoxingInteger_04() throws Exception {
		assertTrue(getComputer().isConformant(ref(Number.class), ref(Integer.TYPE)));
	}
	@Test public void testAutoBoxingInteger_05() throws Exception {
		assertTrue(getComputer().isConformant(ref(Object.class), ref(Integer.TYPE)));
	}
	@Test public void testAutoBoxingInteger_06() throws Exception {
		assertFalse(getComputer().isConformant(ref(String.class), ref(Integer.TYPE)));
	}

	@Test public void testAutoBoxingBoolean() throws Exception {
		assertTrue(getComputer().isConformant(ref(Boolean.TYPE), ref(Boolean.class)));
		assertTrue(getComputer().isConformant(ref(Boolean.class), ref(Boolean.TYPE)));
	}

	@Test public void testAutoBoxingLong() throws Exception {
		assertTrue(getComputer().isConformant(ref(Long.TYPE), ref(Long.class)));
		assertTrue(getComputer().isConformant(ref(Long.class), ref(Long.TYPE)));
	}

	@Test public void testAutoBoxingFloat() throws Exception {
		assertTrue(getComputer().isConformant(ref(Float.TYPE), ref(Float.class)));
		assertTrue(getComputer().isConformant(ref(Float.class), ref(Float.TYPE)));
	}

	@Test public void testAutoBoxingDouble() throws Exception {
		assertTrue(getComputer().isConformant(ref(Double.TYPE), ref(Double.class)));
		assertTrue(getComputer().isConformant(ref(Double.class), ref(Double.TYPE)));
	}

	@Test public void testAutoBoxingByte() throws Exception {
		assertTrue(getComputer().isConformant(ref(Byte.TYPE), ref(Byte.class)));
		assertTrue(getComputer().isConformant(ref(Byte.class), ref(Byte.TYPE)));
	}
	
	@Test public void testPrimitiveVoid() throws Exception {
		assertTrue(getComputer().isConformant(ref(Void.TYPE), ref(Void.TYPE)));
	}

	@Test public void testArrayType() throws Exception {
		assertTrue(getComputer().isConformant(array(ref(String.class), 1), array(ref(String.class), 1)));
		assertFalse(getComputer().isConformant(array(ref(String.class), 1), array(ref(CharSequence.class), 1)));
		assertFalse(getComputer().isConformant(array(ref(String.class), 2), array(ref(String.class), 1)));
		assertFalse(getComputer().isConformant(array(ref(String.class), 1), array(ref(String.class), 2)));
	}
	
	@Test public void testPrimitiveArrayType() throws Exception {
		assertFalse(getComputer().isConformant(array(ref(double.class), 1), array(ref(int.class), 1)));
		assertFalse(getComputer().isConformant(array(ref(int.class), 1), array(ref(double.class), 1)));
	}
	
	@Test public void testPrimitiveArrayToObjectArray() throws Exception {
		assertTrue(getComputer().isConformant(array(ref(Object.class), 1), array(ref(int.class), 2)));
		assertTrue(getComputer().isConformant(array(ref(Serializable.class), 1), array(ref(double.class), 2)));
	}
	
	@Test public void testArrayTypeToObject() throws Exception {
		assertTrue(getComputer().isConformant(ref(Object.class), array(ref(String.class), 1)));
		assertTrue(getComputer().isConformant(ref(Object.class), array(ref(String.class), 3)));
		assertFalse(getComputer().isConformant(array(ref(String.class), 1), ref(Object.class)));
	}
	
	@Test public void testArrayTypeToSerializable() throws Exception {
		assertTrue(getComputer().isConformant(ref(Serializable.class), array(ref(String.class), 1)));
		assertTrue(getComputer().isConformant(ref(Serializable.class), array(ref(int.class), 1)));
	}
	
	@Test public void testArrayTypeToClonable() throws Exception {
		assertTrue(getComputer().isConformant(ref(Cloneable.class), array(ref(String.class), 1)));
		assertTrue(getComputer().isConformant(ref(Cloneable.class), array(ref(int.class), 1)));
	}
	
	@Test public void testAnyToArray() throws Exception {
		assertTrue(getComputer().isConformant(array(ref(String.class), 1), any()));
		assertTrue(getComputer().isConformant(array(ref(int.class), 1), any()));
	}

	protected void assertCommonSuperType(Class<?> expected, Class<?> ...types) {
		List<JvmTypeReference> refs = Lists.newArrayList();
		for (int i = 0; i < types.length; i++) {
			Class<?> class1 = types[i];
			refs.add(ref(class1));
		}
		assertCommonSuperType(expected.getCanonicalName(), refs);
	}
	
	protected void assertCommonSuperType(String expected, JvmTypeReference... types) {
		assertCommonSuperType(expected, Arrays.asList(types));
	}
	
	protected void assertCommonSuperType(String expected, List<JvmTypeReference> refs) {
		JvmTypeReference type = getComputer().getCommonSuperType(refs);
		assertEquals(expected, type.getIdentifier());
	}
	
	@Test public void testCommonSuperType_0() throws Exception {
		assertCommonSuperType(
				"java.io.Serializable & java.lang.CharSequence",
				ref(String.class),
				ref(StringBuilder.class));
	}
	

	@Test public void testCommonSuperType_1() throws Exception {
		assertCommonSuperType(
				CharSequence.class,
				String.class,
				StringBuilder.class,
				CharSequence.class
				);
	}
	
	@Test public void testCommonSuperType_2() throws Exception {
		assertCommonSuperType(Object.class,
				String.class,
				StringBuilder.class,
				CharSequence.class,
				Object.class
		);
	}
	
	@Test public void testCommonSuperType_3() throws Exception {
		assertCommonSuperType(String.class,
				String.class,
				String.class
		);
	}
	
	@Test public void testCommonSuperType_4() throws Exception {
		assertCommonSuperType(String.class,
				String.class
		);
	}
	
	@Test public void testCommonSuperType_5() throws Exception {
		assertCommonSuperType(
				Serializable.class,
				String.class,
				StringBuilder.class,
				Serializable.class);
	}
	
	@Test public void testCommonSuperType_6() throws Exception {
		assertCommonSuperType(
				"java.io.Serializable & java.lang.CharSequence", // like testCommonSuperType_6 but different order
				ref(StringBuilder.class),
				ref(String.class));
	}
	
	@Test public void testCommonSuperType_7() throws Exception {
		assertCommonSuperType(
				"java.lang.Appendable & java.lang.CharSequence",
				ref(StringBuilder.class),
				ref(CharBuffer.class));
	}
	
	@Test public void testCommonSuperType_8() throws Exception {
		assertCommonSuperType(
				"java.util.Collection<java.lang.String>",
				ref(Set.class, ref(String.class)),
				ref(List.class, ref(String.class)));
	}
	
	@Test public void testCommonSuperType_9() throws Exception {
		assertCommonSuperType(
				"java.util.Collection", // one rawtype given - expect raw type
				ref(Set.class, ref(String.class)),
				ref(List.class));
	}
	
	@Test public void testCommonSuperType_10() throws Exception {
		assertCommonSuperType(
				"java.util.Collection<? extends java.lang.CharSequence>",
				ref(Set.class, ref(String.class)),
				ref(List.class, ref(CharSequence.class)));
	}
	
	@Test public void testCommonSuperType_11() throws Exception {
		assertCommonSuperType(
				"java.lang.Comparable<? extends java.lang.Object> & java.io.Serializable",
				ref(String.class),
				ref(Integer.class));
	}
	
	@Test public void testCommonSuperType_12() throws Exception {
		assertCommonSuperType(
				"java.lang.Number & java.lang.Comparable<? extends java.lang.Object>",
				ref(Double.class),
				ref(Integer.class));
	}
	
	@Test public void testCommonSuperType_13() throws Exception {
		assertCommonSuperType(
				"java.lang.AbstractStringBuilder & java.io.Serializable",
				ref(StringBuilder.class),
				ref(StringBuffer.class));
	}
	
	@Test public void testCommonSuperType_14() throws Exception {
		assertCommonSuperType(
				"java.lang.CharSequence & java.io.Serializable",
				multiRef(ref(CharSequence.class), ref(Serializable.class)),
				multiRef(ref(CharSequence.class), ref(Serializable.class)));
	}
	
	@Test public void testCommonSuperType_15() throws Exception {
		assertCommonSuperType(
				"java.io.Serializable & java.lang.CharSequence",
				multiRef(ref(Serializable.class), ref(CharSequence.class)),
				multiRef(ref(CharSequence.class), ref(Serializable.class)));
	}
	
	@Test public void testCommonSuperType_16() throws Exception {
		assertCommonSuperType(
				"java.io.Serializable & java.lang.CharSequence",
				multiRef(ref(Serializable.class), ref(CharSequence.class), ref(Comparable.class)),
				multiRef(ref(Serializable.class), ref(CharSequence.class)));
	}
	
	@Test public void testCommonSuperType_17() throws Exception {
		assertCommonSuperType(
				"java.lang.CharSequence & java.io.Serializable",
				multiRef(ref(Serializable.class), ref(CharSequence.class), ref(Comparable.class)),
				multiRef(ref(CharSequence.class), ref(Serializable.class)));
	}
	
	@Test public void testCommonSuperType_18() throws Exception {
		assertCommonSuperType(
				"java.io.Serializable & java.lang.CharSequence",
				multiRef(ref(Serializable.class), ref(CharSequence.class)),
				multiRef(ref(Serializable.class), ref(CharSequence.class), ref(Comparable.class)));
	}
	
	@Test public void testCommonSuperType_19() throws Exception {
		assertCommonSuperType(
				"java.lang.CharSequence & java.io.Serializable",
				multiRef(ref(CharSequence.class), ref(Serializable.class)),
				multiRef(ref(Serializable.class), ref(CharSequence.class), ref(Comparable.class)));
	}
	
	@Test public void testCommonSuperType_20() throws Exception {
		assertCommonSuperType(
				"java.lang.Object",
				multiRef(ref(Serializable.class), ref(CharSequence.class)),
				multiRef(ref(Comparable.class), ref(Appendable.class)));
	}
	
	@Test public void testCommonSuperType_21() throws Exception {
		assertCommonSuperType(
				"java.lang.Object",
				multiRef(ref(Serializable.class), ref(CharSequence.class)),
				ref(CharSequence.class),
				ref(Serializable.class));
	}
	
	@Test public void testCommonSuperType_22() throws Exception {
		assertCommonSuperType(
				"java.lang.CharSequence",
				multiRef(ref(Serializable.class), ref(CharSequence.class)),
				ref(CharSequence.class));
	}
	
	@Test public void testCommonSuperType_23() throws Exception {
		assertCommonSuperType(
				"java.io.Serializable & java.lang.CharSequence",
				multiRef(ref(Serializable.class), ref(CharSequence.class)),
				any());
	}
	
	@Test public void testCommonSuperType_24() throws Exception {
		assertCommonSuperType(
				"null",
				any(),
				any());
	}
	
	@Test public void testCommonSuperType_25() throws Exception {
		assertCommonSuperType(
				"java.lang.CharSequence",
				ref(CharSequence.class),
				ref(String.class),
				any());
	}
	
	@Test public void testCommonSuperType_26() throws Exception {
		assertCommonSuperType(
				"java.util.Collection<? extends java.lang.AbstractStringBuilder & java.io.Serializable>",
				ref(List.class, ref(StringBuffer.class)),
				ref(Set.class, ref(StringBuilder.class)));
	}
	
	@Test public void testCommonSuperType_27() throws Exception {
		assertCommonSuperType(
				"java.lang.Iterable", // one raw type - super type should be raw type
				ref(Collection.class, ref(String.class)),
				ref(RawIterable.class));
	}
	
	@Test public void testCommonSuperType_28() throws Exception {
//		Integer[] i = null;
//		Double[] d = null;
//		List<Comparable<? extends Number>[]> list = ImmutableList.<Comparable<? extends Number>[]>of(i, d);
//		List<Comparable<? extends Comparable<? extends Object>>[]> list2 = ImmutableList.<Comparable<? extends Comparable<? extends Object>>[]>of(i, d);
		assertCommonSuperType(
				"java.lang.Number[] & java.lang.Comparable<? extends java.lang.Object>[]",
				array(ref(Double.class),1),
				array(ref(Integer.class),1));
	}
	
	@Test public void testCommonSuperType_29() throws Exception {
		assertCommonSuperType(
				"java.io.Serializable & java.lang.Cloneable",
				array(ref(double.class),1),
				array(ref(int.class),1));
	}
	
	@Test public void testCommonSuperType_30() throws Exception {
		assertCommonSuperType(
				"java.io.Serializable & java.lang.Cloneable",
				array(ref(int.class),2),
				array(ref(int.class),1));
	}
	
	@Test public void testCommonSuperType_31() throws Exception {
		assertCommonSuperType(
				"java.lang.Object[]",
				array(ref(CharSequence.class),2),
				array(ref(CharSequence.class),1));
	}
	
	@Test public void testCommonSuperType_32() throws Exception {
		assertCommonSuperType(
				"java.lang.Number[]",
				array(ref(Number.class),1),
				array(ref(Integer.class),1),
				array(ref(Double.class),1));
	}
	
	@Test public void testCommonSuperType_33() throws Exception {
		assertCommonSuperType(
				"java.lang.Number[][][] & java.lang.Comparable<? extends java.lang.Object>[][][]",
				array(ref(Double.class),3),
				array(ref(Integer.class),3));
	}
	
	@Test public void testCommonSuperType_34() throws Exception {
		assertCommonSuperType(
				"java.io.Serializable[]",
				array(ref(Number.class),2),
				array(ref(Number.class),1));
	}
	
	@Test public void testCommonSuperType_35() throws Exception {
		assertCommonSuperType(
				"java.lang.Comparable<? extends java.lang.Object> & java.io.Serializable",
				ref(String.class),
				ref(int.class));
	}
	
	@Test
	public void testCommonSuperType_36() throws Exception {
		assertCommonSuperType(
				"java.util.AbstractList<java.lang.String> & java.io.Serializable & java.lang.Cloneable",
				ref(LinkedList.class, ref(String.class)),
				ref(ArrayList.class, ref(String.class)));
	}
	@Test
	public void testCommonSuperType_37() throws Exception {
		assertCommonSuperType(
				"java.util.AbstractList<java.lang.String> & java.io.Serializable & java.lang.Cloneable",
				ref(LinkedList.class, ref(String.class)),
				ref(ArrayList.class, ref(String.class)));
	}
	
	@Test public void testBug343100_01() throws Exception {
		assertCommonSuperType(
				"java.lang.Class<? extends java.lang.Object>",
				ref(Class.class, ref(Void.class)),
				ref(Class.class, ref(String.class)),
				any());
	}
	
	@Test public void testBug343100_02() throws Exception {
		assertCommonSuperType(
				"java.util.Collection<? extends java.lang.Object>",
				ref(Collection.class, ref(Void.class)),
				ref(List.class, ref(String.class)));
	}
	
//	public <T extends Number> void method(Number n, T t) {
//		n = t;
//		t = n; // compile error in this line
//	}
	
	@Test public void testBug343089_01() throws Exception {
		JvmTypeReference number = ref(Number.class);
		JvmTypeReference param = param(upper(ref(Number.class)));
		assertTrue(getComputer().isConformant(number, param));
		assertFalse(getComputer().isConformant(param, number));
	}
	
	@Test public void testBug343089_02() throws Exception {
		JvmTypeReference serializable = ref(Serializable.class);
		JvmTypeReference param = param(upper(ref(Number.class)));
		assertTrue(getComputer().isConformant(serializable, param));
	}
	
	@Test public void testBug343089_03() throws Exception {
		JvmTypeReference number = ref(Number.class);
		JvmTypeReference param = param(lower(ref(Number.class)));
		assertFalse(getComputer().isConformant(number, param));
		assertFalse(getComputer().isConformant(param, number));
	}
	
	@Test public void testConformanceWithTypeParameter() throws Exception {
		JvmTypeParameter typeParamE = ((JvmGenericType)ref(List.class).getType()).getTypeParameters().get(0);
		JvmParameterizedTypeReference reference = TypesFactory.eINSTANCE.createJvmParameterizedTypeReference();
		reference.setType(typeParamE);
		assertFalse(getComputer().isConformant(reference, ref(Object.class)));
		assertFalse(getComputer().isConformant(reference, ref(String.class)));
		assertFalse(getComputer().isConformant(reference, ref(CharSequence.class)));
		assertFalse(getComputer().isConformant(reference, ref(Serializable.class)));
	}
	
	@Test public void testConformanceWithTypeParameter_02() throws Exception {
		JvmTypeParameter typeParamE = ((JvmGenericType)ref(List.class).getType()).getTypeParameters().get(0);
		JvmParameterizedTypeReference reference = TypesFactory.eINSTANCE.createJvmParameterizedTypeReference();
		reference.setType(typeParamE);
		JvmTypeReference typeParamExtends = param(upper(reference));
		assertTrue(getComputer().isConformant(reference, typeParamExtends, true));
		assertTrue(getComputer().isConformant(reference, typeParamExtends, false));
		assertFalse(getComputer().isConformant(typeParamExtends, reference, true));
		assertFalse(getComputer().isConformant(typeParamExtends, reference, false));
	}
	
	
	public TypeConformanceComputer getComputer() {
		return computer;
	}

}


*/