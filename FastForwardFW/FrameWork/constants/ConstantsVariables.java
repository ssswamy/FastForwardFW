package constants;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

// Read Properties file and get the constant values as string
public class ConstantsVariables {
    
    private static Properties Proprties;
    private static FileReader reader=null;
    

    private static void ReadPropertyFile() {
        reader = null;
        try {
            reader = new FileReader("./Constants.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Proprties = new Properties();
        try {
            Proprties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getConstantsVariable(String ConstantName) {
        if (reader == null) {
            ConstantsVariables.ReadPropertyFile();
        }
        return Proprties.getProperty(ConstantName).toString();

    }



}