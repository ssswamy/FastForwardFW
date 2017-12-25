package executor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

import constants.EnvironmentalVariables;

public class EventHandler implements WebDriverEventListener {

	@Override
	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub
		//LogUpdater.updateLog("Info", "Changed value of WebElement "+arg0.toString());	
		
	}

	@Override
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		
		LogUpdater.updateLog("Info", "Clicked on WebElement "+arg0.toString());	
	}

	@Override
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		
		LogUpdater.updateLog("Info", "Identified WebElement "+arg0.toString()+" using "+arg0.toString());
	}

	@Override
	public void afterNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		LogUpdater.updateLog("Info", " Navigated to "+arg0.getTitle());	
		
	}

	@Override
	public void afterNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		LogUpdater.updateLog("Info", "Navigated forward "+arg0.getTitle());
		
	}

	@Override
	public void afterNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		LogUpdater.updateLog("Info", "Navigated to "+arg0.getTitle()+" and Refreshed");	
		
	}

	@Override
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		
		LogUpdater.updateLog("Info", "WebDriber navigared to "+arg0.toString()+ " - "+arg1.getTitle());
		
	}

	@Override
	public void afterScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		LogUpdater.updateLog("Info", arg0+" Script Executed "+arg1.getTitle());	
		
	}

	@Override
	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		// TODO Auto-generated method stub
		//LogUpdater.updateLog("Info", "About to change value of WebElement "+arg0.toString()+" to "+arg2.toString());	
		
	}

	@Override
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		//LogUpdater.updateLog("Info", "ABout to click on WebElement "+arg0.toString());	
		
	}

	@Override
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		//LogUpdater.updateLog("Info", "About to Find WebElement "+arg0.toString()+" using "+arg0.toString());
		
	}

	@Override
	public void beforeNavigateBack(WebDriver arg0) {
		// TODO Auto-generated method stub
		
		//LogUpdater.updateLog("Info", "About to Navigated back from page "+arg0.getTitle());
	}

	@Override
	public void beforeNavigateForward(WebDriver arg0) {
		// TODO Auto-generated method stub
		//LogUpdater.updateLog("Info", "About to Navigated forward from page "+arg0.getTitle());
		
	}

	@Override
	public void beforeNavigateRefresh(WebDriver arg0) {
		// TODO Auto-generated method stub
		//LogUpdater.updateLog("Info", "About to Navigate back from page "+arg0.getTitle()+" and Refresh");	
		
	}

	@Override
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		//LogUpdater.updateLog("Info", "About to Navigate from "+arg1.getTitle()+" to "+arg0);	
	}

	@Override
	public void beforeScript(String arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		LogUpdater.updateLog("Info", arg0+" : Before Script Execute on page "+arg1.getTitle());	
	}

	@Override
	public void onException(Throwable arg0, WebDriver arg1) {
		// TODO Auto-generated method stub
		//LogUpdater.updateLog("Info", arg0+" Exception occured at "+arg1.getTitle());	
	}
	public static void StartEvenListener(WebDriver driver)
	{
		EventFiringWebDriver eventDriver;
		EventHandler handler;
		eventDriver = new EventFiringWebDriver(driver);
		handler = new EventHandler();
		EnvironmentalVariables.driver=eventDriver;
		eventDriver.register(handler);
	}
	

}
