package com.juanpablofajardo.postsapp.ui.adapters.posts;

import com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewType;

import static com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewTypeConstants.POST_VIEW_TYPE;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class PostViewType implements ViewType {

    private final int id;
    private final String title;
    private final String content;
    private boolean read;
    private boolean favorite;

    public PostViewType(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public int getViewType() {
        return POST_VIEW_TYPE;
    }

}
