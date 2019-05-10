package com.example.baraa.cabbh;

public class DiabetesResult {
    private String name;
    private int Pregnancies;
    private float BloodPressure;
    private int SkinThickness;
    private int Insulin;
    private float BMI;
    private float Function;
    private int Age;
    private int Glucose;
    private String Result;

    public int getGlucose() {
        return Glucose;
    }

    public void setGlucose(int glucose) {
        Glucose = glucose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPregnancies() {
        return Pregnancies;
    }

    public void setPregnancies(int pregnancies) {
        Pregnancies = pregnancies;
    }

    public float getBloodPressure() {
        return BloodPressure;
    }

    public void setBloodPressure(float bloodPressure) {
        BloodPressure = bloodPressure;
    }

    public int getSkinThickness() {
        return SkinThickness;
    }

    public void setSkinThickness(int skinThickness) {
        SkinThickness = skinThickness;
    }

    public int getInsulin() {
        return Insulin;
    }

    public void setInsulin(int insulin) {
        Insulin = insulin;
    }

    public float getBMI() {
        return BMI;
    }

    public void setBMI(float BMI) {
        this.BMI = BMI;
    }

    public float getFunction() {
        return Function;
    }

    public void setFunction(float function) {
        Function = function;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }
}
