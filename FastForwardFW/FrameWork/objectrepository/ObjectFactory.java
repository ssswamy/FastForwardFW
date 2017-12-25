package objectrepository;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import constants.ConstantsVariables;
import constants.EnvironmentalVariables;
import datautil.DataUtil;
import executor.EventHandler;
import executor.LogUpdater;
import executor.Sync;
import reporter.Reporter;

/*Work with web objects, This class includes all Static method to work with all type web elements*/
public class ObjectFactory {
    private static String[][] ObjectProperties;
    protected static WebElement Element;
    private static DataUtil ConnectOR;
    private static XSSFSheet Sheet;
    protected WebDriver driver;

    public ObjectFactory(WebDriver driver) {
        this.driver = driver;
    }
    //*get WebLink as WebElement
    public static WebLink WebLink(String ObjectID) {
        WebElement Element = null;
        try {
            Element = getObjPrortiesANDIdentifyElement(ObjectID);
            return new WebLink(Element, ObjectID);

        } catch (Exception e) {
        	LogUpdater.updateLog("Info", ObjectID+" Web Link is not identified-"+e.getMessage());
            return new WebLink(Element, ObjectID);
        }


    }
    //*get WebEdit as WebElement 
    public static WebEdit WebEdit(String ObjectID) {
        WebElement Element = null;
        try {
            Element = getObjPrortiesANDIdentifyElement(ObjectID);
            return new WebEdit(Element, ObjectID);

        } catch (Exception e) {
        	LogUpdater.updateLog("Info", ObjectID+" Web Edit is not identified-"+e.getMessage());
            return new WebEdit(Element, ObjectID);
        }

    }
    //*get WebRadioButton as WebElement
    public static WebRadioButton WebRadioButton(String ObjectID) {
        List < WebElement > Element = null;
        try {
            Element = getObjPrortiesANDIdentifyElements(ObjectID);
            return new WebRadioButton(Element, ObjectID);


        } catch (Exception e) {
        	LogUpdater.updateLog("Info", ObjectID+" Web Radio Button is not identified-"+e.getMessage());
            return new WebRadioButton(Element, ObjectID);
        }

    }
    //*get WebCheckBox as WebElement
    public static WebCheckBox WebCheckBox(String ObjectID) {
        List < WebElement > Element = null;
        try {
            Element = getObjPrortiesANDIdentifyElements(ObjectID);
            return new WebCheckBox(Element, ObjectID);


        } catch (Exception e) {
        	LogUpdater.updateLog("Info", ObjectID+" Web Check Box is not identified-"+e.getMessage());
            return new WebCheckBox(Element, ObjectID);
        }

    }

    //*get WebList
    public static WebList WebList(String ObjectID) {
        WebElement Element = null;
        try {

            Element = getObjPrortiesANDIdentifyElement(ObjectID);
            return new WebList(Element, ObjectID);

        } catch (Exception e) {
        	LogUpdater.updateLog("Info", ObjectID+" Web List is not identified-"+e.getMessage());
            return new WebList(Element, ObjectID);
        }
    }

    //* get WebImage
    public static WebImage WebImage(String ObjectID) {
        WebElement Element = null;
        try {

            Element = getObjPrortiesANDIdentifyElement(ObjectID);
            return new WebImage(Element, ObjectID);

        } catch (Exception e) {
        	LogUpdater.updateLog("Info", ObjectID+" Web Image is not identified-"+e.getMessage());
            return new WebImage(Element, ObjectID);
        }


    }

    //*get Webelement
    public static Element Element(String ObjectID) {
        WebElement WE = null;
        try {

            WE = getObjPrortiesANDIdentifyElement(ObjectID);
            return new Element(WE, ObjectID);

        } catch (Exception e) {
        	LogUpdater.updateLog("Info", ObjectID+" Web Element is not identified-"+e.getMessage());
            return new Element(WE, ObjectID);
        }
    }

    //*get WebTable
    public static WebTable WebTable(String ObjectID) {
        WebElement Element = null;
        try {

            Element = getObjPrortiesANDIdentifyElement(ObjectID);
            return new WebTable(Element, ObjectID);

        } catch (Exception e) {
        	LogUpdater.updateLog("Info", ObjectID+" Web Table is not identified-"+e.getMessage());
            return new WebTable(Element, ObjectID);
        }


    }

