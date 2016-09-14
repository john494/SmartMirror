
public class PowerOn {
	static Utils utils;
	
	public void boot(){
		//check if file exists
		int tester;
		
		utils = new Utils();
		tester = utils.savedFile();
		if(tester != 0){
			initialBoot();
		}
		else {
			savedBoot();
		}
		
	}
	
	private void initialBoot(){
		//method for the first boot of machine
		utils.loadDefault();
		utils.loadInfo();
		utils.connection();
		Main main = new Main();
		main.main();
		
	}
	
	private void savedBoot(){
		//method to boot from a saved file
		utils.readSavedData();
		utils.loadInfo();
		utils.connection();
		Main main = new Main();
		main.main();
		
	}
	
}
