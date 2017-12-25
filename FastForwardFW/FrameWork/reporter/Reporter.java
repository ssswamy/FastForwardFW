package reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import constants.ConstantsVariables;
import constants.EnvironmentalVariables;


//Create HTML Report
public class Reporter {
	private  String  sReportHTMLContent ;
	private  int iNumOfValidation=1;
	private static String sReportPath ;
	@SuppressWarnings("unused")
	private static WebDriver driver=EnvironmentalVariables.driver;
	private static String sTakeScreenShot_Parameter=ConstantsVariables.getConstantsVariable("Screenshots");
	private static String sRegex="<tr><td></td></tr>";
	private static Reporter oReporter=null;
	private static String templatePath = ConstantsVariables.getConstantsVariable("TestResultFile");
	private static String  CurrentDate,CurrentDateandTime;
	@SuppressWarnings("unused")
	private static String TestCaseID;
	private static String StartTime;
	private static String EndTime;
	private static String ProjectName=ConstantsVariables.getConstantsVariable("ProjectName");
	private static int NoOfPassed=0;
	private static int NoOfFailed=0;
	private static String ExecutedByUser=System.getProperty("user.name");
	
	
	
	private Reporter(){
		CurrentDate = new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
		CurrentDateandTime = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss a").format(new Date());
		StartTime=new SimpleDateFormat("hh:mm:ss a").format(new Date());
		new File("./TestResults/"+CurrentDateandTime+"/"+EnvironmentalVariables.CurrentTCID+"/").mkdirs();
		sReportPath = "./TestResults/"+CurrentDateandTime+"/"+EnvironmentalVariables.CurrentTCID+"/"+EnvironmentalVariables.CurrentTCID+"_"+CurrentDateandTime+".html";
		try {
			sReportHTMLContent = new String(Files.readAllBytes(Paths.get(templatePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
//*To Update Report event in HTML file
public static void Report(String StepName, String sDescription, String Status){
	//*if Report is called for the first time, it creates 'oReporter' object and call constructor to get the conf constants
	
	String Keyword = EnvironmentalVariables.CurrentKeyWord;
	
	switch(Status.toLowerCase()){
	case "pass":
		NoOfPassed=NoOfPassed+1;
	break;
	case "fail":
		NoOfFailed=NoOfFailed+1;
	}
	
	if(oReporter==null){
		oReporter=new Reporter();
	}
	try {
		
		if(oReporter.takescreenshot(Status)){ //*Take the screen shot based on configured in Constant(either FAIL/ALL steps

			TakesScreenshot screen=(TakesScreenshot)EnvironmentalVariables.driver;
			File tempFile=screen.getScreenshotAs(OutputType.FILE);
			String tempFileName;
			Date oDate=new Date();
			DateFormat oDateFormat=new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			tempFileName= oDateFormat.format(oDate).replace("/", "_").replace(":", "_").replace(" ", "_");
			//TestCaseID=EnvironmentalVariables.CurrentTCID;
			
			FileUtils.copyFile(tempFile, new File("./TestResults/"+CurrentDateandTime+"/"+EnvironmentalVariables.CurrentTCID+"/"+tempFileName+".png"));
			//FileUtils.copyFile(tempFile, new File("./test-output/"+TCname+""+tempFileName+".png"));
			String ImageLink="./"+tempFileName+".png";
			
			oReporter.sReportHTMLContent=oReporter.sReportHTMLContent.replaceAll(sRegex, "<tr><td>" + oReporter.iNumOfValidation + "</td><td>"+Keyword+"</td><td>"+StepName+"</td><td>"+sDescription+"</td><td><a href="+ImageLink+">"+Status+"</td></tr>"+sRegex);
			
			oReporter.iNumOfValidation=oReporter.iNumOfValidation+1;
			
		}
		else{ //*Update the html report without screen shot
			oReporter.sReportHTMLContent=oReporter.sReportHTMLContent.replaceAll(sRegex, "<tr><td>" + oReporter.iNumOfValidation + "</td><td>"+Keyword+"</td><td>"+StepName+"</td><td>"+sDescription+"</td><td>"+Status+"</td></tr>"+sRegex);
			oReporter.iNumOfValidation=oReporter.iNumOfValidation+1;
		}
	}catch(Exception e){
		
	}
	if(EnvironmentalVariables.StopExecution){
		try {
			generateReport();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
//* To generate Report at the end of Test Execution
public static void generateReport() throws IOException{
	if(EnvironmentalVariables.GenaratedReport!=true){
	oReporter.sReportHTMLContent=oReporter.sReportHTMLContent.replaceFirst("Test_Script_Name_Variable",EnvironmentalVariables.CurrentTCDescription);
	oReporter.sReportHTMLContent=oReporter.sReportHTMLContent.replaceFirst("Date_Variable",CurrentDate);
	
	oReporter.sReportHTMLContent=oReporter.sReportHTMLContent.replaceFirst("Start_Time_Variable",StartTime);
	EndTime=new SimpleDateFormat("hh:mm:ss a").format(new Date());
	oReporter.sReportHTMLContent=oReporter.sReportHTMLContent.replaceFirst("End_Time_Variable",EndTime);
	
	oReporter.sReportHTMLContent=oReporter.sReportHTMLContent.replaceFirst("Project_Name_Variable",ProjectName);
	
	
	oReporter.sReportHTMLContent=oReporter.sReportHTMLContent.replaceFirst("NumberOfPassed",Integer.toString(NoOfPassed));
	oReporter.sReportHTMLContent=oReporter.sReportHTMLContent.replaceFirst("NumberOfFailed",Integer.toString(NoOfFailed));
	oReporter.sReportHTMLContent=oReporter.sReportHTMLContent.replaceFirst("NumberOfTotal",Integer.toString(NoOfPassed+NoOfFailed));
	oReporter.sReportHTMLContent=oReporter.sReportHTMLContent.replaceFirst("ExecutedByUser",ExecutedByUser);
	
try {
	Files.write(Paths.get(sReportPath), oReporter.sReportHTMLContent.getBytes(), StandardOpenOption.CREATE);
	oReporter=null;
	EnvironmentalVariables.GenaratedReport=true;
	NoOfPassed=0;
	NoOfFailed=0;
	} catch (IOException e) {
		throw e;
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}

//* Return Boolean value to take the screen shot based on status
private boolean takescreenshot(String Status){
	if(sTakeScreenShot_Parameter.equalsIgnoreCase(Status)){
		return true;
	}else if(sTakeScreenShot_Parameter.equalsIgnoreCase("ALL")){
		return true;
	}
	return false;
}

@SuppressWarnings("unused")
private static String getCallerClassName(int level) throws ClassNotFoundException {
    StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
   return stElements[level+1].getMethodName();
    // String rawFQN = stElements[level+1].toString().split("\\(")[0];
   // return Class.forName(rawFQN.substring(0, rawFQN.lastIndexOf('.'))).getName();
}

}
