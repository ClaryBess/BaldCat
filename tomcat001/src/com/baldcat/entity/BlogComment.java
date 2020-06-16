package com.baldcat.entity;

import com.baldcat.repository.UserRepository;

import java.sql.Date;

public class BlogComment {
    private int CommentID;
    private int BlogID;
    private int UserID;
    private Date DateTime;
    private String Content;

    public int getCommentID() {
        return CommentID;
    }

    public void setCommentID(int commentID) {
        CommentID = commentID;
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

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    /**
     * 获取该评论的用户名
     * @return
     */
    public String getUserName(){
        UserRepository userRepository=new UserRepository();
        User user=userRepository.findById(UserID);
        return user.getName();
    }

    public BlogComment() {
    }

    public BlogComment(int commentID, int blogID, int userID, Date dateTime, String content) {
        CommentID = commentID;
        BlogID = blogID;
        UserID = userID;
        DateTime = dateTime;
        Content = content;
    }

    @Override
    public String toString() {
        return "BlogComment{" +
                "CommentID=" + CommentID +
                ", BlogID=" + BlogID +
                ", UserID=" + UserID +
                ", DateTime=" + DateTime +
                ", Content='" + Content + '\'' +
                '}';
    }
}
