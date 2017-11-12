public class Square extends Rectangle {

	public Square(String color, Point center, double edgeLength, boolean count) {
		super(color, new Point(center.getX()+edgeLength/2,center.getY()+edgeLength/2) ,
				new Point(center.getX()-edgeLength/2,center.getY()-edgeLength/2)
				,count);
	}
	public Square(String[] arguments) {
		this(	arguments[2],	
				new Point(arguments[3],arguments[4]),
				Double.parseDouble(arguments[5]),
				true);
	}
	
	@Override
	public Shape copy() {
		Point[] vertices = Point.copyPoints(this.getVertices());
		return new Square(this.getColor(), vertices[0].getMiddlePoint(vertices[2]), 
				Math.abs( vertices[0].getX()-vertices[2].getX() ),true);
	}	
}
