package com.models.pojo;

/**
 * Created by Павел on 25.02.2017.
 */
public class Article {
    private int id;
    private String title;
    private String textArticle;
    private String datePublication;
    private User user;
    private Topic topic;

    public Article(int id, String title, String textArticle, String datePublication, User user, Topic topic) {
        this.id = id;
        this.title = title;
        this.textArticle = textArticle;
        this.datePublication = datePublication;
        this.user = user;
        this.topic = topic;
    }

    public Article() {
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

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
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
