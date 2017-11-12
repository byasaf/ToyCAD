import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Sketch {
	private enum ShapeType {
	    CIRCLE, ELLIPSE, PARALLELOGRAM, RECTANGLE, SQUARE, TRIANGLE, TRAPEZOID
	}	
	private Map<Integer,Shape> shapes;

	public Sketch() {
		this.shapes = new HashMap<Integer,Shape>();
	}

	@SuppressWarnings("unchecked")
	public void addShape(String[] argsFromStdin){
		try {
			//Convert user's input class name to the right convention 
			argsFromStdin[1] = 
					argsFromStdin[1].substring(0, 1).toUpperCase() + argsFromStdin[1].substring(1).toLowerCase();
			
			Class<? extends Shape> shapeClass= (Class<? extends Shape>)Class.forName(argsFromStdin[1]);		
			Constructor<? extends Shape>[] ctor =
				 (Constructor<? extends Shape>[]) shapeClass.getDeclaredConstructors();
			Shape newShape = shapeClass.cast(ctor[1].newInstance((Object)argsFromStdin));
			
			if(newShape != null ){
				shapes.put(newShape.getID(), newShape);
				System.out.println(newShape.getID());
			}
		//use the original addShape(before the reflection exercise) in case of exception
		} catch (ClassNotFoundException e) {
			addShapeWithoutReflection(argsFromStdin);
		} catch (SecurityException e) {
			addShapeWithoutReflection(argsFromStdin);
		} catch (InstantiationException e) {
			addShapeWithoutReflection(argsFromStdin);
		} catch (IllegalAccessException e) {
			addShapeWithoutReflection(argsFromStdin);
		} catch (IllegalArgumentException e) {
			addShapeWithoutReflection(argsFromStdin);
		} catch (InvocationTargetException e) {
			addShapeWithoutReflection(argsFromStdin);
		}
	}
	private void addShapeWithoutReflection(String[] arguments){		
		Shape newShape = null;
		String shapeTypeString = arguments[1].toUpperCase();
		String color = arguments[2];
		try {
			ShapeType shapeType = ShapeType.valueOf(shapeTypeString);
			switch(shapeType) {
			case CIRCLE:{
				Point center = new Point(arguments[3],arguments[4]);
				double radius = Double.parseDouble(arguments[5]);
				newShape = new Circle(color,center,radius,true);
				break;
			}
			case ELLIPSE:{
				Point focus1 = new Point(arguments[3],arguments[4]);
				Point focus2 = new Point(arguments[5],arguments[6]);
				double D = Double.parseDouble(arguments[7]);
				newShape = new Ellipse(color,focus1,focus2,D,true);
				break;
			}
			case PARALLELOGRAM:{
				Point p1 = new Point(arguments[3],arguments[4]);
				Point p2 = new Point(arguments[5],arguments[6]);
				Point p3 = new Point(arguments[7],arguments[8]);
				newShape = new Parallelogram(color,p1,p2,p3,true);
				break;
			}
	        case RECTANGLE:{
				Point p1 = new Point(arguments[3],arguments[4]);
				Point p2 = new Point(arguments[5],arguments[6]);
				newShape = new Rectangle(color,p1,p2,true);
	        	break;
	        }
	        case SQUARE:{
				Point center = new Point(arguments[3],arguments[4]);
				double edgeLength = Double.parseDouble(arguments[5]);
				newShape = new Square(color,center,edgeLength,true);
	        	break;
	        }
	        case TRIANGLE: {
				Point p1 = new Point(arguments[3],arguments[4]);
				Point p2 = new Point(arguments[5],arguments[6]);
				Point p3 = new Point(arguments[7],arguments[8]);
				newShape = new Triangle(color,p1,p2,p3,true);
				break;
	        }
			case TRAPEZOID:
				Point p1 = new Point(arguments[3],arguments[4]);
				Point p2 = new Point(arguments[5],arguments[6]);
				Point p3 = new Point(arguments[7],arguments[8]);
				Point p4 = new Point(arguments[9],arguments[10]);
				newShape = new Trapezoid(color,p1,p2,p3,p4,true);
				break;
			default:
				break;
		    }
		    } catch (IllegalArgumentException e){
		        System.out.println("Unknown shape or bad arguments.");
		    }
		if(newShape != null ){
			shapes.put(newShape.getID(), newShape);
			System.out.println(newShape.getID());
		}
	}	
	public void deleteShape(int shapeID){
		shapes.remove(shapeID);
	}
	public void moveShape(int shapeID, double dx, double dy){
		Shape shape = shapes.get(shapeID);
		if (shape == null) {
			System.out.println("Shape ID does not exist, could not move shape.");
		}
		else {
			shape.move(dx,dy);
		}
	}
	public void copyShape(int sourceID, double dx, double dy) {
		Shape sourceShape = shapes.get(sourceID);
		if (sourceShape == null) {
			System.out.println("Source shape ID does not exist, could not copy shape.");
		}
		else {
			Shape destShape = sourceShape.copy(); 
			destShape.move(dx,dy);
			shapes.put(destShape.getID(),destShape);
			System.out.println(destShape.getID());
		}
	}
	public void printColoredAreaSize(String color){
		double totalArea = 0;
		String upperCaseColor = color.toUpperCase();
		
        for(Integer key: shapes.keySet()){
    		Shape currentShape = shapes.get(key);
    		String currentShapeColor = currentShape.getColor().toUpperCase();
        	if(currentShapeColor.equals(upperCaseColor)){
        		totalArea += currentShape.getArea();
        	}
        }
        System.out.println(String.format("%.2f",totalArea));
	}
	public void printColoredCircumferenceSize(String color){
		double totalCircumference = 0;
		String upperCaseColor = color.toUpperCase();
		
        for(Integer key: shapes.keySet()){
    		Shape currentShape = shapes.get(key);
    		String currentShapeColor = currentShape.getColor().toUpperCase();
        	if(currentShapeColor.equals(upperCaseColor)){
        		totalCircumference += currentShape.getCircumference();
        	}
        }
        System.out.println(String.format("%.2f",totalCircumference));
	}
	public void changeShapeColor(String color, int shapeID){
		Shape shape = shapes.get(shapeID);
		if (shape == null) {
			System.out.println("Shape ID does not exist, could not change shape's color.");
		}
		else{
			shape.setColor(color);
		}
	}
	public void printIfPointInside(int shapeID, double x, double y){
		Shape shape = shapes.get(shapeID);
		if (shape == null) {
			System.out.println("Shape ID does not exist, could not test point.");
		}
		else{
			if (shape.isInside(new Point(x,y))) {
				System.out.println("1");
			}
			else {
				System.out.println("0");
			}
		}
	}
	public void printSketch(int rows, int cols){
		Printer printer = new Printer(shapes,rows,cols);
        printer.printAllShapes();
	}
}