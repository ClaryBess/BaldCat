package com.baldcat.entity;

import java.util.Date;

public class User {
    private Integer UserID;
    private String Name;
    private String Password;
    private String Email;
    private String Gender;
    private Date Birthday;
    private String Introduction;
    private Integer FansNumber;

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public String printGender() {
        switch (getGender()){
            case "F":
                return "女";
            case "M":
                return "男";
            case "S":
                return "保密";
        }
        return getGender();
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date birthday) {
        Birthday = birthday;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public Integer getFansNumber() {
        return FansNumber;
    }

    public void setFansNumber(Integer fansNumber) {
        FansNumber = fansNumber;
    }

    public User(Integer userID, String name, String password, String email, String gender, Date birthday, String introduction, Integer fansNumber) {
        UserID = userID;
        Name = name;
        Password = password;
        Email = email;
        Gender = gender;
        Birthday = birthday;
        Introduction = introduction;
        FansNumber = fansNumber;
    }

    public User(){
    }

    @Override
    public String toString() {
        return "User{" +
                "UserID=" + UserID +
                ", Name='" + Name + '\'' +
                ", Password='" + Password + '\'' +
                ", Email='" + Email + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Birthday=" + Birthday +
                ", Introduction='" + Introduction + '\'' +
                ", FansNumber=" + FansNumber +
                '}';
    }
}
