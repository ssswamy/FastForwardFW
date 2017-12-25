package runmanager;

import constants.EnvironmentalVariables;
import executor.LogUpdater;
import executor.TestScriptExecutor;

//* Execute all test scripts which are marked as Yes in RM
class ExecutionEngine { 
static boolean Result=false;
private static String[][] BatchRunTestCases=EnvironmentalVariables.BatchRunTestCases;

	protected static void ExecutionEngineRun(){
		//* execute all batch run test cases
		for(int i=0;i<BatchRunTestCases.length;i++){ 
			EnvironmentalVariables.CurrentTestScenario=BatchRunTestCases[i][0];
			EnvironmentalVariables.Browser=BatchRunTestCases[i][6];
			EnvironmentalVariables.CurrentTCID=BatchRunTestCases[i][1];
			EnvironmentalVariables.CurrentTCDescription=BatchRunTestCases[i][2];
			LogUpdater.updateLog("Info",i+1+":"+EnvironmentalVariables.CurrentTCID +"::Test Script started");
			LogUpdater.updateLog("Info","------------------------------------------------------------------");
			Result=TestScriptExecutor.testScriptExecutor();
			if(Result){
				LogUpdater.updateLog("Info",i+1+":"+EnvironmentalVariables.CurrentTCID +"::Test Script Completed");
			LogUpdater.updateLog("Info","------------------------------------------------------------------");
			}else{
				LogUpdater.updateLog("Fail",i+1+":"+EnvironmentalVariables.CurrentTCID +"::Test Script not Completed");
				LogUpdater.updateLog("Info","------------------------------------------------------------------");
			}
			EnvironmentalVariables.StopExecution=false;
			EnvironmentalVariables.GenaratedReport=false;
			EnvironmentalVariables.driver=null;
		}
	}
}
