package objectrepository;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import executor.LogUpdater;
import reporter.Reporter;


//Work with Web tables
public class WebTable extends WebObj {
    String ObjectID;
    @SuppressWarnings("unused")
    private WebElement Element;
    @SuppressWarnings("unused")
    private int Rows, Columns;
    private List < WebElement > oRows, oCol;

    WebTable(WebElement Element, String ObjectID) {
        if (Element != null) {
            this.Element = Element;
            Rows = Element.findElements(By.tagName("tr")).size();
            oRows = Element.findElements(By.tagName("tr"));
        } else {
            this.Element = null;
            Rows = 0;
            oRows = null;
        }

        this.ObjectID = ObjectID;
    }

    WebTable() {
        // TODO Auto-generated constructor stub
    }

    //* get Cell data based on Row,Column Number	
    public String GetCelldata(int Row, int Column) {
        try {
            if (Row <= oRows.size()) {

                if (Row == 1) {
                    oCol = oRows.get(Row - 1).findElements(By.tagName("th"));
                    if (oCol == null) {
                        oCol = oRows.get(Row - 1).findElements(By.tagName("td"));
                    }
                } else {
                    oCol = oRows.get(Row - 1).findElements(By.tagName("td"));
                }

                if (Column <= oRows.get(Row - 1).findElements(By.tagName("td")).size()) {
                    return oCol.get(Column - 1).getText();
                } else {
                    Reporter.Report("Out of the Column Count to get cell data from Table '" + ObjectID + "'", "Column " + Column + " not avaible in in table. Actual Columns :" + oCol.size(), "Fail");
                    LogUpdater.updateLog("ERROR", "Out of the Column Count to get cell data from Table '" + ObjectID + "'" + "Column " + Column + " not avaible in in table. Actual Columns :" + oCol.size());
                }

            } else {
                Reporter.Report("Out of the Row Count to get cell data from Table '" + ObjectID + "'", "Row " + Row + " not avaible in in table. Actual Rows :" + oRows.size(), "Fail");
                LogUpdater.updateLog("ERROR", "Out of the Row Count to get cell data from Table '" + ObjectID + "'" + "Row " + Row + " not avaible in in table. Actual Columns :" + oRows.size());
            }
        } catch (Exception e) {
            Reporter.Report("Failed to get cell data from Table '" + ObjectID + "'", "Webtable not available" + e.toString(), "Fail");
            LogUpdater.updateLog("ERROR", "Failed to get cell data from Table '" + ObjectID + "' Webtable not available" + e.toString());
        }

        return null;

    }


    //get the child element in a webtable
    public WebObj GetChildElement(int Row, int Column, int index) {
        try {
            if (Row <= oRows.size()) {

                if (Row == 1) {
                    oCol = oRows.get(Row - 1).findElements(By.tagName("th"));
                    if (oCol == null) {
                        oCol = oRows.get(Row - 1).findElements(By.tagName("td"));
                    }
                } else {
                    oCol = oRows.get(Row - 1).findElements(By.tagName("td"));
                }

                if (Column <= oRows.get(Row - 1).findElements(By.tagName("td")).size()) {
                    List < WebElement > ChildItems = oCol.get(Column - 1).findElements(By.xpath(".//*"));

                    return new WebObj(ChildItems.get(index), "");
                } else {
                    Reporter.Report("Out of the Column Count to get cell data from Table '" + ObjectID + "'", "Column " + Column + " not avaible in in table. Actual Columns :" + oCol.size(), "Fail");
                    LogUpdater.updateLog("ERROR", "Out of the Column Count to get cell data from Table '" + ObjectID + "'" + "Column " + Column + " not avaible in in table. Actual Columns :" + oCol.size());
                }

            } else {
                Reporter.Report("Out of the Row Count to get cell data from Table '" + ObjectID + "'", "Row " + Row + " not avaible in in table. Actual Rows :" + oRows.size(), "Fail");
                LogUpdater.updateLog("ERROR", "Out of the Row Count to get cell data from Table '" + ObjectID + "'" + "Row " + Row + " not avaible in in table. Actual Columns :" + oRows.size());
            }
        } catch (Exception e) {
            Reporter.Report("Failed to get cell data from Table '" + ObjectID + "'", "Webtable not available" + e.toString(), "Fail");
            LogUpdater.updateLog("ERROR", "Failed to get cell data from Table '" + ObjectID + "' Webtable not available" + e.toString());
        }

        return null;

    }

