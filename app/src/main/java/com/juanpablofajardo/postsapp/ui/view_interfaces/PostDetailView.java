package com.juanpablofajardo.postsapp.ui.view_interfaces;

import android.content.res.Resources;

import com.juanpablofajardo.postsapp.ui.adapters.detail.PostDetailAdapter;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public interface PostDetailView extends BaseView {

    void setDetailAdapter(final PostDetailAdapter detailAdapter);

    void refreshOptionsMenu();

    Resources getResources();
}
