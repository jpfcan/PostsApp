package com.juanpablofajardo.postsapp.presenters;

import com.juanpablofajardo.postsapp.callbacks.PostsListener;
import com.juanpablofajardo.postsapp.models.posts.PostsModel;
import com.juanpablofajardo.postsapp.objects.Post;
import com.juanpablofajardo.postsapp.ui.adapters.posts.PostsAdapter;
import com.juanpablofajardo.postsapp.ui.view_interfaces.AllPostsView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class AllPostsPresenter implements BasePresenter<AllPostsView>, PostsListener {

    private AllPostsView view;
    private PostsAdapter adapter;

    @Inject
    protected PostsModel postsModel;

    @Inject
    public AllPostsPresenter() {
    }

    @Override
    public void setView(AllPostsView view) {
        this.view = view;
    }

    public void fetchAllPostsFromServer() {
        view.showLoading();
        postsModel.fetchPosts(this);
    }

    @Override
    public void onPostsFetchSuccess(@NotNull List<Post> posts) {
        adapter = new PostsAdapter(posts);
        view.setupRecyclerView(adapter);
        view.hideLoading();
    }

    @Override
    public void onError() {

    }
}
