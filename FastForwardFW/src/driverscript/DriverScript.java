package driverscript;

import runmanager.RunManager;

//This Class has main method. i.e Execution starts here
public class DriverScript {

	public static void main(String[] args) {
		RunManager.runManager(); // Call RunManager to Read Run manager and Execute the test script which are marked 'need to execute' as YES.
		
	}

}
