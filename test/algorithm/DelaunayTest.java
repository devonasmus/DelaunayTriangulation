package algorithm;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import graph.Graph;
import graph.Location;
import graph.Weight;

public class DelaunayTest {

	Graph graph = new Graph();
	@Test
	public void test() {
//		fail("Not yet implemented");
		Location l1 = new Location(0, 0.5);
		Location l2 = new Location(0, -0.5);
		Location l3 = new Location(1, 0);
		Location l4 = new Location(-2, 0);
		Location l5 = new Location(-2, -0.5);
		graph.addVertex(l1);
		graph.addVertex(l2);
		graph.addVertex(l3);
		graph.addVertex(l4);
		graph.addVertex(l5);
		Delaunay d = new Delaunay();
		d.delaunay(graph);
		testLoop();
	}
	
	@Test
	public void test2() {
		Location l1 = new Location(0, 0);
		Location l2 = new Location(1, 0.5);
		Location l3 = new Location(0.5, 1);
		Location l4 = new Location(4, 0.5);
		Location l5 = new Location(5, 1);
		Location l6 = new Location(4.5, 1.5);
		graph.addVertex(l1);
		graph.addVertex(l2);
		graph.addVertex(l3);
		graph.addVertex(l4);
		graph.addVertex(l5);
		graph.addVertex(l6);
		Delaunay d = new Delaunay();
		d.delaunay(graph);
		testLoop();
	}
	
	@Test
	public void secondTest() {
		Location l1 = new Location(0, 0);
		Location l2 = new Location(1, 1);
		Location l3 = new Location(5, 0);
		Location l4 = new Location(4, 5);
		Location l5 = new Location(5, 5);
		graph.addVertex(l1);
		graph.addVertex(l2);
		graph.addVertex(l3);
		graph.addVertex(l4);
		graph.addVertex(l5);
		Delaunay d = new Delaunay();
		d.delaunay(graph);
		testLoop();
	}
	
	@Test
	public void thirdTest() {
		Location l1 = new Location(238, 248);
		Location l2 = new Location(501, 286);
		Location l3 = new Location(330, 431);
		Location l4 = new Location(436, 174);
		Location l5 = new Location(667, 402);
		Location l6 = new Location(623, 213);
		graph.addVertex(l1);
		graph.addVertex(l2);
		graph.addVertex(l3);
		graph.addVertex(l4);
		graph.addVertex(l5);
		graph.addVertex(l6);
		Delaunay d = new Delaunay();
		d.delaunay(graph);
		testLoop();
	}
	
	@Test
	public void fourthTest() {
		Location l1 = new Location(187, 313);
		Location l2 = new Location(235, 202);
		Location l3 = new Location(226, 492);
		Location l4 = new Location(475, 320);
		Location l5 = new Location(357, 318);
		Location l6 = new Location(764, 542);
		graph.addVertex(l1);
		graph.addVertex(l2);
		graph.addVertex(l3);
		graph.addVertex(l4);
		graph.addVertex(l5);
		graph.addVertex(l6);
		Delaunay d = new Delaunay();
		d.delaunay(graph);
		testLoop();
	}
	
	@Test
	public void fifthTest() {
		Location l1 = new Location(0, 0);
		Location l2 = new Location(12, 0);
		Location l3 = new Location(-1, 1);
		Location l4 = new Location(-2, 2);
		graph.addVertex(l1);
		graph.addVertex(l2);
		graph.addVertex(l3);
		graph.addVertex(l4);
		Delaunay d = new Delaunay();
		d.delaunay(graph);
		testLoop();
	}
	
	@Test
	public void sixthTest() {
		Location l1 = new Location(307, 338);
		Location l2 = new Location(622, 483);
		Location l3 = new Location(676, 302);
		Location l4 = new Location(437, 307);
		Location l5 = new Location(520, 164);
		graph.addVertex(l1);
		graph.addVertex(l2);
		graph.addVertex(l3);
		graph.addVertex(l4);
		graph.addVertex(l5);
		Delaunay d = new Delaunay();
		d.delaunay(graph);
		testLoop();
	}
	
