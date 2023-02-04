package com.example.myfeedapplication.application.models.local;

public class TableInfo {

    String mTableName;
    Object  rows;
    String tag;

    public TableInfo(String mTableName, Object rows,String tag) {
        this.mTableName = mTableName;
        this.rows = rows;
        this.tag = tag;
    }

    public String getmTableName() {
        return mTableName;
    }

    public void setmTableName(String mTableName) {
        this.mTableName = mTableName;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
