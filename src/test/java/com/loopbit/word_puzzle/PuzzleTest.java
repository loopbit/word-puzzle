package com.loopbit.word_puzzle;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

public class PuzzleTest {
	
	@Test public void testMain() throws IOException, URISyntaxException {
		Puzzle puzzle = new Puzzle();
		puzzle.search("head", "tail");
		
	}

}
