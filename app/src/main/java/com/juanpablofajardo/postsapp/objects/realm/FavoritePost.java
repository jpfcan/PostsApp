package com.juanpablofajardo.postsapp.objects.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Object that saves the id of a favorite post in Realm's instance.
 * This is so we can know which posts have been marked as favorites by the user, even though he closes the app.
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
