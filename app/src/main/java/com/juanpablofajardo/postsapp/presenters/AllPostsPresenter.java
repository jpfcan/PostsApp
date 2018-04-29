package com.juanpablofajardo.postsapp.presenters;

import com.juanpablofajardo.postsapp.callbacks.PostsListener;
import com.juanpablofajardo.postsapp.models.posts.PostsModel;
import com.juanpablofajardo.postsapp.models.realm.PostsRealmModel;
import com.juanpablofajardo.postsapp.objects.Post;
import com.juanpablofajardo.postsapp.objects.realm.FavoritePost;
import com.juanpablofajardo.postsapp.ui.adapters.posts.PostsAdapter;
import com.juanpablofajardo.postsapp.ui.adapters.posts.PostsAdapter.PostsAdapterListener;
import com.juanpablofajardo.postsapp.ui.view_interfaces.AllPostsView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class AllPostsPresenter implements BasePresenter<AllPostsView>, PostsListener, PostsAdapterListener {

    private AllPostsView view;
    private PostsAdapter adapter;

    private PostsModel postsModel;
    private PostsRealmModel postsRealmModel;

    private boolean shouldShowReload = false;

    @Inject
    public AllPostsPresenter(final PostsModel postsModel, final PostsRealmModel postsRealmModel) {
        this.postsModel = postsModel;
        this.postsRealmModel = postsRealmModel;
    }

    @Override
    public void setView(AllPostsView view) {
        this.view = view;
    }

    public void fetchAllPostsFromDB() {
        view.showLoading();
        try {
            List<Post> postsFromRealm = postsRealmModel.getPosts();

            if (postsFromRealm.isEmpty()) {
                fetchAllPostFromService();
            } else {
                setupAdapter(postsFromRealm);
            }
        } catch (Exception e) {
            fetchAllPostFromService();
        }
    }

    public void fetchAllPostFromService() {
        shouldShowReload = false;
        view.refreshOptionsMenu();
        view.showLoading();
        postsModel.fetchPosts(this);
    }

    private void setupAdapter(List<Post> posts) {
        adapter = new PostsAdapter(posts, this, postsRealmModel);
        view.setupRecyclerView(adapter);
        shouldShowReload = true;
        view.refreshOptionsMenu();
        view.hideLoading();
    }

    public void removeItem(int position) {
        adapter.removeItemOnSwipe(position);
    }

    @Override
    public void onPostsFetchSuccess(@NotNull List<Post> posts) {
        postsRealmModel.insertPosts(posts);
        setupAdapter(posts);
    }

    @Override
    public void onError() {
        //TODO show error screen
    }

    @Override
    public void onPostClicked(Post clickedPost) {
        //TODO implement
    }

    @Override
    public void onPostFavoriteIndicatorAction(int favoriteActionPostId, boolean added) {
        postsRealmModel.updateFavoritePost(new FavoritePost(favoriteActionPostId), added);
    }

    @Override
    public void onRemoveItem(int id) {
        postsRealmModel.removePost(id);
    }

    public boolean getShouldShowReload() {
        return shouldShowReload;
    }

}
