package com.models.entity;

import javax.persistence.*;
import java.util.HashSet;

/**
 * Created by admin on 20.03.2017.
 */
@Entity
@Table(name = "topics", schema = "blog")
public class TopicsEntity {
    private int id;
    private String name;
    private int version;
    private HashSet<ArticlesEntity> articlesEntities;
    private HashSet<NewArticlesEntity> newArticlesEntities;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "topic")
    public HashSet<ArticlesEntity> getArticlesEntities() {
        return articlesEntities;
    }

    public void setArticlesEntities(HashSet<ArticlesEntity> articlesEntities) {
        this.articlesEntities = articlesEntities;
    }

    @OneToMany(mappedBy = "topic")
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

        TopicsEntity that = (TopicsEntity) o;

        if (id != that.id) return false;
        if (version != that.version) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + version;
        return result;
    }
}
