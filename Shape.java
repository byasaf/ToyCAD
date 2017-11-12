public abstract class Shape {
	
	private String color;
	private Point[] vertices;
	private int	shapeID;
	private static int ID = 0;
		
	public Shape(String color, Point[] vertices, boolean count) {
		this.color = color;
		this.vertices = vertices;
		if(count) {
			this.shapeID = ID;
			ID++;		
		}
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Point[] getVertices() {
		return this.vertices;
	}
	public Point[] getOuterVertices(){	
		return this.vertices;
	}
	public int getID() {
		return this.shapeID;
	}
	
	public void move(double dx, double dy) {
		for(int i=0 ; i < this.vertices.length ; i++) {
			this.vertices[i].move(dx, dy);
		}
	}
	public abstract Shape copy();
	public abstract double getArea();
	public abstract double getCircumference();
	public abstract boolean isInside(Point point);
	
}
