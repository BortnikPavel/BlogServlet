package com.models.pojo;

/**
 * Created by Павел on 25.02.2017.
 */
public class Comment {
    private int id;
    private String text;
    private String date;
    private User user;
    private Article article;

    public Comment(int id, String text, String date, User user, Article article) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.user = user;
        this.article = article;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
