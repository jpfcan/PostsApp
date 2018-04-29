package com.juanpablofajardo.postsapp.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public class Comment {

    private int id;
    private int postId;

    @SerializedName(value = "name")
    private String title;

    private String body;
    private String email;

    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getEmail() {
        return email;
    }
}