    //* get WebFrame as Driver
    public static WebFrame WebFrame(WebElement Element) {
        WebDriver driver = EnvironmentalVariables.driver;
        try {
            if (Element != null) {
                driver = driver.switchTo().frame(Element);

                return new WebFrame(driver);
            } else {
                LogUpdater.updateLog("Error", "Webelement is null to swich to frame");
                return new WebFrame(null);
            }
        } catch (Exception e) {
            LogUpdater.updateLog("Error", "Exception raised to swich to frame" + e.getMessage());

        }
        return null;

    }



//Get object Properties and identify elements
    protected static List < WebElement > getObjPrortiesANDIdentifyElements(String ObjectID) {
        try {
            List < WebElement > WE = null;

            ObjectProperties = GetObjectProperties(ObjectID);
            WE = IdentifyElements(ObjectProperties);
            return WE;
        } catch (Exception e) {
            LogUpdater.updateLog("Info", "Exception raised at finding " + ObjectID + e.getMessage());
            return null;
        }
        // return null;

    }

    //Get object Properties and identify element
    protected static WebElement getObjPrortiesANDIdentifyElement(String ObjectID) {
        try {
            WebElement Element = null;
            ObjectProperties = GetObjectProperties(ObjectID);
            Element = IdentifyElement(ObjectProperties);
            return Element;

        } catch (Exception e) {
            LogUpdater.updateLog("Info", "Exception raised at finding " + ObjectID + e.getMessage());
            return null;
        }
        // return null;
    }


    //* Get any Object properties from OR sheet
    static String[][] GetObjectProperties(String ObjectID) {

        int Object_Row, NoOfProperties = 0;
        String Property, PropertyValue;
        //*Create Object Repository connection
        if (ConnectOR == null) {
            ConnectOR = new DataUtil(ConstantsVariables.getConstantsVariable("Object_Repository_Path"), "ObjectRepository");
            Sheet = ConnectOR.getSheet("ObjectRepository");
        }
        Object_Row = ConnectOR.getRowWithCellText(ObjectID, 0);
        
        for (Cell cell: Sheet.getRow(Object_Row)) {
            if (cell.getStringCellValue() != null) {
                PropertyValue = cell.getStringCellValue();
                Property = Sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();
                if (Property.substring(0, 4).equalsIgnoreCase("obj_")) {
                    if (PropertyValue.length() != 0) {
                        NoOfProperties = NoOfProperties + 1;
                    }
                }
            }
        }

        //        for (int counter = 1; counter < Sheet.getRow(Object_Row).getPhysicalNumberOfCells(); counter++) {
        //            PropertyValue = Sheet.getRow(Object_Row).getCell(counter).getStringCellValue();
        //            Property = Sheet.getRow(0).getCell(counter).getStringCellValue();
        //            if (Property.substring(0, 4).equalsIgnoreCase("obj_")) {
        //                if (PropertyValue.length() != 0) {
        //                    NoOfProperties = NoOfProperties + 1;
        //                }
        //            }
        //        }

        String[][] ObjectProperties = new String[NoOfProperties][2];
        int PropertyIndex = 0;

        for (int counter = 1; counter < Sheet.getRow(Object_Row).getPhysicalNumberOfCells(); counter++) {
            PropertyValue = Sheet.getRow(Object_Row).getCell(counter).getStringCellValue();
            Property = Sheet.getRow(0).getCell(counter).getStringCellValue();

            if (Property.substring(0, 4).equalsIgnoreCase("obj_")) {
                if (PropertyValue.length() != 0) {
                    ObjectProperties[PropertyIndex][0] = Property;
                    ObjectProperties[PropertyIndex][1] = PropertyValue;
                    
                    PropertyIndex += 1;
                }
            }


        }

        return ObjectProperties;

    }

