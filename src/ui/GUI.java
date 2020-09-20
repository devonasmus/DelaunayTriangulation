package ui;

import static org.junit.Assert.assertFalse;

//import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
//import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
//import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.awt.geom.*;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;

import algorithm.Delaunay;
import graph.Graph;
import graph.Location;
import graph.Weight;

public class GUI implements ActionListener {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private Graph graph = new Graph();
	private ArrayList<Integer[]> circleList =  new ArrayList<Integer[]>();
	JButton b;
	JFrame f;
	public GUI() {
		f = new JFrame();
		f.setSize(900, 700);
		b = new JButton();
		b.setBounds(400, 600, 100, 50);
		b.addActionListener(this);
		//f.setLayout(null);
		//f.add(b);
		f.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				double xCor = Double.valueOf(e.getX());
				double yCor = Double.valueOf(e.getY());
				graph.addVertex(new Location(xCor, yCor));
				addCircle(e.getX(), e.getY());
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		//f.setLayout(null);
	    
		f.setVisible(true);
	}
	
	public void addCircle(int x, int y) {
		JPanel p = new JPanel() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				//Shape line = new Line2D.Double(3, 3, 303, 303);
				for (int i = 0; i < circleList.size(); i++) {
					Shape prevCircles = new Ellipse2D.Double(circleList.get(i)[0], circleList.get(i)[1], 10, 10);
					g2.draw(prevCircles);
				}
				Shape circle = new Ellipse2D.Double(x, y, 10, 10);
				Integer[] coor = new Integer[2];
				coor[0] = x; coor[1] = y;
				circleList.add(coor);
				g2.draw(circle);
			}
			
		};
		p.setLayout(null);
		p.setVisible(true);
		f.getContentPane().add(p);
		p.add(b);
		f.setVisible(true);
		System.out.print("<" + x + ", " + y + ">\n");
		
	}
	
	public void addLines() {
		JPanel p = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				//Shape line = new Line2D.Double(3, 3, 303, 303);
				for (int i = 0; i < circleList.size(); i++) {
					Shape prevCircles = new Ellipse2D.Double(circleList.get(i)[0], circleList.get(i)[1], 10, 10);
					g2.draw(prevCircles);
				}
				for (Weight w: graph.getEdges()) {
					Shape line = new Line2D.Double(w.getEndVertices()[0].getX(), w.getEndVertices()[0].getY(), w.getEndVertices()[1].getX(), w.getEndVertices()[1].getY());
					g2.draw(line);
				}
			}
		};
		
		f.getContentPane().add(p);
		p.setVisible(true);
		f.setVisible(true);
		//System.out.print("sefedfde");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GUI();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(b)) {
			Delaunay d = new Delaunay();
			d.delaunay(graph);
			addLines();
			testLoop();
		}
		
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
						//assertFalse(inCircle(e.getEndVertices()[0], e.getEndVertices()[1], lowest1.getValue(), p));
						if (inCircle(e.getEndVertices()[0], e.getEndVertices()[1], lowest1.getValue(), p)) {
							System.out.println("Failed");
						}
					} else {
						//assertFalse(inCircle(e.getEndVertices()[1], e.getEndVertices()[0], lowest1.getValue(), p));
						if (inCircle(e.getEndVertices()[1], e.getEndVertices()[0], lowest1.getValue(), p)) {
							System.out.println("Failed");
						}
					}
				}
				if (lowest2 != null) {
					if (counterClockwise(e.getEndVertices()[0], e.getEndVertices()[1], lowest2.getValue())) {
						//assertFalse(inCircle(e.getEndVertices()[0], e.getEndVertices()[1], lowest2.getValue(), p));
						if (inCircle(e.getEndVertices()[0], e.getEndVertices()[1], lowest2.getValue(), p)) {
							System.out.println("Failed");
						}
					} else {
						//assertFalse(inCircle(e.getEndVertices()[1], e.getEndVertices()[0], lowest2.getValue(), p));
						if (inCircle(e.getEndVertices()[1], e.getEndVertices()[0], lowest2.getValue(), p)) {
							System.out.println("Failed");
						}
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
