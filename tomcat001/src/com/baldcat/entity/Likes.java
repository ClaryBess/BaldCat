package com.baldcat.entity;

import java.sql.Date;

public class Likes {
    private int LikeID;
    private int BlogID;
    private int UserID;
    private Date DateTime;

    public int getLikeID() {
        return LikeID;
    }

    public void setLikeID(int likeID) {
        LikeID = likeID;
    }

    public int getBlogID() {
        return BlogID;
    }

    public void setBlogID(int blogID) {
        BlogID = blogID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public Likes() {
    }

    public Likes(int likeID, int blogID, int userID, Date dateTime) {
        LikeID = likeID;
        BlogID = blogID;
        UserID = userID;
        DateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Likes{" +
                "LikeID=" + LikeID +
                ", BlogID=" + BlogID +
                ", UserID=" + UserID +
                ", DateTime=" + DateTime +
                '}';
    }
}
