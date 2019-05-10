package com.example.baraa.cabbh;

public class FSResult {
    private String name ;
    private int old_features ;
    private int new_features ;
    private double old_accuracy ;
    private double new_accurcay ;
    private String csv;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOld_features() {
        return old_features;
    }

    public void setOld_features(int old_features) {
        this.old_features = old_features;
    }

    public int getNew_features() {
        return new_features;
    }

    public void setNew_features(int new_features) {
        this.new_features = new_features;
    }

    public double getOld_accuracy() {
        return old_accuracy;
    }

    public void setOld_accuracy(double old_accuracy) {
        this.old_accuracy = old_accuracy;
    }

    public double getNew_accurcay() {
        return new_accurcay;
    }

    public void setNew_accurcay(double new_accurcay) {
        this.new_accurcay = new_accurcay;
    }

    public String getCsv() {
        return csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }
}
