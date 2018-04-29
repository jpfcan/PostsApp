package com.juanpablofajardo.postsapp.ui.view_interfaces;

import android.support.annotation.StringRes;

import com.juanpablofajardo.postsapp.ui.adapters.posts.PostsAdapter;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public interface FavoritePostsView extends BaseView {

    void setupRecyclerView(PostsAdapter adapter);

    void showFavoriteAlertDialog();

    void refreshOptionsMenu();

}
