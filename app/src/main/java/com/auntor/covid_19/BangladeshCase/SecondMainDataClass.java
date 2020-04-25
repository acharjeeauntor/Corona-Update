package com.auntor.covid_19.BangladeshCase;

import java.util.ArrayList;

public class SecondMainDataClass {

    private String type;
    private Object geometry;
    private ArrayList<Raw_DataBD> properties;


    public SecondMainDataClass(String type, Object geometry, ArrayList<Raw_DataBD> properties) {
        this.type = type;
        this.geometry = geometry;
        this.properties = properties;
    }

    public SecondMainDataClass() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getGeometry() {
        return geometry;
    }

    public void setGeometry(Object geometry) {
        this.geometry = geometry;
    }

    public ArrayList<Raw_DataBD> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Raw_DataBD> properties) {
        this.properties = properties;
    }
}
