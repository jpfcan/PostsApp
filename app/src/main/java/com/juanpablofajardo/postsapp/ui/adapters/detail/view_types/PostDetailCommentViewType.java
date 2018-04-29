package com.juanpablofajardo.postsapp.ui.adapters.detail.view_types;

import com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewType;

import static com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewTypeConstants.POST_DETAIL_COMMENT_VIEW_TYPE;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public class PostDetailCommentViewType implements ViewType {

    private String title;
    private String content;
    private String authorEmail;

    public PostDetailCommentViewType() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    @Override
    public int getViewType() {
        return POST_DETAIL_COMMENT_VIEW_TYPE;
    }

}
