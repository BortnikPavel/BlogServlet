package com.models.pojo;

/**
 * Created by admin on 12.03.2017.
 */
public class NewArticle {
    private int id;
    private String title;
    private String textArticle;
    private User user;
    private Topic topic;

    public NewArticle(String title, String textArticle, User user, Topic topic) {
        this.title = title;
        this.textArticle = textArticle;
        this.user = user;
        this.topic = topic;
    }

    public NewArticle() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextArticle() {
        return textArticle;
    }

    public void setTextArticle(String textArticle) {
        this.textArticle = textArticle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
