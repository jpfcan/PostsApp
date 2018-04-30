package com.juanpablofajardo.postsapp.ui.view_interfaces;

import com.juanpablofajardo.postsapp.ui.adapters.posts.PostsAdapter;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public interface BasePostListView extends BaseView {

    void setupRecyclerView(PostsAdapter adapter);

    void setEmptyStateVisibility(boolean visible);

    void refreshOptionsMenu();

}
