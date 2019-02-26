package com.login.valeriya.feelit.Utils;


public class User {
    private String uid = "0";
    private int sex = 0;
    private int birth_year = 1970;
    private int lifestyle = 0;
    private int foodstyle = 0;
    private int weight = 75;
    private int height = 175;

    public User() {
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public int getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(int lifestyle) {
        this.lifestyle = lifestyle;
    }

    public int getFoodstyle() {
        return foodstyle;
    }

    public void setFoodstyle(int foodstyle) {
        this.foodstyle = foodstyle;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSexString()
    {
        switch (sex) {
            case 0:
                return "Male";
            case 1:
                return "Female";
            case 2:
                return "N/A";
        }
        return "(sex)";
    }

    public String getLifestyleString()
    {
        switch (sex) {
            case 0:
                return "Active";
            case 1:
                return "Middle-active";
            case 2:
                return "Passive";
        }
        return "(lifestyle)";
    }

    public String getFoodstyleString()
    {
        switch (sex) {
            case 0:
                return "Normal";
            case 1:
                return "Vegeterian";
            case 2:
                return "Vegan";
        }
        return "(foodstyle)";
    }

    public String getBirth_yearString()
    {
        return Integer.toString(birth_year);
    }
    public String getWeightString()
    {
        return Integer.toString(weight);
    }
    public String getHeightString()
    {
        return Integer.toString(height);
    }
}

