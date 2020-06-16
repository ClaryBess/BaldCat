package com.baldcat.entity;

import com.baldcat.repository.UserRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Blog {
    private int BlogID;
    private int UserID;
    private String Title;
    private Date DateTime;
    private String Content;
    private int LikeNumber;
    private int CommentNumber;
    private String Path;
    private String Tag1,Tag2,Tag3,Tag4,Tag5;

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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
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

    public int getLikeNumber() {
        return LikeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        LikeNumber = likeNumber;
    }

    public int getCommentNumber() {
        return CommentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        CommentNumber = commentNumber;
    }

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    public String getTag1() {
        return Tag1;
    }

    public void setTag1(String tag1) {
        Tag1 = tag1;
    }

    public String getTag2() {
        return Tag2;
    }

    public void setTag2(String tag2) {
        Tag2 = tag2;
    }

    public String getTag3() {
        return Tag3;
    }

    public void setTag3(String tag3) {
        Tag3 = tag3;
    }

    public String getTag4() {
        return Tag4;
    }

    public void setTag4(String tag4) {
        Tag4 = tag4;
    }

    public String getTag5() {
        return Tag5;
    }

    public void setTag5(String tag5) {
        Tag5 = tag5;
    }

    /**
     * 获取博客的所有标签
     * @return
     */
    public List<String> getTags(){
        List<String> tag=new ArrayList<>();
        if(getTag1()!=null)
            tag.add(getTag1());
        if(getTag2()!=null)
            tag.add(getTag2());
        if(getTag3()!=null)
            tag.add(getTag3());
        if(getTag4()!=null)
            tag.add(getTag4());
        if(getTag5()!=null)
            tag.add(getTag5());
        return tag;
    }

    /**
     * 获取博客作者用户名
     * @return
     */
    public String getUserName(){
        UserRepository userRepository=new UserRepository();
        User user=userRepository.findById(UserID);
        return user.getName();
    }

    /**
     * 获取年份
     * @return
     */
    public String getYear(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
        Date date=new Date();
        return sdf.format(DateTime);
    }

    /**
     * 获取月份日期
     * @return
     */
    public String getMonthDay(){
        SimpleDateFormat sdf=new SimpleDateFormat("MM.dd");
        Date date=new Date();
        return sdf.format(DateTime);
    }

    /**
     * 获取摘要
     * @param length
     * @return
     */
    public String getAbstarct(int length) {
        String input=getContent();
        if (input == null || input.trim().equals("")) {
            return "";
        }
        // 去掉所有html元素,
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                "<[^>]*>", "");
        str = str.replaceAll("[(/>)<]", "");
        int len = str.length();
        if (len <= length) {
            return str;
        } else {
            str = str.substring(0, length);
            str += "......";
        }
        return str;
    }

    public Blog(int blogID, int userID, String title, Date dateTime, String content, int likeNumber, int commentNumber, String path, String tag1, String tag2, String tag3, String tag4, String tag5) {
        BlogID = blogID;
        UserID = userID;
        Title = title;
        DateTime = dateTime;
        Content = content;
        LikeNumber = likeNumber;
        CommentNumber = commentNumber;
        Path = path;
        Tag1 = tag1;
        Tag2 = tag2;
        Tag3 = tag3;
        Tag4 = tag4;
        Tag5 = tag5;
    }

    public Blog() {
    }

    @Override
    public String toString() {
        return "Blog{" +
                "BlogID=" + BlogID +
                ", UserID=" + UserID +
                ", Title='" + Title + '\'' +
                ", DateTime=" + DateTime +
                ", Content='" + Content + '\'' +
                ", LikeNumber=" + LikeNumber +
                ", CommentNumber=" + CommentNumber +
                ", Path='" + Path + '\'' +
                ", Tag1='" + Tag1 + '\'' +
                ", Tag2='" + Tag2 + '\'' +
                ", Tag3='" + Tag3 + '\'' +
                ", Tag4='" + Tag4 + '\'' +
                ", Tag5='" + Tag5 + '\'' +
                '}';
    }
}