	@Test
	public void seventhTest() {
		Location l1 = new Location(177, 351); //Check
		Location l2 = new Location(349, 279); //Check
		Location l3 = new Location(211, 218); //Check
		Location l4 = new Location(164, 202); //Check
		Location l5 = new Location(311, 122); //Check
		Location l6 = new Location(509, 85); //Check
		Location l7 = new Location(614, 249); //Check
		Location l8 = new Location(525, 354); //Check
		Location l9 = new Location(431, 469); //Check
		Location l10 = new Location(267, 509); //Check
		Location l11 = new Location(173, 517); //Check
		Location l12 = new Location(82, 485); //Check
		Location l13 = new Location(284, 407); //Check
		Location l14 = new Location(507, 273); //Check
		Location l15 = new Location(445, 222); //Check
		Location l16 = new Location(424, 99); //Check
		Location l17 = new Location(736, 35); //Check
		Location l18 = new Location(642, 132); //Check
		Location l19 = new Location(807, 158); //Check
		Location l20 = new Location(687, 394); //Check
		Location l21 = new Location(820, 419); //Check
		Location l22 = new Location(783, 305); //Check
		Location l23 = new Location(600, 510); //Check
		Location l24 = new Location(792, 621); //Check
		graph.addVertex(l1);
		graph.addVertex(l2);
		graph.addVertex(l3);
		graph.addVertex(l4);
		graph.addVertex(l5);
		graph.addVertex(l6);
		graph.addVertex(l7);
		graph.addVertex(l8);
		graph.addVertex(l9);
		graph.addVertex(l10);
		graph.addVertex(l11);
		graph.addVertex(l12);
		graph.addVertex(l13);
		graph.addVertex(l14);
		graph.addVertex(l15);
		graph.addVertex(l16);
		graph.addVertex(l17);
		graph.addVertex(l18);
		graph.addVertex(l19);
		graph.addVertex(l20);
		graph.addVertex(l21);
		graph.addVertex(l22);
		graph.addVertex(l23);
		graph.addVertex(l24);
		Delaunay d = new Delaunay();
		d.delaunay(graph);
		testLoop();
	}
	
	@Test
	public void eighthTest() {
		Location l1 = new Location(332, 195);
		Location l2 = new Location(236, 234);
		Location l3 = new Location(224, 363);
		Location l4 = new Location(324, 371);
		Location l5 = new Location(488, 372);
		Location l6 = new Location(488, 208);
		Location l7 = new Location(345, 95);
		Location l8 = new Location(93, 148);
		Location l9 = new Location(99, 333);
		Location l10 = new Location(148, 452);
		Location l11 = new Location(235, 89);
		Location l12 = new Location(610, 120);
		Location l13 = new Location(761, 92);
		Location l14 = new Location(534, 90);
		Location l15 = new Location(741, 270);
		Location l16 = new Location(630, 358);
		Location l17 = new Location(697, 211);
		Location l18 = new Location(830, 419);
		Location l19 = new Location(671, 494);
		Location l20 = new Location(499, 504);
		Location l21 = new Location(325, 556);
		Location l22 = new Location(307, 473);
		Location l23 = new Location(175, 564);
		graph.addVertex(l1);
		graph.addVertex(l2);
		graph.addVertex(l3);
		graph.addVertex(l4);
		graph.addVertex(l5);
		graph.addVertex(l6);
		graph.addVertex(l7);
		graph.addVertex(l8);
		graph.addVertex(l9);
		graph.addVertex(l10);
		graph.addVertex(l11);
		graph.addVertex(l12);
		graph.addVertex(l13);
		graph.addVertex(l14);
		graph.addVertex(l15);
		graph.addVertex(l16);
		graph.addVertex(l17);
		graph.addVertex(l18);
		graph.addVertex(l19);
		graph.addVertex(l20);
		graph.addVertex(l21);
		graph.addVertex(l22);
		graph.addVertex(l23);
		//graph.addVertex(l24);
		Delaunay d = new Delaunay();
		d.delaunay(graph);
		testLoop();
	}
	
	@Test
	public void ninthTest() {
		Location l1 = new Location(178, 315); //Check
		Location l2 = new Location(454, 291); //Check
		Location l3 = new Location(378, 243); //Check
		Location l4 = new Location(293, 265); //Check
		Location l5 = new Location(190, 137); //Check
		Location l6 = new Location(328, 117); //Check
		Location l7 = new Location(316, 347); //Check
		Location l8 = new Location(486, 171); //Check
		Location l9 = new Location(446, 148); //Check
		Location l10 = new Location(629, 71); //Check
		Location l11 = new Location(607, 295); //Check
		graph.addVertex(l1);
		graph.addVertex(l2);
		graph.addVertex(l3);
		graph.addVertex(l4);
		graph.addVertex(l5);
		graph.addVertex(l6);
		graph.addVertex(l7);
		graph.addVertex(l8);
		graph.addVertex(l9);
		graph.addVertex(l10);
		graph.addVertex(l11);
		Delaunay d = new Delaunay();
		d.delaunay(graph);
		testLoop();
	}
	
	@Test
	public void tenthTest() {
		Location l1 = new Location(622, 217);
		Location l2 = new Location(403, 217);
		Location l3 = new Location(175, 298);
		Location l4 = new Location(412, 387); //Check
		Location l5 = new Location(515, 347);
		Location l6 = new Location(481, 157);
		Location l7 = new Location(327, 157);
		Location l8 = new Location(304, 387); //Check
		Location l9 = new Location(190, 532);
		Location l10 = new Location(125, 191);
		graph.addVertex(l1);
		graph.addVertex(l2);
		graph.addVertex(l3);
		graph.addVertex(l4);
		graph.addVertex(l5);
		graph.addVertex(l6);
		graph.addVertex(l7);
		graph.addVertex(l8);
		graph.addVertex(l9);
		graph.addVertex(l10);
		Delaunay d = new Delaunay();
		d.delaunay(graph);
		testLoop();
	}
	
	
//	<622, 217>
//	<403, 217>
//	<175, 298>
//	<412, 387>
//	<515, 347>
//	<481, 157>
//	<327, 157>
//	<304, 387>
//	<190, 532>
//	<125, 191>
	
