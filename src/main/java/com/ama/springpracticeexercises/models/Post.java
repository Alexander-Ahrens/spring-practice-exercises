package com.ama.springpracticeexercises.models;

public class Post {
    private long id;
    private String title;
    private String body;

    public Post(long i, String title, String body) {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
