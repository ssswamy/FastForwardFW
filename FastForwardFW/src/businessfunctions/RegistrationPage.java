package businessfunctions;

import datautil.DataUtil;
import executor.Sync;
import objectrepository.ObjectFactory;
import reporter.Reporter;

public class RegistrationPage{

	
	public boolean Registration(){
		
		if(ObjectFactory.WebLink("Registration").Exist(4)) {// Check List exist in Page
			ObjectFactory.WebLink("Registration").click();//Click on Web Link
			Sync.Wait(5);	//Sync to 1 sec

			Sync.WaitForElementPresent("First_Name", 10);// Wait till the First_Name Object exist or Wait for 10 sec
			ObjectFactory.WebEdit("First_Name").Set("Test");
			ObjectFactory.WebEdit("First_Name").Set(DataUtil.getTestData("Reg_Details", "First_Name"));//Get the data from datasheet 
																					//based on Test Case nane and set to Web Edit
			ObjectFactory.WebEdit("Last_Name").Set(DataUtil.getTestData("Reg_Details", "Last_Name"));
			
			ObjectFactory.WebRadioButton("Marital_Status").get("Single").click();//get the rdaio button based on Value
			ObjectFactory.WebCheckBox("Hobby").get(2).click();//get the Check box based on Index
			//
			if (ObjectFactory.WebList("Country").CheckListItem("India")) {//Check a Item Present in Web List
				ObjectFactory.WebList("Country").Set("India");//Select item in Web list based on Value
			}else {
				ObjectFactory.WebList("Country").Set("United States");//Select item in Web list based on Index
			}
			ObjectFactory.WebEdit("Phone_Number").Set("1234567890");
			ObjectFactory.WebEdit("Username").Set("Pres001234");
			ObjectFactory.WebEdit("Mail").Set("ssswamy536@gmail.com");
			ObjectFactory.WebEdit("Password").Set("Test@12345");
			ObjectFactory.WebEdit("Confirm_Password").Set("Test@12345");			
			Sync.Wait(1);
			ObjectFactory.Element("Submit").click();
			Sync.Wait(4);
			
		/*	if (ObjectFactory.Element("Error_Email").Exist(2)) {
				if (ObjectFactory.Element("Error_Email").getText().equalsIgnoreCase("Error: E-mail address already exists")) {
					Reporter.Report( "Email Error", "Email Error displayed when user is tried to reg with existing email", "Pass");
					return true;
				}else {
					Reporter.Report( "Email Error", "Email Error not displayed with correct text when user is tried to reg with existing email", "Fail");
						
				}
			}else {
				Reporter.Report( "Email Error", "Email Error not displayed when user is tried to reg with existing email", "Fail");
				
			}
			*/
			
		}else {
			Reporter.Report("Registration","Registration link is not Present on Page", "Fail");
				
			
		}
		return false;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
