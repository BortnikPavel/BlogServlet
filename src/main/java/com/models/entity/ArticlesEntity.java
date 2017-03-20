package com.models.entity;

import com.models.pojo.Topic;
import com.models.pojo.User;

import javax.persistence.*;

/**
 * Created by admin on 20.03.2017.
 */
@Entity
@Table(name = "articles", schema = "blog")
public class ArticlesEntity {
    private int id;
    private String title;
    private String text;
    private String publicationdate;
    private UsersEntity user;
    private TopicsEntity topic;
    private int version;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 200)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "text", nullable = false, length = -1)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "publicationdate", nullable = false, length = 45)
    public String getPublicationdate() {
        return publicationdate;
    }

    public void setPublicationdate(String publicationdate) {
        this.publicationdate = publicationdate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topicId")
    public TopicsEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicsEntity topic) {
        this.topic = topic;
    }

    @Version
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

        ArticlesEntity that = (ArticlesEntity) o;

        if (id != that.id) return false;
        if (version != that.version) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;
        if (publicationdate != null ? !publicationdate.equals(that.publicationdate) : that.publicationdate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (publicationdate != null ? publicationdate.hashCode() : 0);
        result = 31 * result + version;
        return result;
    }
}