    static WebElement IdentifyElement(String[][] ObjectProperties) {
        String Index = null;
        WebDriver driver;
        for (int count = 0; count <= ObjectProperties.length; count++) {
            String Property = ObjectProperties[count][0];
            String PropertyValue = ObjectProperties[count][1];
            switch (Property) {
                case "obj_LinkText":

                    try {
                        driver = EnvironmentalVariables.driver;
                        List < WebElement > element = driver.findElements(By.linkText(PropertyValue));
                        if (element.size() > 1) {

                            Index = getIndex(ObjectProperties);
                            Element = element.get(Integer.parseInt(Index));
                            return element.get(Integer.parseInt(Index));
                        } else {

                            Element = element.get(0);
                            return element.get(0);
                        }

                    } catch (Exception e) {
                        LogUpdater.updateLog("Info", "Exception raised at finding " + e.getMessage());

                        throw (e);
                    } finally {
                        driver = null;
                    }
                case "obj_ID":
                    try {
                        driver = EnvironmentalVariables.driver;
                        List < WebElement > element = driver.findElements(By.id(PropertyValue));
                        if (element.size() > 1) {
                            Index = getIndex(ObjectProperties);
                            Element = element.get(Integer.parseInt(Index));
                            return element.get(Integer.parseInt(Index));
                        } else {
                            return element.get(0);
                        }

                    } catch (Exception e) {
                        throw (e);
                    }
                case "obj_Name":
                    try {
                        driver = EnvironmentalVariables.driver;
                        List < WebElement > element = driver.findElements(By.name(PropertyValue));

                        if (element.size() > 1) {
                            Index = getIndex(ObjectProperties);
                            Element = element.get(Integer.parseInt(Index));
                            return element.get(Integer.parseInt(Index));
                        } else {
                            //this.Element=element.get(0);
                            //element.get(0).sendKeys("TEST");
                            return element.get(0);
                        }

                    } catch (Exception e) {
                        throw (e);
                    }
                case "obj_Class":
                    try {
                        driver = EnvironmentalVariables.driver;
                        List < WebElement > element = driver.findElements(By.className(PropertyValue));
                        if (element.size() > 1) {
                            Index = getIndex(ObjectProperties);
                            Element = element.get(Integer.parseInt(Index));
                            return element.get(Integer.parseInt(Index));
                        } else {
                            return element.get(0);
                        }

                    } catch (Exception e) {
                        throw (e);
                    }
                case "obj_xPath":
                    try {
                        driver = EnvironmentalVariables.driver;
                        List < WebElement > element = driver.findElements(By.xpath(PropertyValue));
                        if (element.size() > 1) {
                            Index = getIndex(ObjectProperties);
                            Element = element.get(Integer.parseInt(Index));
                            return element.get(Integer.parseInt(Index));
                        } else {
                            Element = element.get(0);
                            return element.get(0);
                        }

                    } catch (Exception e) {
                        throw (e);
                    }
            }
        }
        return null;


    }

    static List < WebElement > IdentifyElements(String[][] ObjectProperties) {
        for (int count = 0; count < ObjectProperties.length; count++) {
            String Property = ObjectProperties[count][0];
            String PropertyValue = ObjectProperties[count][1];
            switch (Property) {

                case "obj_Name":
                    try {
                        WebDriver driver = EnvironmentalVariables.driver;
                        Sync.Wait(1);
                        List < WebElement > elements = driver.findElements(By.name(PropertyValue));
                        return elements;

                    } catch (Exception e) {
                        throw (e);
                    }
                case "obj_ID":
                    try {
                        WebDriver driver = EnvironmentalVariables.driver;
                        Sync.Wait(1);
                        List < WebElement > elements = driver.findElements(By.id(PropertyValue));
                        return elements;

                    } catch (Exception e) {
                        throw (e);
                    }
                case "obj_xPath":
                    try {
                        WebDriver driver = EnvironmentalVariables.driver;
                        Sync.Wait(1);
                        List < WebElement > elements = driver.findElements(By.xpath(PropertyValue));
                        return elements;

                    } catch (Exception e) {
                        throw (e);
                    }
            }
        }
        return null;


    }

    private static String getIndex(String[][] ObjectProperties) {

        for (int count = 0; count < ObjectProperties.length; count++) {
            String Property = ObjectProperties[count][0];
            if (Property.equalsIgnoreCase("obj_index")) {
                return ObjectProperties[count][1];
            }
        }

        return null;
    }

    public static boolean Open(String URL) {
        WebDriver driver = null;
        switch (EnvironmentalVariables.Browser) {
            case "FF":
            case "FireFox":
                System.setProperty("webdriver.gecko.driver", ConstantsVariables.getConstantsVariable("GeckodriverPath"));
                driver = new FirefoxDriver();
                EnvironmentalVariables.driver = driver;
                EventHandler.StartEvenListener(driver);
                break;
            case "GC":
            case "Google Chrome":
                System.setProperty("webdriver.chrome.driver", ConstantsVariables.getConstantsVariable("ChromeDriverPath"));
                driver = new ChromeDriver();
                EnvironmentalVariables.driver = driver;
                EventHandler.StartEvenListener(driver);
                break;
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.get(URL);
        Reporter.Report("Open Browser", "Browser is opened successfully", "Pass");


        return true;

    }





}