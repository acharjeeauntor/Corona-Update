package com.auntor.covid_19;

public class Raw_Data {
    private int id;
    private String country;
    private String total_case;
    private String total_death;
    private String total_recovered;
    private String new_case;
    private String new_death;
    private String active_case;
    private String serious_critical;
    private String tot_case;
    private String created_at;
    private String updated_at;

    public Raw_Data(int id, String country, String total_case, String total_death, String total_recovered, String new_case, String new_death, String active_case, String serious_critical, String tot_case, String created_at, String updated_at) {
        this.id = id;
        this.country = country;
        this.total_case = total_case;
        this.total_death = total_death;
        this.total_recovered = total_recovered;
        this.new_case = new_case;
        this.new_death = new_death;
        this.active_case = active_case;
        this.serious_critical = serious_critical;
        this.tot_case = tot_case;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Raw_Data() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTotal_case() {
        return total_case;
    }

    public void setTotal_case(String total_case) {
        this.total_case = total_case;
    }

    public String getTotal_death() {
        return total_death;
    }

    public void setTotal_death(String total_death) {
        this.total_death = total_death;
    }

    public String getTotal_recovered() {
        return total_recovered;
    }

    public void setTotal_recovered(String total_recovered) {
        this.total_recovered = total_recovered;
    }

    public String getNew_case() {
        return new_case;
    }

    public void setNew_case(String new_case) {
        this.new_case = new_case;
    }

    public String getNew_death() {
        return new_death;
    }

    public void setNew_death(String new_death) {
        this.new_death = new_death;
    }

    public String getActive_case() {
        return active_case;
    }

    public void setActive_case(String active_case) {
        this.active_case = active_case;
    }

    public String getSerious_critical() {
        return serious_critical;
    }

    public void setSerious_critical(String serious_critical) {
        this.serious_critical = serious_critical;
    }

    public String getTot_case() {
        return tot_case;
    }

    public void setTot_case(String tot_case) {
        this.tot_case = tot_case;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
