package com.stackexchange.astar;

import java.util.Map;

public final class NodeData<T> { 
	private static final double DEFAULT_HEURISTIC = 1.0;

    private final T nodeId;
    private final Map<T, Double> heuristic;

    private double g;  // g is distance from the source
    private double h;  // h is the heuristic of destination.
    private double f;  // f = g + h 

    public NodeData (T nodeId, Map<T, Double> heuristic) {
        this.nodeId = nodeId;
        this.g = Double.MAX_VALUE; 
        this.heuristic = heuristic;
    }

    public T getNodeId() {
        return nodeId;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void calcF(T destination) {
    	if(heuristic.containsKey(destination)) {
    		this.h = heuristic.get(destination);
    	} else {
    		this.h = DEFAULT_HEURISTIC;
    	}
        this.f = g + h;
    } 

    public double getH() {
        return h;
    }

    public double getF() {
        return f;
    }
 }