package algorithm;

import graph.Graph;
import graph.Location;
import graph.Weight;

public class Delaunay {
	/**
	 * The start of the algorithm. It takes all the vertices in the
	 * graph and puts them in a list of DTVertex objects
	 * @param g the graph
	 */
	public void delaunay(Graph g) {
		DTVertex[] list = new DTVertex[g.numVertices()];
		int index = 0;
		for (Location l: g.getVertices()) {
			list[index] = new DTVertex(l);
			index++;
		}
		delaunayHelper(g, list, 0, list.length - 1);
	}
	
	/**
	 * This method partitions the graph into subgraphs and merges them.
	 * @param g the graph
	 * @param list the list of DTVertex objects
	 * @param low the low index
	 * @param high the high index
	 */
	private void delaunayHelper(Graph g, DTVertex[] list, int low, int high) {
		quickselect(list, low, high, ((high - low)/2) + low);
		if (high - low + 1 == 2) {
			Weight e = g.addEdge(list[low].vertex, list[high].vertex);
			DTEdge dte = createDTEdge(e, list[low], list[high]);
			addDTEdge(dte, dte, dte, dte.reverse, dte.reverse);
			list[low].setFirst(dte);
			list[high].setFirst(dte.reverse);
		} else if (high - low + 1 == 3) {
			if (orientation(list[low].vertex, list[low + 1].vertex, list[high].vertex) == 0) {
				Weight e1 = g.addEdge(list[low].vertex, list[low + 1].vertex);
				DTEdge dte1 = createDTEdge(e1, list[low], list[low + 1]);
				Weight e2 = g.addEdge(list[high].vertex, list[low + 1].vertex);
				DTEdge dte2 = createDTEdge(e2, list[high], list[low + 1]);
				addDTEdge(dte1, dte1, dte1, dte2, dte2);
				addDTEdge(dte2, dte2, dte2, dte1, dte1);
				list[low].setFirst(dte1);
				list[high].setFirst(dte2);
			} else {
				Weight e1 = g.addEdge(list[low].vertex, list[low + 1].vertex);
				DTEdge dte1 = createDTEdge(e1, list[low], list[low + 1]);
				Weight e2 = g.addEdge(list[low + 1].vertex, list[high].vertex);
				DTEdge dte2 = createDTEdge(e2, list[low + 1], list[high]);
				Weight e3 = g.addEdge(list[low].vertex, list[high].vertex);
				DTEdge dte3 = createDTEdge(e3, list[low], list[high]);
				addDTEdge(dte1, dte3, dte3, dte2, dte2);
				addDTEdge(dte2, dte1.reverse, dte1.reverse, dte3.reverse, dte3.reverse);
				addDTEdge(dte3, dte1, dte1, dte2.reverse, dte2.reverse);
				
				if (ensureFirst(list[low], dte1, true)) {
					list[low].setFirst(dte1);
				}
				if (ensureFirst(list[low], dte3, true)) {
					list[low].setFirst(dte3);
				}
				if (ensureFirst(list[high], dte2.reverse, false)) {
					list[high].setFirst(dte2.reverse);
				}
				if (ensureFirst(list[high], dte3.reverse, false)) {
					list[high].setFirst(dte3.reverse);
				}
				
			}
		} else if (high - low + 1 > 3) {
			delaunayHelper(g, list, low, ((high - low) / 2) + low); //left subset
			delaunayHelper(g, list, ((high - low) / 2) + low + 1, high); //right subset
			DTEdge baseEdge = initialBaseEdge(g, list[((high - low) / 2) + low], list[((high - low) / 2) + low + 1], list[((high - low) / 2) + low].first, list[((high - low) / 2) + low + 1].first);
			if (baseEdge.origin.equals(list[low])) {
				list[low].first = baseEdge;
			}
			if (baseEdge.destination.equals(list[high])) {
				list[high].first = baseEdge.getReverse();
			}
			merge(g, baseEdge);
		}
	}
	
