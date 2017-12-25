package objectrepository;

import org.openqa.selenium.WebElement;
import executor.LogUpdater;
import reporter.Reporter;

//Work with WebEdits
public class WebEdit extends WebObj{
WebElement Element;


public WebEdit(WebElement Element, String ObjectID) {
	this.Element=Element;
	super.ObjectID=ObjectID;
	super.Element=Element;
		
	}

public WebEdit() {
	// TODO Auto-generated constructor stub
}

//Set the value in WebEdit
public void Set(String Value){
	try{
		Element.sendKeys(Value);
		LogUpdater.updateLog("Info", Value+" is set to the WebEdit "+ super.ObjectID);
	}catch(Exception e){
		Reporter.Report("WebEdit "+super.ObjectID+" is not idenitified", e.getMessage(), "Fail");
		LogUpdater.updateLog("ERROR", Value+" is not set to the WebEdit "+ super.ObjectID+" EXCEPTION:"+e.getMessage());
	}
}





}
