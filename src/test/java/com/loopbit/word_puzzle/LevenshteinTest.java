package com.loopbit.word_puzzle;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.loopbit.distance.Levenshtein;

public class LevenshteinTest {
	
	@Test public void testWithin() {
		Levenshtein automaton = new Levenshtein("banana", 1);
		
		assertTrue("'w' should be within 'banana'", automaton.within("w"));
		assertFalse("'wo' shouldn't be within 'banana'", automaton.within("wo"));

		assertTrue("'banano' should be within 'banana'", automaton.within("banano"));
		assertFalse("'bonono' shouldn't be within 'banana'", automaton.within("bonono"));
		
		assertFalse("'qwerty' shouldn't be within 'banana'", automaton.within("qwerty"));
	}
	
	@Test public void testSashAshy() {
		Levenshtein automaton = new Levenshtein("sash", 1);
		
		assertFalse("'ashy' shouldn't be within 'sash'", automaton.within("ashy"));
	}

}
