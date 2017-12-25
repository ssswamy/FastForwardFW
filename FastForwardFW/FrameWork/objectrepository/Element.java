package objectrepository;

import org.openqa.selenium.WebElement;
import reporter.Reporter;

public class Element extends WebObj {
	
	WebElement Element=null;
	String ObjectID;
	
	Element(WebElement Element, String ObjectID){
		this.Element=Element;
		this.ObjectID=ObjectID;
		super.Element=Element;
		super.ObjectID=ObjectID;
	}

	protected Element() {
		// TODO Auto-generated constructor stub
	}

	
	public String getText(){
		try{
			return this.Element.getText();
		}catch(Exception e){
			Reporter.Report("WebElement is not idenitified", e.getMessage(), "Fail");
		}
		return null;
		
	}

	
}
