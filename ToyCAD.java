import java.util.Scanner;

public class ToyCAD {
	private enum CommandType { 	NEW, DELETE, MOVE, COPY, AREA, CIRCUMFERENCE,
		COLOR, PRINT, IS_INSIDE ,EXIT };

	public static void main(String[] args) {	
		Sketch sketch = new Sketch();
	    Scanner scanner = new Scanner(System.in);   
	    boolean done = false;    
	    while (!done) {
			String input = scanner.nextLine();
			String[] arguments = input.split(" ");
			try {
				CommandType commandType = CommandType.valueOf(arguments[0].toUpperCase());
				switch(commandType) {
				case NEW:
					sketch.addShape(arguments);
					break;
				case DELETE:
					sketch.deleteShape(Integer.parseInt(arguments[1]));
					break;
				case MOVE:
					sketch.moveShape(Integer.parseInt(arguments[1]),
							Double.parseDouble(arguments[2]),Double.parseDouble(arguments[3]));
					break;
				case COPY:
					sketch.copyShape(Integer.parseInt(arguments[1]),
							Double.parseDouble(arguments[2]),Double.parseDouble(arguments[3]));
					break;
				case AREA:
					sketch.printColoredAreaSize(arguments[1]);
					break;
				case CIRCUMFERENCE:
					sketch.printColoredCircumferenceSize(arguments[1]);
					break;
				case COLOR:
					sketch.changeShapeColor(arguments[1], Integer.parseInt(arguments[2]));
					break;
				case IS_INSIDE:
					sketch.printIfPointInside(Integer.parseInt(arguments[1]),
							Double.parseDouble(arguments[2]),Double.parseDouble(arguments[3]));
					break;
				case PRINT:
					sketch.printSketch(Integer.parseInt(arguments[1]),Integer.parseInt(arguments[2]));
					break;
				case EXIT:
					done = true;
					break;
			      }
			} catch (IllegalArgumentException e1){
				System.out.println("Unknown command.");
			} catch (ArrayIndexOutOfBoundsException e2){
				System.out.println("The syntax of the command is incorrect.");
		    }
		}
		scanner.close();
	}
}
