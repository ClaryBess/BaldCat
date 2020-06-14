package com.baldcat.entity;

import java.sql.Date;

public class Follow {
    private int FollowID;
    private int FollowUserID;
    private int FollowedUserID;
    private Date DateTime;

    public int getFollowID() {
        return FollowID;
    }

    public void setFollowID(int followID) {
        FollowID = followID;
    }

    public int getFollowUserID() {
        return FollowUserID;
    }

    public void setFollowUserID(int followUserID) {
        FollowUserID = followUserID;
    }

    public int getFollowedUserID() {
        return FollowedUserID;
    }

    public void setFollowedUserID(int followedUserID) {
        FollowedUserID = followedUserID;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public Follow(int followID, int followUserID, int followedUserID, Date dateTime) {
        FollowID = followID;
        FollowUserID = followUserID;
        FollowedUserID = followedUserID;
        DateTime = dateTime;
    }

    public Follow() {
    }

    @Override
    public String toString() {
        return "Follow{" +
                "FollowID=" + FollowID +
                ", FollowUserID=" + FollowUserID +
                ", FollowedUserID=" + FollowedUserID +
                ", DateTime=" + DateTime +
                '}';
    }
}
