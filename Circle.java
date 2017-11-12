public class Circle extends Ellipse {

	public Circle(String color, Point center, double radius, boolean count) {
		//ellipse with focus1 = focus2 is a circle
		super(color, center, new Point(center), 2* radius, count); 
	}
	public Circle(String[] arguments) {
		this(	arguments[2],
				new Point(arguments[3],arguments[4]),
				Double.parseDouble(arguments[5]),
				true); 
	}
	
	public double getRadius() {
		return super.getD()/2;
	}
	public Point getCenter() {
		return this.getFocuses()[0];
	}
	
	@Override
	public Shape copy() {
		Point[] vertices = Point.copyPoints(this.getVertices());
		return new Circle(this.getColor(), vertices[0], this.getRadius(), true);
	}
}