	/**
	 * This method finds the initial base edge in order to merge two subgraphs
	 * @param g the graph
	 * @param leftNode the left node
	 * @param rightNode the right node
	 * @param leftEdge the left edge
	 * @param rightEdge the right edge
	 * @return the initial base edge
	 */
	private DTEdge initialBaseEdge(Graph g, DTVertex leftNode, DTVertex rightNode, DTEdge leftEdge, DTEdge rightEdge) {
		while (true) {
			if (orientation(leftNode.vertex, leftEdge.destination.vertex, rightNode.vertex) > 0) {
				DTVertex next = leftEdge.destination;
				leftEdge = leftEdge.getReverse().getCw();
				leftNode = next;
				
			} else if (orientation(rightNode.vertex, rightEdge.destination.vertex, leftNode.vertex) < 0) {
				DTVertex next = rightEdge.destination;
				rightEdge = rightEdge.getReverse().getCcw();
				rightNode = next;
			} else {
				break;
			}
		}
		
		Weight e = g.addEdge(leftNode.vertex, rightNode.vertex);
		DTEdge dte = createDTEdge(e, leftNode, rightNode);
		addDTEdge(dte, leftEdge, leftEdge.getCcw(), rightEdge.getCw(), rightEdge);
		
		return dte;
	}
	
	/**
	 * This method ensures that an edge can be set as the first edge to reference
	 * in a DTVertex
	 * @param origin the origin
	 * @param candidate the candidate edge
	 * @param right a boolean value to see if the edge in a right subgraph
	 * @return true or false
	 */
	private boolean ensureFirst(DTVertex origin, DTEdge candidate, boolean right) {
		Location l = new Location(origin.vertex.getX(), origin.vertex.getY() - 1);
		if (origin.first == null) {
			return true;
		} else if (right && orientation(origin.vertex, l, candidate.destination.vertex) > 0 && orientation(origin.vertex, origin.first.destination.vertex, candidate.destination.vertex) < 0) {
			return true;
		} else if (!right && orientation(origin.vertex, l, candidate.destination.vertex) < 0 && orientation(origin.vertex, origin.first.destination.vertex, candidate.destination.vertex) > 0) {
			return true;
		}
		
		return false;
	}
	/**
	 * This method merges two subgraphs
	 * @param g the graph
	 * @param dte the base edge
	 */
	private void merge(Graph g, DTEdge dte) {
		DTEdge leftCandidate = getCandidate(g, dte, false);
		DTEdge rightCandidate = getCandidate(g, dte.getReverse(), true);
		if (leftCandidate != null && rightCandidate  != null) {
			if (!inCircle(dte.origin.vertex, dte.destination.vertex, leftCandidate.destination.vertex, rightCandidate.destination.vertex)) {
				Weight e = g.addEdge(leftCandidate.destination.vertex, dte.destination.vertex);
				DTEdge newDte = createDTEdge(e, leftCandidate.destination, dte.destination);
				addDTEdge(newDte, leftCandidate.getReverse(), leftCandidate.getReverse().getCcw(), dte.getReverse().getCw(), dte.getReverse());
				merge(g, newDte);
			} else if (!inCircle(dte.origin.vertex, dte.destination.vertex, rightCandidate.destination.vertex, leftCandidate.destination.vertex)) {
				Weight e = g.addEdge(dte.origin.vertex, rightCandidate.destination.vertex);
				DTEdge newDte = createDTEdge(e, dte.origin, rightCandidate.destination);
				addDTEdge(newDte, dte, dte.getCcw(), rightCandidate.getReverse().getCw(), rightCandidate.getReverse());
				merge(g, newDte);
			}
		} else {
			if (leftCandidate != null) {
				Weight e = g.addEdge(leftCandidate.destination.vertex, dte.destination.vertex);
				DTEdge newDte = createDTEdge(e, leftCandidate.destination, dte.destination);
				addDTEdge(newDte, leftCandidate.getReverse(), leftCandidate.getReverse().getCcw(), dte.getReverse().getCw(), dte.getReverse());
				merge(g, newDte);
			} else if (rightCandidate != null) {
				Weight e = g.addEdge(dte.origin.vertex, rightCandidate.destination.vertex);
				DTEdge newDte = createDTEdge(e, dte.origin, rightCandidate.destination);
				addDTEdge(newDte, dte, dte.getCcw(), rightCandidate.getReverse().getCw(), rightCandidate.getReverse());
				merge(g, newDte);
			}
		}
	}
	/**
	 * This method gets the potential candidate while merging two sub graphs
	 * @param g the graph
	 * @param dte the base edge
	 * @param right a boolean to ensure that it checking the right vertex of the edge.
	 * @return the potential candidate. null if there is no candidate
	 */
	private DTEdge getCandidate(Graph g, DTEdge dte, boolean right) {
		if (right) {
			DTEdge current = dte.getCw();
			DTEdge next = current.getCw();
			if (orientation(dte.destination.vertex, dte.origin.vertex, current.destination.vertex) <= 0) {
				return null;
			}
			if (next.equals(dte)) {
				return current;
			}
			while (!next.equals(dte) && orientation(dte.destination.vertex, dte.origin.vertex, current.destination.vertex) > 0) {
				if (!inCircle(dte.destination.vertex, dte.origin.vertex, current.destination.vertex, next.destination.vertex)) {
					return current;
				} else {
					g.removeEdge(current.edge);
					removeDTEdge(current);
					current = next;
					next = current.getCw();
				}
			}
			if (orientation(dte.destination.vertex, dte.origin.vertex, current.destination.vertex) <= 0) {
				return null;
			} else {
				return current;
			}
		} else {
			DTEdge current = dte.getCcw();
			DTEdge next = current.getCcw();
			if (orientation(dte.origin.vertex, dte.destination.vertex, current.destination.vertex) <= 0) {
				return null;
			}
			if (next.equals(dte)) {
				return current;
			}
			while (!next.equals(dte) && orientation(dte.origin.vertex, dte.destination.vertex, current.destination.vertex) > 0) {
				if (!inCircle(dte.origin.vertex, dte.destination.vertex, current.destination.vertex, next.destination.vertex)) {
					return current;
				} else {
					g.removeEdge(current.edge);
					removeDTEdge(current);
					current = next;
					next = current.getCcw();
				}
			}
			if (orientation(dte.origin.vertex, dte.destination.vertex, current.destination.vertex) <= 0) {
				return null;
			} else {
				return current;
			}
		}
	}
	
