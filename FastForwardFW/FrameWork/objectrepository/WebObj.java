package objectrepository;

import java.util.List;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import executor.LogUpdater;
import reporter.Reporter;


//Super class for all Web Elements
public class WebObj {

    protected WebElement Element;
    protected List < WebElement > Elements;
    private static WebObj WebObj = new WebObj();
    protected String ObjectID;

    WebObj(WebElement Element, String ObjectID) {
        this.Element = Element;
        this.ObjectID = ObjectID;
    }
    WebObj() {}
    WebObj(List < WebElement > Elements, String ObjectID) {
        WebObj.Elements = Elements;
        WebObj.ObjectID = ObjectID;
    }

    public WebElement getElement() {
        return this.Element;
    }

    //* Set to a Property and identify Object based on New Property
    public WebObj SetToProperty(String ObjType, String Property, String PropertyValue) {
        String[][] ObjectProperties;
        ObjectProperties = ObjectFactory.GetObjectProperties(ObjectID);

        for (int i = 0; i < ObjectProperties.length; i++) {
            if (ObjectProperties[i][0].contentEquals(Property)) {
                ObjectProperties[i][1] = PropertyValue;
            }
        }
        switch (ObjType) {
            case "WebEdit":
            case "webedit":
                WebEdit WEdit = new WebEdit(ObjectFactory.IdentifyElement(ObjectProperties), ObjectID);
                return WEdit;
            case "WebLink":
            case "weblink":
                WebLink WLink = new WebLink(ObjectFactory.IdentifyElement(ObjectProperties), ObjectID);
                return WLink;
            case "WebRadioButton":
            case "webradiobutton":
                WebRadioButton WRadioB = new WebRadioButton(ObjectFactory.IdentifyElements(ObjectProperties), ObjectID);
                return WRadioB;
            case "WebCheckBox":
            case "webcheckbox":
                WebCheckBox WCheckB = new WebCheckBox(ObjectFactory.IdentifyElements(ObjectProperties), ObjectID);
                return WCheckB;
            case "WebTable":
            case "webtable":
                WebTable WTable = new WebTable(ObjectFactory.IdentifyElement(ObjectProperties), ObjectID);
                return WTable;
            case "WebList":
            case "weblist":
                WebList WList = new WebList(ObjectFactory.IdentifyElement(ObjectProperties), ObjectID);
                return WList;
            case "Element":
            case "element":
            default:
                Element Element = new Element(ObjectFactory.IdentifyElement(ObjectProperties), ObjectID);
                return Element;
        }
    }








    //* Action methods on Elements

    public void click() {
        try {
            this.Element.click();

        } catch (Exception e) {

            Reporter.Report("Element '" + ObjectID + "' is not available", "Exception:" + e.getLocalizedMessage(), "Fail");
            LogUpdater.updateLog("Error", "Exception raised while clicking on WebElement " + ObjectID + " Exception:" + e.getMessage());
        }
    }

    public boolean isDisplayed() {
        try {
            return this.Element.isDisplayed();
        } catch (Exception e) {
            Reporter.Report("Element '" + ObjectID + "' is not available", e.getLocalizedMessage(), "Fail");
            LogUpdater.updateLog("Error", "Exception raised while checking WebElement visibility" + ObjectID);
        }

        return false;
    }

    public boolean isEnabled() {
        try {
            return this.Element.isEnabled();
        } catch (Exception e) {
            Reporter.Report("Element '" + ObjectID + "' is not available", e.getLocalizedMessage(), "Fail");
            LogUpdater.updateLog("Error", "Exception raised while checking WebElement disability " + ObjectID);
        }

        return false;
    }

    public void clear() {
        try {
            this.Element.clear();

        } catch (Exception e) {

            Reporter.Report("Element '" + ObjectID + "' is not available", "Exception:" + e.getLocalizedMessage(), "Fail");
            LogUpdater.updateLog("Error", "Exception raised while clicking on WebElement " + ObjectID + " Exception:" + e.getMessage());
        }
    }

    public boolean Exist(int Seconds) {
        boolean Result = false;
        long startTime = System.currentTimeMillis();
        do {
            try {
                Result = this.Element.isDisplayed();
                if (Result) {
                    return true;
                } else {
                    Result = false;
                    Thread.sleep(2500);
                }
            } catch (Exception e) {
                LogUpdater.updateLog("Error", "Exception raised while checking WebElement visibility" + ObjectID);
            }

        } while ((System.currentTimeMillis() - startTime) / 1000 < Seconds);
        return false;
    }

    public String getAttribute(String Attribute) {
        try {
            if (this.Element != null) {
                return this.Element.getAttribute(Attribute);
            } else {
                return this.Elements.get(0).getAttribute(Attribute);
            }

        } catch (Exception e) {
            Reporter.Report("Element '" + WebObj.ObjectID + "' is not avaiable", e.getLocalizedMessage(), "info");
            LogUpdater.updateLog("Error", "Exception raised while geting attribute " + Attribute + " from WebElement " + ObjectID);
        }
        return null;
    }


    public String getCssValue(String arg0) {
        try {
            return this.Element.getCssValue(arg0);
        } catch (Exception e) {
            Reporter.Report("WebLink " + ObjectID + " is not idenitified", e.getMessage(), "Fail");
        }
        return null;
    }


    public Point getLocation() {
        try {
            return this.Element.getLocation();
        } catch (Exception e) {
            Reporter.Report("WebLink " + ObjectID + " is not idenitified", e.getMessage(), "Fail");
            LogUpdater.updateLog("Error", "Location value not identified from " + ObjectID);
        }
        // TODO Auto-generated method stub
        return null;
    }



    public String getTagName() {
        String TagName;
        try {
            TagName = this.Element.getTagName();
            LogUpdater.updateLog("Info", "Got tag name as '" + TagName + "' from " + ObjectID);
            return TagName;
        } catch (Exception e) {
            Reporter.Report("WebLink " + ObjectID + " is not idenitified", e.getMessage(), "Fail");
            LogUpdater.updateLog("Error", "Attribute value not identified from " + ObjectID);
        }
        return null;
    }




}