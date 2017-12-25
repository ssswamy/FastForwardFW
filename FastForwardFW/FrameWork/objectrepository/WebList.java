package objectrepository;

import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import constants.EnvironmentalVariables;
import executor.LogUpdater;
import executor.Sync;
import reporter.Reporter;

//Work with List
public class WebList extends WebObj{

	
private String ObjectID;
protected Select Element = null;

WebList(WebElement WElement, String ObjectID) {
		if(WElement!=null){
		this.Element=new Select(WElement);
		super.Element=WElement;}
		else{
			this.Element=null;
			super.Element=null;
		}
		this.ObjectID=ObjectID;
		super.ObjectID=ObjectID;
		
	}

 WebList() {
	// TODO Auto-generated constructor stub
}

//Set the list based on value
public void Set(String Value){
	try{
	this.Element.selectByValue(Value);
	LogUpdater.updateLog("Info", Value+" is set to the WebList "+ObjectID);
	}catch(Exception e){
	     
		Reporter.Report("WebList "+ObjectID+" is not idenitified", e.getMessage(), "Fail");	
		LogUpdater.updateLog("Error","WebList value not identified from "+ ObjectID);
	}
}
//Select item based on visible text
public void SelectBasedOnVisibleText(String Value){
	try{
	this.Element.selectByVisibleText(Value);
	LogUpdater.updateLog("Info", Value+" is set to the WebList "+ObjectID);
	}catch(Exception e){
	     
		Reporter.Report("WebList "+ObjectID+" is not idenitified", e.getMessage(), "Fail");	
		LogUpdater.updateLog("Error","WebList value not identified from "+ ObjectID);
	}
}


//Set the list based on Index
public void Set(int Number){
	try{
		this.Element.selectByIndex(Number);
		LogUpdater.updateLog("Info", Number+" is set to the WebList "+ObjectID);
		}catch(Exception e){
			Reporter.Report("WebList "+ObjectID+" is not idenitified", e.getMessage(), "Fail");	
			LogUpdater.updateLog("Error","WebList value not identified from "+ ObjectID);
		}
}
//Get the all items in list
public int GetListItemsCount(){
	try{
		List<WebElement>Options= this.Element.getOptions();
		LogUpdater.updateLog("Info", Options.size()+" optiosn are present in "+ObjectID);
		return Options.size();
		}catch(Exception e){
			Reporter.Report("WebList "+ObjectID+" is not idenitified", e.getMessage(), "Fail");	
			LogUpdater.updateLog("Error","WebList value not identified from "+ ObjectID);
		}
	
	return 0;
}

//Check iteam in list
public boolean CheckListItem(String Item){
	
	try{
		List<WebElement>Options= this.Element.getOptions();
		for(WebElement ele: Options){
			if(ele.getText().equalsIgnoreCase(Item)){
				LogUpdater.updateLog("Info", Item+" Option is avaiable in "+ObjectID);
				return true;
			}
		}
		}catch(Exception e){
			Reporter.Report("WebList-CheckListItem",ObjectID+" List is not avaiable: "+ e.getMessage(), "Fail");	
			LogUpdater.updateLog("Error","WebList-CheckListItem"+ObjectID+" List is not avaiable");
		}
	
	return false;	
}


//Get the selected item in list
public String getSelectedValue(){
	String Value;
	try{
		Value= this.Element.getFirstSelectedOption().getText();
		LogUpdater.updateLog("Info","WebList "+ObjectID+" selected value"+Value);
		return Value;
	}catch(Exception e){
		Reporter.Report("WebList-GetSelectedValue",ObjectID+" List is not avaiable: "+ e.getMessage(), "Fail");
		LogUpdater.updateLog("Error","WebList-GetSelectedValue"+ObjectID+" List is not avaiable");
	}
	return null;
}

//* in Progress
//--------------
public void ExpandListBox(){
	WebDriver driver=EnvironmentalVariables.driver;
	Actions builder=new Actions(driver);
	
	//builder.SendKeys(this.Element, Keys.LEFT_ALT + Keys.DOWN).Build().Perform();
	builder.sendKeys(this.Element.getFirstSelectedOption(),  Keys.DOWN).build().perform();
	//this.Element.getFirstSelectedOption().click();
	//this.Element.getFirstSelectedOption().sendKeys(Keys.DOWN);
	Sync.Wait(2);
	Reporter.Report("WebList-GetSelectedValue",ObjectID+" List expanded", "Pass");
	
}


}
