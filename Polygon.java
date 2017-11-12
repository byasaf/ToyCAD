public abstract class Polygon extends Shape {

	public Polygon(String color, Point[] vertices, boolean count) {
		super(color, vertices, count);
	}
	
	public double getCircumference(){
		double retVal = 0;
		Point[] vertices = this.getVertices();
		for(int i = 0 ; i<vertices.length ; i++) {
			retVal += vertices[i].distance(vertices[(i+1)%vertices.length]);
		}
		return retVal;
	}

	public String toString() {
		Point[] vertices = this.getVertices();
		String retString = "";
		int i;
		for(i = 0; i < vertices.length-1 ; i++) {
			retString = retString + "p" + i + "="  + vertices[i] + ", ";
		}
		retString = retString + "p" + i + "="  + vertices[i];
		retString = retString + ", Area= " + this.getArea() + ", Circum= "  + this.getCircumference();
		return retString;
	}	
}
