package com.models.entity;

import javax.persistence.*;
import java.util.HashSet;

/**
 * Created by admin on 20.03.2017.
 */
@Entity
@Table(name = "users", schema = "blog")
public class UsersEntity {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String nickname;
    private String password;
    private String role;
    private HashSet<ArticlesEntity> articlesEntities;
    private HashSet<CommentsEntity> commentsEntities;
    private HashSet<NewArticlesEntity> newArticlesEntities;
    private int version;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstname", nullable = false, length = 45)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = false, length = 45)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "nickname", nullable = false, length = 45)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public HashSet<ArticlesEntity> getArticlesEntities() {
        return articlesEntities;
    }

    public void setArticlesEntities(HashSet<ArticlesEntity> articlesEntities) {
        this.articlesEntities = articlesEntities;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public HashSet<CommentsEntity> getCommentsEntities() {
        return commentsEntities;
    }

    public void setCommentsEntities(HashSet<CommentsEntity> commentsEntities) {
        this.commentsEntities = commentsEntities;
    }

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    public HashSet<NewArticlesEntity> getNewArticlesEntities() {
        return newArticlesEntities;
    }

    public void setNewArticlesEntities(HashSet<NewArticlesEntity> newArticlesEntities) {
        this.newArticlesEntities = newArticlesEntities;
    }

    @Basic
    @Column(name = "version", nullable = false)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (version != that.version) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + version;
        return result;
    }
}
