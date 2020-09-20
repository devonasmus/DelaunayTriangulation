package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
private Map<Location, Map<Location, Weight>> graph = new HashMap<Location, Map<Location, Weight>>();
	
	private Set<Weight> edges = new HashSet<Weight>();
	private int numVertices = 0;
	public void addVertex(Location location) {
		graph.put(location, new HashMap<Location, Weight>());
		numVertices++;
	}
	
	public Weight addEdge(Location l1, Location l2) {
		double verticalDistance = l1.getY() - l2.getY();
		double horizontalDistance = l1.getX() - l2.getX();
		double distance = Math.sqrt(Math.pow(verticalDistance, 2) + Math.pow(horizontalDistance, 2));
		Weight edge = new Weight(l1, l2, distance);
		graph.get(l1).put(l2, edge);
		graph.get(l2).put(l1, edge);
		edges.add(edge);
		return edge;
		
	}
	
	public void removeEdge(Weight w) {
//		Weight w = graph.get(l1).remove(l2);
//		graph.get(l2).remove(l1);
		graph.get(w.getEndVertices()[0]).remove(w.getEndVertices()[1]);
		graph.get(w.getEndVertices()[1]).remove(w.getEndVertices()[0]);
		edges.remove(w);
	}
	
	public Weight getEdge(Location l1, Location l2) {
		return graph.get(l1).get(l2);
	}
	
	public Iterable<Location> getVertices() {
		return graph.keySet();
	}
	
	public Iterable<Location> getAdjacentVertices(Location location) {
		return graph.get(location).keySet();
	}
	
	public Iterable<Weight> getEdges() {
		return edges;
	}
	
	public Iterable<Weight> getIndicentEdges(Location location) {
		return graph.get(location).values();
	}
	
	public int numVertices() {
		return numVertices;
	}
	
	public Location opposite(Location location, Weight weight) {
		if (location.equals(weight.getEndVertices()[0])) {
			return weight.getEndVertices()[1];
		} else if (location.equals(weight.getEndVertices()[1])) {
			return weight.getEndVertices()[0];
		} else {
			return null;
		}
	}
}
