public class Trapezoid extends Quadrilateral{

	public Trapezoid(String color, Point p1, Point p2, Point p3, Point p4, boolean count) {
		super(color, p1, p2, p3, p4, count);
	}
	public Trapezoid(String[] arguments) {
		this( 	arguments[2],
				new Point(arguments[3],arguments[4]),
				new Point(arguments[5],arguments[6]),
				new Point(arguments[7],arguments[8]),
				new Point(arguments[9],arguments[10]),
				true);
	}
	
	@Override
	public Shape copy() {
		Point[] vertices = Point.copyPoints(this.getVertices());
		return new Trapezoid(this.getColor(),
				vertices[0], vertices[1], vertices[2], vertices[3],
				true);
	}
}
