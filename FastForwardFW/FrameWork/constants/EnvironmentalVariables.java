package constants;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import datautil.DataUtil;
import executor.LogUpdater;

//*** Create Environmental variables at run time and get the env var based on its name 
public class EnvironmentalVariables {
    public static int NoOfTestScriptsToExecute;
    public static String[][] BatchRunTestCases;
    public static String CurrentTCID;
    public static String CurrentTCDescription;
    public static String TestDataFile;
    public static WebDriver driver;
    public static String CurrentKeyWord;
    public static String Browser;
    public static boolean StopExecution;
    public static boolean GenaratedReport;
    public static String CurrentTestScenario;
    public static DataUtil oTestDataSheet;

    private static Map < String, String > variables = null;


//* Create Env variable by providing Var_Name and its Value
    public static void put(String Variable_Name, String value) {
        Create();
        variables.put(Variable_Name, value);
    }




    private static void Create() {
        //if(variables!=null) {
        variables = new HashMap < String, String > ();
        //}
    }

 //* Get the env Var Value based on Its name
    public static String get(String Variable_Name) {
        if (variables.containsKey(Variable_Name)) {
            return variables.get(Variable_Name).toString();
        } else {
            LogUpdater.updateLog("Info", "Environmental variable " + Variable_Name + " is not avaiable");
            return null;
        }

    }


}