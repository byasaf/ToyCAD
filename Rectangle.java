public class Rectangle extends Parallelogram {

	public Rectangle(String color, Point p1, Point p2, boolean count) {
		super(color, p1, new Point(p1.getX(),p2.getY()), p2, count);
	}
	public Rectangle(String[] arguments) {
	this(	arguments[2],
			new Point(arguments[3],arguments[4]),
			new Point(arguments[5],arguments[6]),
			true);
	}
	
	@Override
	public Shape copy() {
		Point[] vertices = Point.copyPoints(this.getVertices());
		return new Rectangle(this.getColor(), vertices[0], vertices[2],true);
	}	
}
