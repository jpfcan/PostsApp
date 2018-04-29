package com.juanpablofajardo.postsapp.objects.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class FavoritePost extends RealmObject {

    @PrimaryKey
    private int postId;

    public FavoritePost() {
    }

    public FavoritePost(int postId) {
        this.postId = postId;
    }

    public int getId() {
        return postId;
    }

    public void setId(int id) {
        this.postId = id;
    }
}
