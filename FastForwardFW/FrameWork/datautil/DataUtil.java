package datautil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import constants.EnvironmentalVariables;
import executor.LogUpdater;

//* To Work with Excel files
public class DataUtil {
    private XSSFWorkbook WorkBook;
    private XSSFSheet Sheet;
    private static DataUtil TestDataDataUtil = null;

    // DataUtil Constructor to create an Object and connect other methods
    public DataUtil(String Excelpath, String SheetName) {
        XSSFWorkbook WorkBook;
        try {
            WorkBook = new XSSFWorkbook(new FileInputStream(new File(Excelpath)));
            this.WorkBook = WorkBook;
            this.Sheet = WorkBook.getSheet(SheetName);

        } catch (FileNotFoundException e) {
            LogUpdater.updateLog("ERROR", Excelpath + " file not found");

        } catch (IOException e) {
            LogUpdater.updateLog("ERROR", Excelpath + " file not found. Exception raised: " + e.getMessage());
        }

    }

    //Get Sheet based on dataUtil object(Workbook created by constructor)
    public XSSFSheet getSheet(String SheetName) {
        this.Sheet = this.WorkBook.getSheet(SheetName);
        return Sheet;
    }

    //get No of Rows used
    
	//@SuppressWarnings("deprecation")
	public int getNumberOfRowsUsed() {
        int count = 0;
        for (Row row: this.Sheet) {
            if (row.getCell(1) != null) {
                if (row.getCell(3).getCellType() == Cell.CELL_TYPE_STRING) {
                    count += 1;
                }
            }
        }
        return count;
    }

    //Get row number with cell text
    public int getRowWithCellText(String SearchString, int Column) {
        for (Row row: this.Sheet) {
            if (row.getCell(Column) != null) {
                //if(row.getCell(Column).getCellType()==Cell.CELL_TYPE_STRING){
                if (row.getCell(Column).getStringCellValue().equalsIgnoreCase(SearchString)) {
                    return row.getRowNum();
                }
                //}
            }
        }
        return -1;
    }

    //Get no of columns used	
    public int getNumberOfColumnsUsed(int RowNumber) {
            int count = 0;
            for (Row row: Sheet) {
                if (row.getCell(RowNumber) != null) {
                    if (row.getCell(RowNumber).getCellType() == Cell.CELL_TYPE_STRING) {
                        count += 1;
                    }
                }
            }
            return count;

        }
        //Get no of rows present with specific text
    public int getNumberOfRowsWithText(String Text, int ColumnNumber) {
        int count = 0;
        for (Row row: this.Sheet) {
            if (this.Sheet.getRow(row.getRowNum()).getCell(ColumnNumber).getStringCellValue().equalsIgnoreCase(Text)) {
                count = count + 1;
            }
        }
        return count;

    }


    //* Get test data from test data sheet for the current test scenario and test case
    public static String getTestData(String SheetName, String RequiredParameter) {

        String TestScenario = EnvironmentalVariables.CurrentTestScenario;
        String CurrentTCID = EnvironmentalVariables.CurrentTCID;
        XSSFSheet Sheet = null;

        if (TestDataDataUtil == null) {
            TestDataDataUtil = new DataUtil("./TestData/" + TestScenario + ".xlsx", SheetName);
        }
        Sheet = TestDataDataUtil.getSheet(SheetName);
        int TestCaseRow = TestDataDataUtil.getRowWithCellText(CurrentTCID, 0);
        int TestParameterColumn = TestDataDataUtil.getColumnWithCellText(RequiredParameter, 0);

        return TestDataDataUtil.getCellData(TestCaseRow, TestParameterColumn);
    }


