package com.auntor.covid_19;

import java.util.ArrayList;
import java.util.List;

public class OurMainDataClass {
private String status_code;
private String message;
private Object statistics;
    private ArrayList<Raw_Data> data;


    public OurMainDataClass(String status_code, String message, ArrayList<Raw_Data> data) {
        this.status_code = status_code;
        this.message = message;
        this.data = data;
    }

    public OurMainDataClass(String status_code, String message, Object statistics, ArrayList<Raw_Data> data) {
        this.status_code = status_code;
        this.message = message;
        this.statistics = statistics;
        this.data = data;
    }

    public OurMainDataClass() {
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getStatistics() {
        return statistics;
    }

    public void setStatistics(Object statistics) {
        this.statistics = statistics;
    }

    public List<Raw_Data> getData() {
        return data;
    }

    public void setData(ArrayList<Raw_Data> data) {
        this.data = data;
    }
}
