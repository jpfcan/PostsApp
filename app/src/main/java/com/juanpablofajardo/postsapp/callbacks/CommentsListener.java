package com.juanpablofajardo.postsapp.callbacks;

import com.juanpablofajardo.postsapp.objects.Comment;

import java.util.List;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public interface CommentsListener extends ServiceEvent {
    void onCommentsFetchSuccess(List<Comment> comments);
}
