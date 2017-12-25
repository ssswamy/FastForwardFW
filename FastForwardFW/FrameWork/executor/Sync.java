package executor;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import constants.EnvironmentalVariables;
import objectrepository.ObjectFactory;


//* Sync of Web pages
public class Sync {

	//***  Thread sleep 
    public static void Wait(int Seconds) {
        try {
            Thread.sleep(Seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    /* Wait the web page till the WebElement present. If Element is not found and time exceeded, Update Logs as WebElement 
    not displayed */
    public static void WaitForElementPresent(String ObjectID, int Seconds) {
        if (!(ObjectFactory.Element(ObjectID).Exist(Seconds))) {
            LogUpdater.updateLog("Info", "  WebElement " + ObjectID + " is not displayed after " + Seconds + " Seconds");
        } else {
            LogUpdater.updateLog("Info", "  WebElement " + ObjectID + " is displayed after " + Seconds + " Seconds");
        }
    }
    
    
    /*Wait the web page till the WebElement Property present. If Element property is not found and time exceeded, Update Logs 
     * as WebElement property not displayed*/
    public static void WaitForElementPropertyValue(String ObjectID, String Attribute, String Value, int Seconds) {
        boolean Result = false;
        if (ObjectFactory.Element(ObjectID).Exist(Seconds)) {
            long startTime = System.currentTimeMillis();
            do {
                try {

                    if (ObjectFactory.Element(ObjectID).getAttribute(Attribute).equalsIgnoreCase(Value)) {
                        LogUpdater.updateLog("Info", "  WebElement " + ObjectID + " has attribute" + Attribute + " - " + Value);
                        Result = true;
                        return;
                    } else {
                        Thread.sleep(1000);
                    }

                } catch (Exception e) {
                    LogUpdater.updateLog("Error", "Exception raised while checking WebElement visibility" + ObjectID);
                }

            } while ((System.currentTimeMillis() - startTime) / 1000 < Seconds);

            if (!(Result)) {
                LogUpdater.updateLog("Info", "  WebElement " + ObjectID + "  not has attribute" + Attribute + " - " + Value);
            }
        } else {
            LogUpdater.updateLog("Info", "  WebElement " + ObjectID + " is not displayed after " + Seconds + " Seconds");
        }
    }



    //* In Progress
    @SuppressWarnings("unused")
    private static void ExplicitWait(int Seconds, String ObjectID) {
        try {
            WebDriverWait Wait = new WebDriverWait(EnvironmentalVariables.driver, 10);
            WebElement Element = null;
            Wait.until(ExpectedConditions.visibilityOf(Element));
        } catch (Exception e) {
            e.printStackTrace();
            LogUpdater.updateLog("Info", "Exception raised @ Explicitwait of " + ObjectID + " " + e.getMessage());
        }
    }


}