public abstract class Quadrilateral extends Polygon {
	
	public Quadrilateral(String color, Point p1, Point p2, Point p3, Point p4, boolean count) {
		super(color, new Point[] {p1,p2,p3,p4}, count);
	}
	
	// getArea() and isInside(Point point) should be override for type quadrilateral
	// which their diagonals lie outside them such as concave kite.
	// In this exercise there are no quadrilaterals like this.
	public double getArea(){
		Triangle[] splitedQuad = splitToTriangles();
		return splitedQuad[0].getArea() + splitedQuad[1].getArea();
	}
	public boolean isInside(Point point) {
		Triangle[] splitedQuad = splitToTriangles();
		return splitedQuad[0].isInside(point) || splitedQuad[1].isInside(point);
	}
	private Triangle[] splitToTriangles() {
		Point[] vertices = this.getVertices();
		Triangle trngl1 = new Triangle(this.getColor(),vertices[0],vertices[1],vertices[3],false);
		Triangle trngl2 = new Triangle(this.getColor(),vertices[2],vertices[1],vertices[3],false);
		return new Triangle[] {trngl1,trngl2};
	}
}
