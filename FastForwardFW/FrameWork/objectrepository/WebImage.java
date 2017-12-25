package objectrepository;

import org.openqa.selenium.WebElement;

public class WebImage extends WebObj {

	public WebElement Element;
	private String ObjectID;

	WebImage(WebElement element, String ObjectID) {
		this.Element=element;
		this.ObjectID=ObjectID;
	}


	public WebImage() {
		// TODO Auto-generated constructor stub
		System.out.print(ObjectID);
	}
	
	


	
}
