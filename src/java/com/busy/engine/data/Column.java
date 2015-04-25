
package com.busy.engine.data;


public class Column 
{
    private String columnName;
    private String columnValue;
    private boolean numeric;

    public Column(String columnName, String columnValue, boolean isNumeric) {
        this.columnName = columnName;
        this.columnValue = columnValue;
        this.numeric = isNumeric;
    }
    
    public Column(String columnName, String columnValue) {
        this.columnName = columnName;
        this.columnValue = columnValue;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(String columnValue) {
        this.columnValue = columnValue;
    }

    public boolean isNumeric() {
        return numeric;
    }

    public void setNumeric(boolean isNumeric) {
        this.numeric = isNumeric;
    }
    
    
}
