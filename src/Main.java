
public class Main {

	public void main(){
		lockScreen lockScreen = new lockScreen();
		lockScreen.displayLockScreen();
		lockScreen.lock();
		GUI gui = new GUI();
		gui.displayGUI();
		//at this point we need to update GUI info
		//with info gathered from server
		//connection has been established
		//need to make decisions on how to grab info...
		//ex. wait for upcalls? make requests etc...
	}
	
}
