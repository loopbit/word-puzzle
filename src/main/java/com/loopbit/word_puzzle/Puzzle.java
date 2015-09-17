package com.loopbit.word_puzzle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import com.loopbit.distance.Levenshtein;
import com.stackexchange.astar.AStar;
import com.stackexchange.astar.GraphAStar;

public class Puzzle {
	
	private AStar<String> aStar;
	
	public Puzzle() throws URISyntaxException, IOException {
		List<String> words = new ArrayList<String>();
		// TODO Refactor AStar so edges and nodes can be added dynamically.
		Map<String, ArrayList<String>> edges = new HashMap<String, ArrayList<String>>();
		
		// Load dictionary file
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL resourceURL = classloader.getResource("dictionary.txt");
		Path filePath = Paths.get(resourceURL.toURI());
	    try(Stream<String> lines = Files.lines(filePath)){
	    	lines.forEach(s -> words.add(s.trim()));
	    }
	    
		// Build map
	    GraphAStar<String> graph = new GraphAStar<String>();
	    words.forEach(string -> {
	    	Levenshtein automaton = new Levenshtein(string, 1);
	    	Map<String, Double> heuristic = new HashMap<String, Double>();
	    	
	    	heuristic.put(string, 0.0);
			edges.put(string, new ArrayList<String>());
	    	words.forEach(query -> {
	    		if(automaton.within(query)){
	    			edges.get(string).add(query);
	    			heuristic.put(query, 1.0);
	    		}
	    	});
	    	graph.addHeuristic(string, heuristic);
	    });
	    
	    edges.keySet().forEach(node -> {
	        graph.addNode(node);
	    });

	    edges.entrySet().forEach(paths -> {
	    	paths.getValue().forEach(dest -> {
		    	graph.addEdge(paths.getKey(), dest, 1.0);
	    	});
	    });
	    
	    aStar = new AStar<String>(graph);
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		Puzzle puzzle = new Puzzle();
		puzzle.search(args[0], args[1]);
	}
	
	public void search(String from, String to) throws URISyntaxException, IOException {
	    for (String path : aStar.astar(from, to)) {
            System.out.println(path);
        }
	}

}
