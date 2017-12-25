package objectrepository;

import java.util.List;
import org.openqa.selenium.WebElement;
import executor.LogUpdater;

//Work with Check box
public class WebCheckBox extends WebObj {

    protected List < WebElement > Element;
    private String ObjectID;
    WebCheckBox(List < WebElement > element,String ObjectID) {
        this.Element = element;
        this.ObjectID=ObjectID;
        super.ObjectID=ObjectID;
        super.Elements=element;
    }

    WebCheckBox() {
        // TODO Auto-generated constructor stub
    }

    // get the Check Box values based on index
    public WebObj get(int count) {
        WebElement Element=null;
    	try {
    		Element= this.Element.get(count);
            LogUpdater.updateLog("Info","Got "+count+" of the value from WebCheckBox "+ ObjectID);
            return new WebObj(Element,ObjectID);
        } catch (Exception e) {
          //  Reporter.Report("WebCheckBox '" + ObjectID + "' is not available", e.getLocalizedMessage(), "Fail");
            LogUpdater.updateLog("ERROR","Not Identified "+count+" of the value from WebCheckBox "+ ObjectID);
            return new WebObj(Element,ObjectID);
        }
        
    }
 // get the Check Box values based on Value
    public WebObj get(String Value) {
        WebElement WE=null;
    	try {
            for (WebElement E: Element) {
                if (E.getAttribute("value").equalsIgnoreCase(Value)) {
                	LogUpdater.updateLog("Info","Got "+Value+" from WebCheckBox "+ ObjectID);
                	return new WebObj(E,ObjectID);
                }
            }
        } catch (Exception e) {
           // Reporter.Report("WebCheckBox '" + ObjectID + "' is not available", e.getLocalizedMessage(), "Fail");
            LogUpdater.updateLog("ERROR","Not Identified "+Value+" from WebCheckBox "+ ObjectID);
        }
        return new WebObj(WE,ObjectID);

    }


}