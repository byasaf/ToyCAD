import java.util.Arrays;

public class Ellipse extends Shape{
	private double a;
	private double b;
	
	public Ellipse(String color, Point focus1, Point focus2, double D, boolean count) {
		super(color, new Point[] {focus1, focus2} ,count);
		
		Point[] focuses = this.getFocuses();
		double focusesHalfDistance = 0.5*focuses[0].distance(focuses[1]);
		
		this.a = D/2;
		this.b = Math.sqrt( a * a - focusesHalfDistance * focusesHalfDistance);
	}
	public Ellipse(String[] arguments) {
		this(	arguments[2], 
				new Point(arguments[3],arguments[4]), 
				new Point(arguments[5],arguments[6]),
				Double.parseDouble(arguments[7]),
				true);
	}

	public double getA() {
		return a;
	}
	public double getD() {
		return 2*a;
	}	
	public Point[] getFocuses() {
		return super.getVertices();
	}	
	public Point[] getOuterVertices(){	
		Point center = getFocuses()[0].getMiddlePoint(getFocuses()[1]);
		Point[] vertices = new Point[4];
		for(int i=0 ; i<vertices.length ; i++){
			vertices[i] = new Point(center);
		}
		double maxDistance = this.getA();
		vertices[0].move(0,maxDistance);
		vertices[1].move(0,-maxDistance);
		vertices[2].move(maxDistance, 0);
		vertices[3].move(-maxDistance, 0);	
		
		return vertices;
	}
	
	public double getArea() {
		return Math.PI*a*b;
	}	
	public double getCircumference() {
		double h = Math.pow(a-b, 2)/Math.pow(a+b, 2);
		return Math.PI*(a+b)*(1+(3*h)/(10+Math.sqrt(4-3*h)));
	}
	public boolean isInside(Point point) {
		double pointToFocus1Distance = point.distance(getFocuses()[0]);
		double pointToFocus2Distance = point.distance(getFocuses()[1]);
		if  (pointToFocus1Distance + pointToFocus2Distance <= getD())
			return true;
		else return false;
	}
	@Override
	public Shape copy() {
		Point[] vertices = Point.copyPoints(this.getVertices());
		return new Ellipse(this.getColor(),vertices[0],vertices[1],
				this.getD(),true);
	}	
}