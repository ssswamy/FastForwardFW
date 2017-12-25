package datautil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import executor.LogUpdater;


//Work with data base 
public class DataBase {
    private Connection conn = null;
    private Statement stmt = null;
	private ResultSet resutSet = null;

    //Constructor to create data base connection by providing url , usernmae, password
    public DataBase(String JDBC_DRIVER, String DB_URL, String USERNAME, String PASSWORD) {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            stmt = conn.createStatement();

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LogUpdater.updateLog("Info", "Data base connection is not set up" + e.getMessage());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LogUpdater.updateLog("Info", "Data base connection is not set up" + e.getMessage());
        } catch (Exception e) {
            LogUpdater.updateLog("Info", "Data base connection is not set up" + e.getMessage());
        }
    }


    // Get result set data by providing sql quiry
    public ResultSet GetData(String SQL_Query) {
        try {
            ResultSet rs = stmt.executeQuery(SQL_Query);
            this.resutSet = rs;
            return rs;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }


    // a get column values as string array
    public String[] GetData(String SQL_Query, String ColumnName) {
        String[] Requiredvalues;
        int NumberOfRows = 0;
        try {
            ResultSet rs = stmt.executeQuery(SQL_Query);
            if (rs.last()) {
                NumberOfRows = rs.getRow();
            }
            Requiredvalues = new String[NumberOfRows];
            int i = 0;
            while (rs.next()) {
                Requiredvalues[i] = rs.getString(ColumnName);
                i = i + 1;
            }
            return Requiredvalues;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    
    public void CloseDB() {
    	try {
    		resutSet.close();
    		stmt.close();
		conn.close();
		resutSet=null;
		stmt=null;
		conn=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

}