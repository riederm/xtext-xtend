/*******************************************************************************
 * Copyright (c) 2011 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtend.core.tests.richstring;

import java.util.List;

import org.eclipse.xtend.core.tests.richstring.CommentInserter;
import org.eclipse.xtend.core.tests.validation.RichStringValidationTest;
import org.junit.Rule;
import org.junit.rules.TestName;

import com.google.inject.Inject;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class RichStringWithCommentValidationTest extends RichStringValidationTest {
	
	@Rule public TestName name = new TestName();

	@Inject
	private CommentInserter commentInserter;
	
	@Override
	protected void assertValidRichString(String input) throws Exception {
		input = shorten(input);
		List<String> richStringWithComments = commentInserter.getRichStringWithComments(input);
		System.out.println(richStringWithComments.size() + " " + name.getMethodName());
		for(String richStringWithComment: richStringWithComments)
			super.assertValidRichString(richStringWithComment);
	}
	
	@Override
	public void testRichStringValidation_06() throws Exception {
		// TODO Auto-generated method stub
		super.testRichStringValidation_06();
	}
	
	private String shorten(String s) {
		s = s.replace("indentedLine", "iL");
		return s;
	}
}
