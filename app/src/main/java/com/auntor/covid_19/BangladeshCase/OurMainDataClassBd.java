package com.auntor.covid_19.BangladeshCase;

import java.util.ArrayList;
import java.util.List;

public class OurMainDataClassBd {
private String type;
    private ArrayList<SecondMainDataClass> features;

    public OurMainDataClassBd(String type, ArrayList<SecondMainDataClass> features) {
        this.type = type;
        this.features = features;
    }


    public OurMainDataClassBd() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<SecondMainDataClass> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<SecondMainDataClass> features) {
        this.features = features;
    }
}
