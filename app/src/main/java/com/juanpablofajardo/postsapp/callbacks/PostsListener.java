package com.juanpablofajardo.postsapp.callbacks;

import com.juanpablofajardo.postsapp.objects.Post;

import java.util.List;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public interface PostsListener extends ServiceEvent {
    void onPostsFetchSuccess(List<Post> posts);
}
