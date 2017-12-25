package runmanager;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import constants.ConstantsVariables;
import constants.EnvironmentalVariables;
import datautil.DataUtil;


//Read Run Manager Excel file and Create Test Suit 
public class RunManager{

public static void runManager(){
	ReadRunManager();
	ExecutionEngine.ExecutionEngineRun();
}

private static  void ReadRunManager(){
	int NoOfTestScriptsToExecute=0; //* Number of Test scripts executable as Yes in RunManager file
	int NoOfTestScripts=0;        //* No of Test Scripts in Run Manager file
	String[][] BatchRunTestCases;   //* Array for all RunManager data which are marked as "yes" to execute
	
	DataUtil oRunManagerData=new DataUtil(ConstantsVariables.getConstantsVariable("RunManager_Path"),"BatchRun"); //*Create DataUtil Object to read RunManager
	XSSFSheet Sheet=oRunManagerData.getSheet("BatchRun");
	
	NoOfTestScripts=oRunManagerData.getNumberOfRowsUsed();
	NoOfTestScriptsToExecute=oRunManagerData.getNumberOfRowsWithText("Yes",3);
	BatchRunTestCases=new String[NoOfTestScriptsToExecute][7];
	int k=0;  //*Increment count through batch test scripts
	for(int i=1;i<NoOfTestScripts;i++){
		 //* Iterate Number for Script to execute
		if(Sheet.getRow(i).getCell(3).getStringCellValue().equalsIgnoreCase("Yes")){
			for(int j=0;j<=6;j++){
				BatchRunTestCases[k][j]=Sheet.getRow(i).getCell(j).getStringCellValue();
			}
			k=k+1;
		}
	}
	EnvironmentalVariables.put("NoOfTestScriptsToExecute", Integer.toString(NoOfTestScriptsToExecute));
	EnvironmentalVariables.BatchRunTestCases=BatchRunTestCases;
}
}
