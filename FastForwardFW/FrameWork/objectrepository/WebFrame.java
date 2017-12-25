package objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import constants.EnvironmentalVariables;

public class WebFrame extends ObjectFactory{
	WebElement Element = null;
	WebDriver driver=null;

    protected WebFrame(WebDriver driver) {
    	super(driver);
	this.driver=driver;
	}

    public void GetFrameAndWorkOnSameFrame() {
    EnvironmentalVariables.driver=driver;
    }
	
    public void SwitchToDefaultFrame() {
    	EnvironmentalVariables.driver=driver.switchTo().defaultContent();
    }





}