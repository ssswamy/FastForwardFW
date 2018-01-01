package businessfunctions;

import java.sql.ResultSet;

import org.openqa.selenium.WebDriver;

import constants.ConstantsVariables;
import constants.EnvironmentalVariables;
import datautil.DataBase;
import datautil.DataUtil;
import executor.LogUpdater;
import executor.Sync;
import objectrepository.ObjectFactory;


public class BusinessFunctions {
	
	
	//Open Browser 
	public boolean Open(){	
		
		ObjectFactory.Open(ConstantsVariables.getConstantsVariable("App_Url"));
		WebDriver driver=EnvironmentalVariables.driver;
		
		//if(driver.getTitle().equalsIgnoreCase("Demoqa | Just another WordPress site")){
		if(driver.getTitle().contains("Demoqa")) {
		driver.manage().window().maximize();
			Sync.Wait(5);
			return true;
		}else {
			LogUpdater.updateLog("Fail","Browser is not opened");
			return false;
		}
		}

	public boolean CompanySearch() {
		
		String CompanyName=DataUtil.getdata("TS01","Results",1, 0).trim();
		ObjectFactory.WebEdit("Company").Set(CompanyName);
		Sync.Wait(1);
		ObjectFactory.WebLink("Company_Suggest").SetToProperty("WebLink", "obj_LinkText", CompanyName).click();
		ObjectFactory.WebLink("Company_Suggest").click();
		ObjectFactory.WebList("Exchange").Set("N");
		ObjectFactory.WebList("From_Day").ExpandListBox();
		
		/*
		DataBase DB=new DataBase("URL","a","US","PS");
		ResultSet rs=DB.GetData("SQL_Query");
		DB.CloseDB();
		*/
		DataUtil oDataUtil = new DataUtil("./TestData/"+EnvironmentalVariables.CurrentTestScenario+".xlsx","KeyWords");
		
		return true;
		
		}
	
}
