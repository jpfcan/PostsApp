package com.juanpablofajardo.postsapp.ui.adapters.detail.view_types;

import com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewType;

import static com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewTypeConstants.POST_DETAIL_USER_INFO_VIEW_TYPE;


/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public class PostDetailUserInfoViewType implements ViewType {

    private String userUsername;
    private String userName;
    private String userEmail;
    private String userPhone;
    private String userWebsite;

    public PostDetailUserInfoViewType() {
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserWebsite() {
        return userWebsite;
    }

    public void setUserWebsite(String userWebsite) {
        this.userWebsite = userWebsite;
    }

    @Override
    public int getViewType() {
        return POST_DETAIL_USER_INFO_VIEW_TYPE;
    }

}
