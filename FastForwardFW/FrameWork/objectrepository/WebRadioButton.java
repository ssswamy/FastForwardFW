package objectrepository;


import java.util.List;
import org.openqa.selenium.WebElement;
import executor.LogUpdater;

//Work with radio buttons
public class WebRadioButton extends WebObj {
private String ObjectID;

    protected List < WebElement > Element;

    WebRadioButton(List < WebElement > Element,String ObjectID) {
        this.Element = Element;
        this.ObjectID=ObjectID;
        super.ObjectID=ObjectID;
    	super.Elements=Element;
    }

    WebRadioButton() {
        // TODO Auto-generated constructor stub
    }


//get the radio button webelement based on index
    public WebObj get(int count) {
        WebElement Element=null;
    	try {
    		Element=this.Element.get(count);
            LogUpdater.updateLog("Info","Got "+count+" of the value from WebRadioButton "+ ObjectID);
            return new WebObj(Element,ObjectID);
        } catch (Exception e) {
        	//Reporter.Report("WebRadioButton '" + ObjectID + "' is not available", e.getLocalizedMessage(), "Fail");
            LogUpdater.updateLog("ERROR","Not Identified "+count+" of the value from WebRadioButton "+ ObjectID);
            return new WebObj(Element,ObjectID);
        }
       
    }
    //Get the radio button element based on its value
    public WebObj get(String Value) {
        WebElement WE=null;
    	try {
            for (WebElement E: Element) {
                if (E.getAttribute("value").equalsIgnoreCase(Value)) {
                	LogUpdater.updateLog("Info","Got "+Value+" from WebRadioButton "+ ObjectID);
                	return new WebObj(E,ObjectID);
                }


            }
        } catch (Exception e) {
        	//Reporter.Report("WebRadioButton '" + ObjectID + "' is not available", e.getLocalizedMessage(), "Fail");
            LogUpdater.updateLog("ERROR","Not Identified "+Value+" from WebRadioButton "+ ObjectID);
        }
    	return new WebObj(WE,ObjectID);

    }
    
    // get the radio button label name 
    public String RadioButtonLableNamebyValue(int Value) {
        return get(Value).getAttribute("name");

    }



}