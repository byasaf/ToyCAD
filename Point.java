import java.awt.geom.Point2D;

public class Point extends Point2D.Double {
	private static final long serialVersionUID = 1L;
	
	public Point() {
		super(0, 0);
	}
	public Point(double x, double y) {
		super(x, y);
	}
	public Point(String x, String y) {
		super(java.lang.Double.parseDouble(x), java.lang.Double.parseDouble(y));
	}
	public Point(Point point) {
		super(point.getX(),point.getY());
	}
	
	public static Point[] copyPoints(Point[] srcPoints) {
		Point[] dstPoints = new Point[srcPoints.length];
		for(int i = 0; i < srcPoints.length; i++) {
			dstPoints[i] = new Point(srcPoints[i]);
		}
		return dstPoints;
	}
	
	public Point move(double dx, double dy) {
		this.setLocation(this.getX()+dx, this.getY()+dy);
		return this;
	}
	public Point getMiddlePoint(Point other){
		double x = 0.5*(this.getX()+other.getX());
		double y = 0.5*(this.getY()+other.getY());
		return new Point(x,y);
	}	
	public boolean equals(Point other){
		return this.x == other.getX() && this.y == other.getY();
	}	
	public boolean isLeftToVector(Point p1, Point p2)
	{
		//Dot product
	    if(	 (this.getX() - p2.getX()) * (	p1.getY()	- p2.getY()) 
	    	-(	p1.getX() - p2.getX()) * (this.getY()	- p2.getY()) < 0) {
	    	return true;
	    }
	    else return false;
	}
	
	public static Point getPointWithMaxX(Point[] points) {
		Point max = new Point(java.lang.Double.NEGATIVE_INFINITY,0);
		for(int i = 0; i < points.length; i++) {
			if( points[i].getX() >= max.getX()) {
				max = points[i];
			}
		}
		return max;
	}
	public static Point getPointWithMinX(Point[] points) {
		Point min = new Point(java.lang.Double.POSITIVE_INFINITY,0);
		for(int i = 0; i < points.length; i++) {
			if( points[i].getX() <= min.getX()) {
				min = points[i];
			}
		}
		return min;
	}
	public static Point getPointWithMaxY(Point[] points) {
		Point max = new Point(0,java.lang.Double.NEGATIVE_INFINITY);
		for(int i = 0; i < points.length; i++) {
			if( points[i].getY() >= max.getY()) {
				max = points[i];
			}
		}
		return max;
	}
	public static Point getPointWithMinY(Point[] points) {
		Point min = new Point(0,java.lang.Double.POSITIVE_INFINITY);
		for(int i = 0; i < points.length; i++) {
			if( points[i].getY() <= min.getY()) {
				min = points[i];
			}
		}
		return min;
	}
	
	public String toString() {
		return String.format("(%.2f,%.2f)",x,y);
	}
}

