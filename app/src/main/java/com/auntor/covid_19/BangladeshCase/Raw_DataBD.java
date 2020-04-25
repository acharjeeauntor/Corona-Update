package com.auntor.covid_19.BangladeshCase;

public class Raw_DataBD {
   int uid;
 String name;
     String bnName;
     String confirmed;
     String key;


    public Raw_DataBD(int uid, String name, String bnName, String confirmed, String key) {
        this.uid = uid;
        this.name = name;
        this.bnName = bnName;
        this.confirmed = confirmed;
        this.key = key;
    }

    public Raw_DataBD() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBnName() {
        return bnName;
    }

    public void setBnName(String bnName) {
        this.bnName = bnName;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
