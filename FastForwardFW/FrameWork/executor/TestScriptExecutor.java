package executor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import businessfunctions.BusinessFunctions;
import constants.EnvironmentalVariables;
import datautil.DataUtil;
import reporter.Reporter;

public class TestScriptExecutor {

	private static String[] keywords;
	
	
	//This Method to execute all keywords which are mapped to Current Test script in Test data file in Keywords Sheet
	public static boolean testScriptExecutor() {
		XSSFSheet Sheet;
		DataUtil oExcelKeyWords = new DataUtil("./TestData/"+EnvironmentalVariables.CurrentTestScenario+".xlsx","KeyWords");
		EnvironmentalVariables.oTestDataSheet=oExcelKeyWords;
		Sheet = oExcelKeyWords.getSheet("KeyWords");
		String CurrentTCID = EnvironmentalVariables.CurrentTCID;
		int Current_TC_Row = oExcelKeyWords.getRowWithCellText(CurrentTCID, 0);

		//*get number of Business Keywords are present in the current Test script
		int NumberOfKeyWords = oExcelKeyWords.getNumberOfColumnsUsed(Current_TC_Row);
		keywords = new String[NumberOfKeyWords];

		//* get each keyword names for the current Test script
		for (int i = 1; i < NumberOfKeyWords; i++) {
			if(EnvironmentalVariables.StopExecution!=true){
			keywords[i - 1] = Sheet.getRow(Current_TC_Row).getCell(i).getStringCellValue();
			//* execute each keyword
			LogUpdater.updateLog("Info", i + " :[ " + keywords[i - 1] + " ] :: Keyword called successfully");
			//*execute each keyword
			EnvironmentalVariables.CurrentKeyWord = keywords[i - 1];
			boolean Result = executeKeyWord(keywords[i - 1]);
			if (Result) {
				LogUpdater.updateLog("Info", i + " :[ " + keywords[i - 1] + " ] :: Keyword completed successfully");
			}
			else {
				LogUpdater.updateLog("Fail", i + " :[ " + keywords[i - 1] + " ] :: Keyword failed in Business Functions");
				//try {
					//Reporter.generateReport();
					//break;
				//} catch(IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				//}
			}
		}}
		try {
			if(EnvironmentalVariables.GenaratedReport!=true){
				Reporter.generateReport();
			}
			
		} catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}
	
	
	/*Execute each keyword associated with TC in Data Sheet*/
	private static boolean executeKeyWord(String Keyword) {
		Object NewObject;
		String packagePath = BusinessFunctions.class.getPackage().getName();
		java.net.URL Resource = ClassLoader.getSystemClassLoader().getResource(packagePath);
		String[] Files = new File(Resource.getPath()).list();
		String[] Classes = new String[Files.length];

		for (int i = 1; i < Files.length; i++) {
			String fileName = Files[i];
			String className = null;
			String fileNm = null;
			if (fileName.endsWith(".class")) {
				fileNm = fileName.substring(0, fileName.length() - 6);
				className = packagePath + '.' + fileNm;
				Classes[i] = className;
			}
			try {
				NewObject = Class.forName(Classes[i]).newInstance();
				Method[] Methods = NewObject.getClass().getMethods();
				for (int k = 0; k < Methods.length; k++) {
					if (Methods[k].getName().equalsIgnoreCase(Keyword)) {
						try {
							Methods[k].invoke(NewObject, null);
							return true;
						} catch(IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Reporter.Report("Exception generated", e.toString(), "fail");
						} catch(InvocationTargetException e) {
							// TODO Auto-generated catch block
							Reporter.Report("Exception generated", e.toString(), "fail");
						}

					}
				}
				NewObject = null;

			} catch(InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch(IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch(ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		return false;

	}

	

}