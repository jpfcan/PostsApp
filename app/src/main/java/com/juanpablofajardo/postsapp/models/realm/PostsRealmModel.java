package com.juanpablofajardo.postsapp.models.realm;

import com.juanpablofajardo.postsapp.objects.Post;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class PostsRealmModel {

    @Inject
    public PostsRealmModel() {
    }

    public void insertPosts(List<Post> posts) {
        Realm.getDefaultInstance().executeTransaction(realm -> realm.insertOrUpdate(posts));
    }
}