	/**
	 * This method partitions elements in an array by placing the
	 * kth element in the center of the list and placing lower
	 * elements to the left, ane higher elements to the right.
	 * @param array the array of DTVertex objects
	 * @param low the low index
	 * @param high the high index
	 * @param k the index of the element to place in the center
	 */
	private void quickselect(DTVertex[] array, int low, int high, int k) {
		while (true) {
			if (high == low) {
				break;
			}
			int pos = partition(array, low, high);
			if (k == pos) {
				break;
			} else if (k < pos) {
				high = pos - 1;
			} else {
				low = pos + 1;
			}
		}
	}
	
	/**
	 * This partitions elements
	 * @param array the DTVertex array
	 * @param low the low index
	 * @param high the high index
	 * @return the index of the value
	 */
	private int partition(DTVertex[] array, int low, int high) {
		DTVertex pivot = array[high];
		int index = low;
		for (int i = low; i <= high - 1; i++) {
			if (array[i].vertex.getX() < pivot.vertex.getX()) {
				swap(array, index, i);
				index++;
				
			} else if (array[i].vertex.getX() == pivot.vertex.getX()) {
				if (array[i].vertex.getY() >= pivot.vertex.getY()) {
					swap(array, index, i);
					index++;
				}
			}
		}
		swap(array, index, high);
		return index;
	}
	
	/**
	 * This swaps two elements
	 * @param array the DTVertex array
	 * @param one the first element
	 * @param two the second element
	 */
	private void swap(DTVertex[] array, int one, int two) {
		DTVertex temp = array[two];
		array[two] = array[one];
		array[one] = temp;
	}
	