    public WebTable GetChildElementAsTable(int Row, int Column, int index) {
        try {
            if (Row <= oRows.size()) {

                if (Row == 1) {
                    oCol = oRows.get(Row - 1).findElements(By.tagName("th"));
                    if (oCol == null) {
                        oCol = oRows.get(Row - 1).findElements(By.tagName("td"));
                    }
                } else {
                    oCol = oRows.get(Row - 1).findElements(By.tagName("td"));
                }

                if (Column <= oRows.get(Row - 1).findElements(By.tagName("td")).size()) {
                    List < WebElement > ChildItems = oCol.get(Column - 1).findElements(By.xpath(".//*"));
                    this.Element = ChildItems.get(index);
                    return new WebTable(ChildItems.get(index), "");
                } else {
                    Reporter.Report("Out of the Column Count to get cell data from Table '" + ObjectID + "'", "Column " + Column + " not avaible in in table. Actual Columns :" + oCol.size(), "Fail");
                    LogUpdater.updateLog("ERROR", "Out of the Column Count to get cell data from Table '" + ObjectID + "'" + "Column " + Column + " not avaible in in table. Actual Columns :" + oCol.size());
                }

            } else {
                Reporter.Report("Out of the Row Count to get cell data from Table '" + ObjectID + "'", "Row " + Row + " not avaible in in table. Actual Rows :" + oRows.size(), "Fail");
                LogUpdater.updateLog("ERROR", "Out of the Row Count to get cell data from Table '" + ObjectID + "'" + "Row " + Row + " not avaible in in table. Actual Columns :" + oRows.size());
            }
        } catch (Exception e) {
            Reporter.Report("Failed to get cell data from Table '" + ObjectID + "'", "Webtable not available" + e.toString(), "Fail");
            LogUpdater.updateLog("ERROR", "Failed to get cell data from Table '" + ObjectID + "' Webtable not available" + e.toString());
        }

        return null;

    }
    //* Get row number with a String
    public int GetRowWithCellText(String SearchString) {
        try {
            for (int i = 0; i < Rows; i++) {
                if (i == 0) {
                    oCol = oRows.get(i).findElements(By.tagName("th"));
                } else {
                    oCol = oRows.get(i).findElements(By.tagName("td"));
                }

                for (int j = 0; j < oCol.size(); j++) {
                    if (oCol.get(j).getText().equalsIgnoreCase(SearchString)) {
                        return i + 1;
                    }

                }

            }
        } catch (Exception e) {
            Reporter.Report("Failed to get Row Number from Table '" + ObjectID + "' with " + SearchString, "Webtable not available" + e.toString(), "Fail");
            LogUpdater.updateLog("ERROR", "Failed to get row number from Table '" + ObjectID + "' with " + SearchString + " Webtable not available" + e.toString());

        }

        return -1;
    }

    public int GetRowWithCellText(String SearchString, int ColumnNumber) {
        try {
            for (int i = 0; i < oRows.size(); i++) {
                if (i == 0) {
                    oCol = oRows.get(i).findElements(By.tagName("th"));
                } else {
                    oCol = oRows.get(i).findElements(By.tagName("td"));
                }

                if (ColumnNumber < oCol.size()) {
                    if (oCol.get(ColumnNumber - 1).getText().equalsIgnoreCase(SearchString)) {
                        return i + 1;
                    }
                }



            }
        } catch (Exception e) {
            Reporter.Report("Failed to get Row Number from Table '" + ObjectID + "' with " + SearchString, "Webtable not available" + e.toString(), "Fail");
            LogUpdater.updateLog("ERROR", "Failed to get row number from Table '" + ObjectID + "' with " + SearchString + " Webtable not available" + e.toString());

        }

        return -1;
    }

    public int getColumnNumberWithtext(String ColumnName) {
        int tempRefColIndex = 1;
        if (oRows.size() != 0) {
            oCol = oRows.get(0).findElements(By.tagName("th"));
            if (oCol == null) {
                oCol = oRows.get(0).findElements(By.tagName("td"));
            }
        }


        for (WebElement WE: oCol) {
            if (WE.getText().equalsIgnoreCase(ColumnName)) {
                return tempRefColIndex - 1;
            } else {
                tempRefColIndex += 1;
            }
        }

        return -1;

    }



    public WebObj ActionOnChildElementBasedOnColumns(String RefColumn, String RefValue, String RequiredColumn) {
        int RefColIndex, ReqColIndex, ReqRowIndex;

        try {
            RefColIndex = getColumnNumberWithtext(RefColumn);
            ReqColIndex = getColumnNumberWithtext(RequiredColumn);
            ReqRowIndex = GetRowWithCellText(RefValue, RefColIndex);

            return new WebObj(oRows.get(ReqRowIndex - 1).findElements(By.tagName("td")).get(ReqColIndex - 1), "");


        } catch (Exception e) {
            Reporter.Report("Failed to get cell data from Table '" + ObjectID + "'", "Webtable not available" + e.toString(), "Fail");
            LogUpdater.updateLog("ERROR", "Failed to get cell data from Table '" + ObjectID + "' Webtable not available" + e.toString());
        }

        return null;

    }
}