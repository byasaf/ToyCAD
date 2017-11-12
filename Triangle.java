public class Triangle extends Polygon {

	public Triangle(String color, Point p1, Point p2, Point p3, boolean count) {
		super(color, new Point[] {p1,p2,p3}, count);
	}
	public Triangle(String[] arguments) {
		this(arguments[2],
				new Point(arguments[3],arguments[4]),
				new Point(arguments[5],arguments[6]),
				new Point(arguments[7],arguments[8]),
				true);
	}
		
	public double getArea() {
		Point[] vertices = super.getVertices();
		Point p1 = vertices[0];
		Point p2 = vertices[1];
		Point p3 = vertices[2];	
		double area = 0.5*(	p1.getX()*(p2.getY()-p3.getY())+
							p2.getX()*(p3.getY()-p1.getY())+
							p3.getX()*(p1.getY()-p2.getY()));
		return Math.abs(area);
	}
	public boolean isInside(Point point) {
		Point[] vertices = super.getVertices();
		Point p1 = vertices[0];
		Point p2 = vertices[1];
		Point p3 = vertices[2];	
		boolean b1, b2, b3;	
		
		//point is inside if it is found to left side of the three vectors: p1-p2,p2-p3,p3-p1
		b1 = point.isLeftToVector(p1, p2);
		b2 = point.isLeftToVector(p2, p3);
		b3 = point.isLeftToVector(p3, p1);

		return ((b1 == b2) && (b2 == b3));
	}	
	@Override
	public Shape copy() {
		Point[] vertices = Point.copyPoints(this.getVertices());
		return new Triangle(this.getColor(),
				vertices[0], vertices[1], vertices[2],
				true);
	}
}
