package com.tutorialproject.website.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, anounce, fullText;
    private int views;

    public Post() {
    }

    public Post(String title, String anounce, String fullText) {
        this.title = title;
        this.anounce = anounce;
        this.fullText = fullText;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAnounce() {
        return anounce;
    }

    public String getFullText() {
        return fullText;
    }

    public int getViews() {
        return views;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAnounce(String anounce) {
        this.anounce = anounce;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void incViews() {
        this.views++;
    }
}
