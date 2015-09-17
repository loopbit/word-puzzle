package com.stackexchange.astar;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class AStarTest {

	@Test public void searchTest() {
//		Map<String, Map<String, Double>> hueristic = new HashMap<String, Map<String, Double>>();
        // map for A    
        Map<String, Double> mapA = new HashMap<String, Double>();
        mapA.put("A",  0.0);
        mapA.put("B", 10.0);
        mapA.put("C", 20.0);
        mapA.put("E", 100.0);
        mapA.put("F", 110.0);


        // map for B
        Map<String, Double> mapB = new HashMap<String, Double>();
        mapB.put("A", 10.0);
        mapB.put("B",  0.0);
        mapB.put("C", 10.0);
        mapB.put("E", 25.0);
        mapB.put("F", 40.0);


        // map for C
        Map<String, Double> mapC = new HashMap<String, Double>();
        mapC.put("A", 20.0);
        mapC.put("B", 10.0);
        mapC.put("C",  0.0);
        mapC.put("E", 10.0);
        mapC.put("F", 30.0);


        // map for X
        Map<String, Double> mapX = new HashMap<String, Double>();
        mapX.put("A", 100.0);
        mapX.put("B", 25.0);
        mapX.put("C", 10.0);
        mapX.put("E",  0.0);
        mapX.put("F", 10.0);

        // map for X
        Map<String, Double> mapZ = new HashMap<String, Double>();
        mapZ.put("A", 110.0);
        mapZ.put("B",  40.0);
        mapZ.put("C",  30.0);
        mapZ.put("E", 10.0);
        mapZ.put("F",  0.0);

//        hueristic.put("A", mapA);
//        hueristic.put("B", mapB);
//        hueristic.put("C", mapC);
//        hueristic.put("E", mapX);
//        hueristic.put("F", mapZ);

//        GraphAStar<String> graph = new GraphAStar<String>(hueristic);
        GraphAStar<String> graph = new GraphAStar<String>();
        
        graph.addHeuristic("A", mapA);
        graph.addNode("A");
        
        graph.addHeuristic("B", mapB);
        graph.addNode("B");
        
        graph.addHeuristic("C", mapC);
        graph.addNode("C");

        graph.addHeuristic("E", mapX);
        graph.addNode("E");

        graph.addHeuristic("F", mapZ);
        graph.addNode("F");

        graph.addEdge("A", "B",  10);
        graph.addEdge("A", "E", 100);
        graph.addEdge("B", "C", 10);
        graph.addEdge("C", "E", 10);
        graph.addEdge("C", "F", 30);
        graph.addEdge("E", "F", 10);

        AStar<String> aStar = new AStar<String>(graph);

        for (String path : aStar.astar("A", "F")) {
            System.out.println(path);
        }
	}

}
