package graph;

public class Weight {
	private double distance;
	private Location[] endVertices;
	public Weight(Location l1, Location l2, double distance) {
		this.distance = distance;
		endVertices = new Location[2];
		endVertices[0] = l1;
		endVertices[1] = l2;
	}
	public double getDistance() {
		return distance;
	}
	public Location[] getEndVertices() {
		return endVertices;
	}
	
	public Location getOpposite(Location location) {
		if (location.equals(endVertices[0])) {
			return endVertices[1];
		} else if (location.equals(endVertices[1])) {
			return  endVertices[0];
		} else {
			return null;
		}
	}
}
