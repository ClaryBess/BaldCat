package com.baldcat.entity;

import java.sql.Date;

public class Resource {
    private int ResourceID;
    private int UserID;
    private String Path;
    private Date DateTime;

    public int getResourceID() {
        return ResourceID;
    }

    public void setResourceID(int resourceID) {
        ResourceID = resourceID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public Resource(int resourceID, int userID, String path, Date dateTime) {
        ResourceID = resourceID;
        UserID = userID;
        Path = path;
        DateTime = dateTime;
    }

    public Resource() {
    }

    @Override
    public String toString() {
        return "Resource{" +
                "ResourceID=" + ResourceID +
                ", UserID=" + UserID +
                ", Path='" + Path + '\'' +
                ", DateTime=" + DateTime +
                '}';
    }
}
