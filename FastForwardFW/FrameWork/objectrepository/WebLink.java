package objectrepository;

import org.openqa.selenium.WebElement;


//Work with links
public  class WebLink extends WebObj {
WebElement Element=null;
String ObjectID;

// Create WebLink object by assigning Element and ObjectIDs in this and super class "WebObj"
WebLink(WebElement Element, String ObjectID){
	this.Element=Element;
	this.ObjectID=ObjectID;
	super.ObjectID=ObjectID;
	super.Element=Element;
}

public WebLink() {
}

}