    //Update test data sheet based on Test scenario and Current test case
    public static void putTestData(String SheetName, String UpdateParameter, String Value) {

        String TestScenario = EnvironmentalVariables.CurrentTestScenario;
        String CurrentTCID = EnvironmentalVariables.CurrentTCID;
        XSSFSheet Sheet = null;

        if (TestDataDataUtil == null) {
            TestDataDataUtil = new DataUtil("./TestData/" + TestScenario + ".xlsx", SheetName);
        }
        Sheet = TestDataDataUtil.getSheet(SheetName);
        int TestCaseRow = TestDataDataUtil.getRowWithCellText(CurrentTCID, 0);
        int TestParameterColumn = TestDataDataUtil.getColumnWithCellText(UpdateParameter, 0);

        Sheet.getRow(TestCaseRow).getCell(TestParameterColumn).setCellValue(Value);
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream("./TestData/" + TestScenario + ".xlsx");
            TestDataDataUtil.WorkBook.write(outputStream);

        } catch (FileNotFoundException e) {
            LogUpdater.updateLog("ERROR", "./TestData/" + TestScenario + ".xlsx file not found");

        } catch (IOException e) {
            LogUpdater.updateLog("ERROR", "./TestData/" + TestScenario + ".xlsx file not found. Exception raised: " + e.getMessage());
        }
    }



    //Get column number with a specific text
    public int getColumnWithCellText(String SearchText, int RowNumber) {
        for (int i = 0; i < this.getNumberOfColumnsUsed(RowNumber); i++) {
            if (this.Sheet.getRow(RowNumber).getCell(i).getStringCellValue().equalsIgnoreCase(SearchText)) {
                return i;

            }
        }

        return extracted();
    }

    private Integer extracted() {
        return (Integer) null;
    }


    public String getCellData(int RowNumber, int Column) {
        return this.Sheet.getRow(RowNumber).getCell(Column).getStringCellValue();
    }


    /* General Excel Methods:
    ----------------------------------------------------------*/

    private static XSSFWorkbook generalWorkBook = null;
    private static int iRowNumber = 0;

    public static void UpdateDataSheet(String WorkBookName, String SheetName, String Row, String Column, String Value) {

        try {
            generalWorkBook = new XSSFWorkbook(new FileInputStream(new File("./TestData/" + WorkBookName + ".xlsx")));
            XSSFSheet Sheet = generalWorkBook.getSheet(SheetName);
            int iColNumber = 0;
            for (int i = 0; i <= 6; i++) {
                if (Sheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(Column)) {
                    iColNumber = i;
                    break;
                }
            }
            for (int j = 1; j <= 1590; j++) {
                //if(iRowNumber==0) {
                String Comp = Sheet.getRow(j).getCell(0).getStringCellValue().trim();
                if (Comp.equalsIgnoreCase(Row)) {
                    iRowNumber = j;
                    break;
                }
            }
            //}
            Sheet.getRow(iRowNumber).getCell(iColNumber).setCellValue(Value);
            //iRowNumber=iRowNumber+1;
            FileOutputStream outputStream;
            try {
                outputStream = new FileOutputStream("./TestData/TS01.xlsx");
                generalWorkBook.write(outputStream);

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
            	LogUpdater.updateLog("Fail",SheetName+" not found "+e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
            	LogUpdater.updateLog("Fail",SheetName+" not found "+e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e) {

        } finally {
            generalWorkBook = null;
        }

    }


    public static String getdata(String WorkBookName, String SheetName, int rownum, int cellnum) {
            try {
                generalWorkBook = new XSSFWorkbook(new FileInputStream(new File("./TestData/"+WorkBookName+".xlsx")));
                return generalWorkBook.getSheet("Result").getRow(rownum).getCell(cellnum).getStringCellValue();
            } catch (FileNotFoundException e) {
            	LogUpdater.updateLog("Fail",SheetName+" not found "+e.getMessage());
            } catch (IOException e) {
            	LogUpdater.updateLog("Fail",SheetName+" not found "+e.getMessage());
            }finally {
            	generalWorkBook=null;
           
        }
			return null;
    }






}