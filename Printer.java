import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Printer {
	private String[][] printBoard;
	private double xScaleValue;
	private double yScaleValue;
	private Point origin;
	private Map<Integer,Shape> shapes;
	private static final Map<String, String> COLORS;
		static {
			final Map<String, String> colors = new HashMap<String, String>();
		    colors.put("BLUE"	,"\u001B[44m \u001B[0m");
		    colors.put("RED"	,"\u001B[41m \u001B[0m");
		    colors.put("YELLOW"	,"\u001B[43m \u001B[0m");
		    colors.put("GREEN"	,"\u001B[42m \u001B[0m");
		    COLORS = Collections.unmodifiableMap(colors);
		}

 	public Printer(Map<Integer,Shape> shapes, int yBoardSize, int xBoardSize) {
		this.printBoard = new String[yBoardSize][xBoardSize];
 		for (int i=0 ; i < printBoard.length; i++) {
 			for(int j=0 ; j < printBoard[0].length; j++){
 				printBoard[i][j]=" ";
 			}
 		}
		this.shapes = shapes;
		setScalingValues(xBoardSize,yBoardSize);	
	}
 	
 	private void setScalingValues(int xBoardSize, int yBoardSize){
		List<Point> allPointsList = new ArrayList<Point>();
			
		//insert all shapes' vertices to allPointsArrayList
        for(Integer key: shapes.keySet()){
        	for(Point point : shapes.get(key).getOuterVertices() ){
        		allPointsList.add(point);
        	}		
        }
        
        //find minimum and maximum points of all shapes
        Point[] allPointsArray = new Point[allPointsList.size()];
        allPointsList.toArray(allPointsArray);
        Point minX = Point.getPointWithMinX(allPointsArray);
        Point maxX = Point.getPointWithMaxX(allPointsArray);
        Point minY = Point.getPointWithMinY(allPointsArray);
        Point maxY = Point.getPointWithMaxY(allPointsArray);
        
        double xLength = maxX.getX() - minX.getX();
        double yLength = maxY.getY() - minY.getY();

        this.xScaleValue = xLength / xBoardSize;
        this.yScaleValue = yLength / yBoardSize; 
        this.origin = new Point(minX.getX(),minY.getY());
	}	
 	
 	public void printAllShapes(){
		 for(Integer key: shapes.keySet()){
			fillShape(key);
		 }
		 refreshView();    
	}	
	
 	private void fillShape(int shapeID){
 		Shape shape = shapes.get(shapeID);
 		for (int i=0 ; i < printBoard.length; i++) {
 			for(int j=0 ; j < printBoard[0].length; j++){
 				//transformation between console axes and real axes
 				double x = (j+0.5)*xScaleValue+origin.getX();
 				double y = (i+0.5)*yScaleValue+origin.getY();

 				Point newPixel = new Point(x,y);
 				if(shape.isInside(newPixel)){
 					printBoard[i][j]=COLORS.get(shape.getColor().toUpperCase());
 				if (printBoard[i][j] == null ) printBoard[i][j] = "#"; //color not exists
 				}
 			}
 		}		
 	}
 	
 	private void refreshView(){
 		for (int i=0 ; i < printBoard.length; i++) {
 			for(int j=0 ; j < printBoard[0].length; j++){
 				System.out.print(printBoard[printBoard.length-i-1][j]);
 			}
 			System.out.print('\n');
 		}
 	}
}