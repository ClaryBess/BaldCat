package com.baldcat.Handler;

import com.baldcat.entity.*;
import com.baldcat.repository.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Handler {

    private static UserRepository userRepository;
    private static BlogRepository blogRepository;
    private static BlogCommentRepository blogCommentRepository;
    private static LikeRepository likeRepository;
    private static ResourceRepository resourceRepository;
    private static FollowRepository followRepository;

    static {
        userRepository = new UserRepository();
        blogRepository = new BlogRepository();
        blogCommentRepository = new BlogCommentRepository();
        likeRepository = new LikeRepository();
        resourceRepository = new ResourceRepository();
        followRepository = new FollowRepository();
    }

    /**
     * 处理注册功能
     * @param Name
     * @param Password
     * @param Email
     * @param Gender
     * @param Birthday
     * @param Introduction
     * @return
     */
    public int register(String Name, String Password, String Email, String Gender, java.sql.Date Birthday, String Introduction) {
        if(Name == null || Password == null || Email == null)
            return 1;
        else if(userRepository.findByName(Name) != null)
            return 2;
        else if(userRepository.findByEmail(Email) != null)
            return 3;
        else{
            userRepository.add(Name, Password, Email, Gender, Birthday, Introduction);
            return 0;
        }
    }

    /**
     * 登录功能
     * @param Name
     * @param Password
     * @return
     */
    public User login(String Name,String Password) {
        return userRepository.findAsLogin(Name,Password);
    }

    /**
     * 删除用户功能
     * @param id
     */
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    /**
     * 添加博客功能
     * @param userID
     * @param title
     * @param content
     * @param Path
     * @param tag1
     * @param tag2
     * @param tag3
     * @param tag4
     * @param tag5
     * @return
     */
    public Blog addBlog(int userID, String title, String content, String Path, String tag1, String tag2, String tag3, String tag4, String tag5){
        Blog blog=blogRepository.add(userID,title,content,Path,tag1,tag2,tag3,tag4,tag5);
        resourceRepository.add(userID,Path);
        return blog;
    }

    /**
     * 添加博客评论功能
     * @param blogID
     * @param userID
     * @param content
     */
    public void commentBlog(int blogID,int userID,String content){
        blogRepository.addComment(blogID);
        blogCommentRepository.add(blogID,userID,content);
    }

    /**
     * 点赞博客功能
     * @param blogID
     * @param userID
     */
    public void likeBlog(int blogID,int userID){
        blogRepository.addLike(blogID);
        likeRepository.add(blogID,userID);
    }

    /**
     * 删除博客功能
     * @param blogID
     */
    public void deleteBlog(Integer blogID){
        blogRepository.deleteById(blogID);
    }

    /**
     * 删除资源功能
     * @param blogID
     */
    public void deleteResource(Integer blogID){
        blogRepository.updateResource(blogID);
    }

    /**
     * 关注用户功能
     * @param FollowUserID
     * @param FollowedUserID
     */
    public void follow(Integer FollowUserID, Integer FollowedUserID){
        followRepository.add(FollowUserID,FollowedUserID);
        userRepository.updateFans(FollowedUserID);
    }

    /**
     * 修改密码功能
     * @param UserID
     * @param old
     * @param new1
     * @param new2
     * @return
     */
    public int chpwd(Integer UserID,String old,String new1,String new2) {
        User user = userRepository.findById(UserID);
        if(user == null)
            return -1;
        if(old.compareTo(user.getPassword()) == 0)
            return 2;
        else if(new1.compareTo(new2) != 0)
            return 1;
        else{
            userRepository.updatePassword(UserID,new1);
            return 0;
        }
    }

    /**
     * 修改个人简介功能
     * @param UserID
     * @param Introduction
     * @return
     */
    public int chintro(Integer UserID,String Introduction) {
        User user = userRepository.findById(UserID);
        if(user == null)
            return -1;
        else{
            userRepository.updateIntroduction(UserID,Introduction);
            return 0;
        }
    }

    /**
     * 修改生日功能
     * @param UserID
     * @param Birthday
     * @return
     */
    public int chBirthday(Integer UserID, Date Birthday) {
        User user = userRepository.findById(UserID);
        if(user == null)
            return -1;
        else{
            userRepository.updateBirthday(UserID,Birthday);
            return 0;
        }
    }

    /**
     * 修改性别功能
     * @param UserID
     * @param Gender
     * @return
     */
    //Gender传入参数为"F"或"M"
    public int chGender(Integer UserID,String Gender) {
        User user = userRepository.findById(UserID);
        if(user == null)
            return -1;
        else{
            userRepository.updateGender(UserID,Gender);
            return 0;
        }
    }

    /**
     * 通过UserID搜索用户
     * @param UserID
     * @return
     */
    public User findByUserId(String UserID){
        if(UserID==null)
            return null;
        else
            return userRepository.findById(Integer.parseInt(UserID));
    }

    /**
     * 通过BlogID搜索博客功能
     * @param BlogID
     * @return
     */
    public Blog findById(String BlogID){
        if(BlogID==null)
            return null;
        else
            return blogRepository.findById(BlogID);
    }

    /**
     * 通过标签搜索博客功能
     * @param tag
     * @return
     */
    public List<Blog> searchByTag(String tag){
        if(tag == null)
            return null;
        else
            return blogRepository.findByTag(tag);
    }

    /**
     * 通过全文匹配搜索博客功能
     * @param subContent
     * @return
     */
    public List<Blog> searchByMatch(String subContent){
        if(subContent == null)
            return null;
        else
            return blogRepository.findByContent(subContent);
    }

    /**
     * 博客按点赞排序
     * @return
     */
    public List<Blog> blogOrderByLike(){
        return blogRepository.findOrderByLike();
    }

    /**
     * 随机推荐博客
     * @return
     */
    public Blog recommendBlog(){
        return blogRepository.recommend();
    }

    /**
     * 查看Blog的评论
     * @param BlogID
     * @return
     */
    public List<BlogComment> commentsOfBlog(String BlogID){
        return blogCommentRepository.findByBlog(BlogID);
    }

    /**
     * 查看User的博客
     * @param UserID
     * @return
     */
    public List<Blog> blogsOfUser(String UserID){
        return blogRepository.findByUser(UserID);
    }

    /**
     * 查看粉丝
     * @param FollowedUserID
     * @return
     */
    public List<User> myFans(Integer FollowedUserID){
        List<Follow> follows = followRepository.findFans(FollowedUserID);
        List<User> users = new ArrayList<>();
        User user = null;
        for(int i=0;i<follows.size();i++){
            user = userRepository.findById(follows.get(i).getFollowUserID());
            if(user != null)
                users.add(user);
        }
        return users;
    }

    /**
     * 查看关注
     * @param FollowUserID
     * @return
     */
    public List<User> myFollow(Integer FollowUserID){
        List<Follow> follows = followRepository.findFollow(FollowUserID);
        List<User> users = new ArrayList<>();
        User user = null;
        for(int i=0;i<follows.size();i++){
            user = userRepository.findById(follows.get(i).getFollowedUserID());
            if(user != null)
                users.add(user);
        }
        return users;
    }

    /**
     * 是否已经关注
     * @param FollowUserID
     * @param FollowedUserID
     * @return
     */
    public boolean hasFollow(int FollowUserID, int FollowedUserID){
        if(followRepository.findByUU(FollowUserID,FollowedUserID)!=null)
            return true;
        else
            return false;
    }

    /**
     * 是否已经点赞
     * @param BlogID
     * @param UserID
     * @return
     */
    public boolean hasLike(int BlogID,int UserID){
        if(likeRepository.findLike(BlogID,UserID)!=null)
            return true;
        else
            return false;
    }
}
