package executor;

import org.apache.log4j.Logger;
import org.apache.log4j.extras.DOMConfigurator;

public class LogUpdater {

	//private static Logger Log = Logger.getLogger(LogUpdater.getCallerClass(2));
	enum Level{
		INFO,PASS,FAIL,DONE,WARNING
	}
	
	public static void updateLog(String Level, String Description) {
	try{
		Logger Log = Logger.getLogger(getCallerClassName(2));
		DOMConfigurator.configure("./FrameWork/log4j.xml");	
		switch(Level){
		case "Info":
		case "info":
		case "INFO":
		{
			Log.info(Description);
			break;
		}
		case "Error":
		case "ERROR":
		case "error":
		{
			Log.error(Description);
			break;
		}
		case "Warn":
		case "WARN":
		case "warn":
		{
			Log.warn(Description);
			break;
		}
			
		}
		
	
	}catch(Exception e){
		
	}
	}
	
	
	private static String getCallerClassName(int level) throws ClassNotFoundException {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        String rawFQN = stElements[level+1].toString().split("\\(")[0];
        return Class.forName(rawFQN.substring(0, rawFQN.lastIndexOf('.'))).getName();
    }
	
	public static void updateLog(String Level,String Description, Exception exp){
		try {
			Logger Log = Logger.getLogger(getCallerClassName(2));
			DOMConfigurator.configure("log4j.xml");	
			Log.warn(Description, exp);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
