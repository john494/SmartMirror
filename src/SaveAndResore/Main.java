import java.util.ArrayList;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SaveAndRestore saveRestore = new SaveAndRestore();
		ArrayList widgetInfo = makeList();
		System.out.println("Plain Array: " + widgetInfo);
		int tester = saveRestore.save(widgetInfo);
		if(tester != 0){
			System.out.println("FAILED");
		}
		else{
			System.out.println("SUCCESS");
		}
		ArrayList restoredArray = null;
		tester = saveRestore.restore(restoredArray);
		if(tester != 0){
			System.out.println("FAILED");
		}
		else{
			System.out.println("SUCCESS");
		}

	}
	
	//this should be called by the UI on close...
	//eventually it will create a list of lists
	//the inner lists containing widget name
	//and info about the widget
	private static ArrayList makeList(){
		ArrayList arrylist = new ArrayList();
		ArrayList weather = new ArrayList();
		String weatherName = "Weather";
		String weatherLocation = "Amherst";
		String weatherType = "Fahrenheit";
		weather.add(weatherName);
		weather.add(weatherLocation);
		weather.add(weatherType);
		ArrayList calender = new ArrayList();
		String calenderName = "Calender";
		String calenderDateLeftOn = "July 4";
		calender.add(calenderName);
		calender.add(calenderDateLeftOn);
		arrylist.add(weather);
		arrylist.add(calender);
		return arrylist;
	}

}
