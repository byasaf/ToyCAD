public class Parallelogram extends Quadrilateral {

	public Parallelogram(String color, Point p1, Point p2, Point p3, boolean count) {
		super(color,p1,p2,p3,createForthPoint(p1, p2, p3), count);	
	}
	public Parallelogram(String[] arguments) {
		this(	arguments[2],
				new Point(arguments[3],arguments[4]),
				new Point(arguments[5],arguments[6]),
				new Point(arguments[7],arguments[8]),
				true);
	}
			
	private static Point createForthPoint(Point p1, Point p2, Point p3) {	
		// diagonals of parallelogram bisect each other at "o"
		Point o = new Point( (p1.getX()+p3.getX())/2 , (p1.getY()+p3.getY())/2 ); 
		return new Point(2*o.getX()-p2.getX(),2*o.getY()-p2.getY());
	}

	@Override
	public Shape copy() {
		Point[] vertices = Point.copyPoints(this.getVertices());
		return new Parallelogram(this.getColor(),
				vertices[0], vertices[1], vertices[2],true);
	}	

}
