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
	
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		//System.out.println(DataUtil.getTestData("UserIDs", "UserName"));
		//DataUtil.putTestData("UserIDs", "UserName", "SSS updated 2");
		//System.out.println(DataUtil.getTestData("UserIDs", "UserName"));
	
//		for(int m=5;m<5;m++) {
//			String CompanyName=DataUtil.getdata("TS01","Results",m, 0).trim();
//			
//			ObjectFactory.WebEdit("Company").Set(CompanyName);
//			Sync.Wait(1);
//			ObjectFactory.WebLink("Company_Suggest").SetToProperty("WebLink", "obj_LinkText", CompanyName).click();
//			//ObjectFactory.WebLink("Company_Suggest").click();
//			System.out.println(m+" : "+CompanyName);
//			ObjectFactory.WebList("Exchange").Set("N");
//			ObjectFactory.WebList("From_Day").ExpandListBox();
//			ObjectFactory.WebList("From_Day").Set("28");
//			ObjectFactory.WebList("From_Month").Set("07");
//			ObjectFactory.WebList("From_Year").Set("2017");
//			
//			ObjectFactory.WebList("To_Day").Set("31");
//			ObjectFactory.WebList("To_Month").Set("07");
//			ObjectFactory.WebList("To_Year").Set("2017");
//			
//			ObjectFactory.WebLink("Go").click();
//			Sync.Wait(10);
//			
//			WebTable WtResults1=ObjectFactory.WebTable("Result");
//			String[][] ResultData=new String[1500][6];
//			
//			for(int x=3;x<=3;x++) {
//				for(int j=2;j<=6;j++) {
//					ResultData[x-3][j-2]=WtResults1.GetCelldata(x, j);
//					
//					
//					DataUtil.UpdateDataSheet("TS01","Result", CompanyName, "V"+(j-1), ResultData[x-3][j-2]);
//				}
//			}
//			// .//*[@id='mc_mainWrapper']/div[2]/div/div[3]/div[2]/div/a/img
//			ObjectFactory.WebLink("Change").click();
//			Sync.Wait(5);
//			ObjectFactory.WebEdit("Company").clear();			
//		}

//		return true;
//	}

	public boolean CompanySearch1() {
		ObjectFactory.WebLink("Registration").click();
		//System.out.print(EnvironmentalVariables.driver.findElement(By.xpath(".//*[@id='content']/table")).getText());
		//System.out.println(ObjectFactory.WebTable("WebTableToolsQA").GetCelldata(3, 2));
		//ObjectFactory.WebTable("WebTableToolsQA").GetChildElement(3,6,0).click();
		//ObjectFactory.WebTable("WebTableToolsQA").ActionOnChildElementBasedOnColumns("City", "Shanghai", "…").click();
		//System.out.println(ObjectFactory.WebTable("WebTableToolsQA").ActionOnChildElementBasedOnColumns("Built", "2080", "City").toString());
		//WebElement Element=null;
		//WebElement El=ObjectFactory.WebTable("WebTableToolsQA").getElement();
		
		return true;
	}
	
	
}