	@Test
	public void randomTest() {
		for (int i = 0; i < 100; i++) {
			graph.addVertex(new Location(Math.random() * 200, Math.random() * 200));
		}
		Delaunay d = new Delaunay();
		d.delaunay(graph);
		testLoop();
	}

	
	private void testLoop() {
		for (Weight e: graph.getEdges()) {
			//Location c;
			Map<Double, Location> side1 = new HashMap<Double, Location>();
			Map<Double, Location> side2 = new HashMap<Double, Location>();
			for (Location l: graph.getVertices()) {
				if (graph.getEdge(e.getEndVertices()[0], l) != null && graph.getEdge(e.getEndVertices()[1], l) != null) {
					if (getDistance(e, l) > 0) {
						side1.put(getDistance(e, l), l);
					} else {
						side2.put(-getDistance(e, l), l);
					}
				}
			}
			Entry<Double, Location> lowest1 = null;
			Entry<Double, Location> lowest2 = null;
			for (Entry<Double, Location> n: side1.entrySet()) {
				if (lowest1 == null) {
					lowest1 = n;
				}
				if (n.getKey() < lowest1.getKey()) {
					lowest1 = n;
				}
			}
			for (Entry<Double, Location> n: side2.entrySet()) {
				if (lowest2 == null) {
					lowest2 = n;
				}
				if (n.getKey() < lowest2.getKey()) {
					lowest2 = n;
				}
			}
			for (Location p: graph.getVertices()) {
				if (lowest1 != null) {
					if (counterClockwise(e.getEndVertices()[0], e.getEndVertices()[1], lowest1.getValue())) {
						assertFalse(inCircle(e.getEndVertices()[0], e.getEndVertices()[1], lowest1.getValue(), p));
					} else {
						assertFalse(inCircle(e.getEndVertices()[1], e.getEndVertices()[0], lowest1.getValue(), p));
					}
				}
				if (lowest2 != null) {
					if (counterClockwise(e.getEndVertices()[0], e.getEndVertices()[1], lowest2.getValue())) {
						assertFalse(inCircle(e.getEndVertices()[0], e.getEndVertices()[1], lowest2.getValue(), p));
					} else {
						assertFalse(inCircle(e.getEndVertices()[1], e.getEndVertices()[0], lowest2.getValue(), p));
					}
				}
			}
			
		}
	}
	
	private double getDistance(Weight w, Location l) {
		double horizontalDistance = 0.0;
		double verticalDistance = 0.0;
		if (w.getEndVertices()[0].getX() > w.getEndVertices()[1].getX()) {
			horizontalDistance = w.getEndVertices()[0].getX() - w.getEndVertices()[1].getX();
			verticalDistance = w.getEndVertices()[0].getY() - w.getEndVertices()[1].getY();
		} else if (w.getEndVertices()[0].getX() < w.getEndVertices()[1].getX()) {
			horizontalDistance = w.getEndVertices()[1].getX() - w.getEndVertices()[0].getX();
			verticalDistance = w.getEndVertices()[1].getY() - w.getEndVertices()[0].getY();
		} else {
			return l.getX() - w.getEndVertices()[0].getX();
		}
		double slope = verticalDistance / horizontalDistance;
		if (slope == 0.0) {
			return l.getY() - w.getEndVertices()[0].getY();
		}
		double vSlope = -(1 / slope);
		double yIntercept = w.getEndVertices()[0].getY() - (slope * w.getEndVertices()[0].getX());
		double vYintercept = l.getY() - (vSlope * l.getX());
		double leftSide = slope - vSlope;
		double rightSide = vYintercept - yIntercept;
		double x = rightSide / leftSide;
		double y = (slope * x) + yIntercept;
		
		double newHorizontalDistance = l.getX() - x;
		double newVerticalDistance = l.getY() - y;
		double distance = Math.sqrt(Math.pow(newHorizontalDistance, 2) + Math.pow(newVerticalDistance, 2));
		if (newVerticalDistance < 0) {
			return -distance;
		}
		
		return distance;
	}

	private boolean counterClockwise(Location l1, Location l2, Location l3) {
		double m11 = l1.getX() - l3.getX();
		double m12 = l1.getY() - l3.getY();
		double m21 = l2.getX() - l3.getX();
		double m22 = l2.getY() - l3.getY();
		
		double det = (m11 * m22) - (m12 * m21);
		return det > 0;
	}
	
	private boolean inCircle(Location l1, Location l2, Location l3, Location candidate) {
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
}
