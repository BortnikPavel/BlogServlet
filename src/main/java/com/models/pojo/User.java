package com.models.pojo;

import java.util.ArrayList;

/**
 * Created by Павел on 25.02.2017.
 */

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String nickName;
    private String password;
    private String role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private ArrayList<Article> articles;
    private ArrayList<Comment> comments;

    public User(int id, String firstName,
                String lastName, String email,
                String nickName, String password,
                ArrayList<Article> articles,
                ArrayList<Comment> comments) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.articles = articles;
        this.comments = comments;
    }

    public User(String firstName, String lastName, String email, String nickName, String password) {
        id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