	/**
	 * Positive if counter clockwise, Negative if clockwise, 0 if colinear.
	 * This is done by taking the determinant of a matrix.
	 * @param l1 The first vertex
	 * @param l2 The second vertex
	 * @param l3 The third vertex
	 * @return the determinant
	 */
	private double orientation(Location l1, Location l2, Location l3) { //O(1)
		double m11 = l1.getX() - l3.getX();
		double m12 = l1.getY() - l3.getY();
		double m21 = l2.getX() - l3.getX();
		double m22 = l2.getY() - l3.getY();
		
		double det = (m11 * m22) - (m12 * m21);
		return det;
	}
	/**
	 * Assumes l1, l2, and l3 in counter clockwise order. Checks if 
	 * a vertex is in the subcircle of three vertices. This is done by 
	 * taking the determinant of a matrix.
	 * @param l1 the vertex that creates the subcircle
	 * @param l2 the vertex that creates the subcircle
	 * @param l3 the vertex that creates the subcircle
	 * @param candidate the vertex to check
	 * @return true if the vertex is in the subcircle
	 */
	private boolean inCircle(Location l1, Location l2, Location l3, Location candidate) { //O(1)
		double m11 = l1.getX() - candidate.getX();
		double m12 = l1.getY() - candidate.getY();
		double m13 = Math.pow(m11, 2) + Math.pow(m12, 2);
		double m21 = l2.getX() - candidate.getX();
		double m22 = l2.getY() - candidate.getY();
		double m23 = Math.pow(m21, 2) + Math.pow(m22, 2);
		double m31 = l3.getX() - candidate.getX();
		double m32 = l3.getY() - candidate.getY();
		double m33 = Math.pow(m31, 2) + Math.pow(m32, 2);
		
		double det = (m11 * ((m22 * m33) - (m32 * m23))) - (m12 * ((m21 * m33) - (m31 * m23))) + (m13 * ((m21 * m32) - (m31 * m22)));
		return det > 0;
	}
	
	/**
	 * This method adds references to other clockwise and counter clockwise edges in
	 * another DTEdge
	 * @param edge the edge
	 * @param cwOrigin the clockwise edge from the origin
	 * @param ccwOrigin the counter clockwise edge from the origin
	 * @param cwDestination the clockwise edge from the destination
	 * @param ccwDestination the counter clockwise edge from the destination
	 */
	private void addDTEdge(DTEdge edge, DTEdge cwOrigin, DTEdge ccwOrigin, DTEdge cwDestination, DTEdge ccwDestination) {
		edge.setCw(cwOrigin);
		edge.setCcw(ccwOrigin);
		cwOrigin.setCcw(edge);
		ccwOrigin.setCw(edge);
		edge.getReverse().setCw(cwDestination);
		edge.getReverse().setCcw(ccwDestination);
		cwDestination.setCcw(edge.getReverse());
		ccwDestination.setCw(edge.getReverse());
		
	}
	
	/**
	 * This removes the references of edges in a DTEdge
	 * @param edge the edge
	 */
	private void removeDTEdge(DTEdge edge) {
		DTEdge temp = edge.getCcw();
		edge.getCw().setCcw(temp);
		temp.setCw(edge.getCw());
		temp = edge.reverse.getCcw();
		edge.reverse.getCw().setCcw(temp);
		temp.setCw(edge.reverse.getCw());
	}
	
	/**
	 * Constructs a DTEdge and the reverse of a DTEdge
	 * @param edge the edge to be contained
	 * @param origin the origin
	 * @param destination the destination
	 * @return the constructed DTEdge
	 */
	private DTEdge createDTEdge(Weight edge, DTVertex origin, DTVertex destination) {
		DTEdge dte = new DTEdge(edge, origin, destination);
		DTEdge reverse = new DTEdge(edge, destination, origin);
		dte.reverse = reverse;
		reverse.reverse = dte;
		return dte;
	}
	
	/**
	 * Inner class to contain a reference to a vertex, and an edge to start from.
	 * @author devonasmus
	 *
	 */
	private class DTVertex {
		Location vertex;
		DTEdge first;
		
		public DTVertex(Location vertex) {
			this.vertex = vertex;
		}
		
		public void setFirst(DTEdge first) {
			this.first = first;
		}
		
		public DTEdge getFirst() {
			return first;
		}
	}
	
	
	/**
	 * Inner cass to contain a graph edge and contain references to the next
	 * clockwise, counter clockwise, and reverse of an edge.
	 * @author devonasmus
	 *
	 */
	private class DTEdge {
		private Weight edge;
		private DTVertex origin;
		private DTVertex destination;
		private DTEdge cw;
		private DTEdge ccw;
		private DTEdge reverse;
		
		public DTEdge(Weight edge, DTVertex origin, DTVertex destination) {
			this.edge = edge;
			this.origin = origin;
			this.destination = destination;
		}
		
		public void setCw(DTEdge cw) {
			this.cw = cw;
		}
		
		public void setCcw(DTEdge ccw) {
			this.ccw = ccw;
		}
		
		public DTEdge getCw() {
			return cw;
		}
		
		public DTEdge getCcw() {
			return ccw;
		}
		
		public DTEdge getReverse() {
			return reverse;
		}
	}
}
