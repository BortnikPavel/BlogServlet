package com.models.pojo;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Павел on 25.02.2017.
 */
@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    public Topic(int id, String name, ArrayList<Article> articles) {
        this.id = id;
        this.name = name;
    }

    public Topic() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
