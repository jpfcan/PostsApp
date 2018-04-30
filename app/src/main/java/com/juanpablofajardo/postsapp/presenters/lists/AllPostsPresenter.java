package com.juanpablofajardo.postsapp.presenters.lists;

import com.juanpablofajardo.postsapp.callbacks.PostsListener;
import com.juanpablofajardo.postsapp.callbacks.ServiceEvent;
import com.juanpablofajardo.postsapp.models.posts.PostsModel;
import com.juanpablofajardo.postsapp.models.realm.PostsRealmModel;
import com.juanpablofajardo.postsapp.navigators.PostListNavigator;
import com.juanpablofajardo.postsapp.objects.Post;
import com.juanpablofajardo.postsapp.objects.realm.FavoritePost;
import com.juanpablofajardo.postsapp.presenters.BasePresenter;
import com.juanpablofajardo.postsapp.ui.adapters.posts.PostsAdapter;
import com.juanpablofajardo.postsapp.ui.adapters.posts.PostsAdapter.PostsAdapterListener;
import com.juanpablofajardo.postsapp.ui.view_interfaces.AllPostsView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Presenter for the fragment that shows all posts, it manages all logic, from fetching/saving data from/to service/Realm to launching detail screen.
 */
public class AllPostsPresenter implements BasePresenter<AllPostsView>, PostsAdapterListener, ServiceEvent, PostsListener {

    private AllPostsView view;

    private PostsAdapter adapter;

    private PostsModel postsModel;
    private PostsRealmModel postsRealmModel;

    private PostListNavigator navigator;

    private boolean shouldShowReload = false;

    @Inject
    public AllPostsPresenter(final PostsModel postsModel, final PostsRealmModel postsRealmModel, final PostListNavigator navigator) {
        this.postsModel = postsModel;
        this.postsRealmModel = postsRealmModel;
        this.navigator = navigator;
    }

    @Override
    public void setView(final AllPostsView view) {
        this.view = view;
    }

    public void fetchAllPostsFromDB() {
        if (view != null) {
            view.setEmptyStateVisibility(false);
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
    }

    public void fetchAllPostFromService() {
        if (view != null) {
            shouldShowReload = false;
            view.refreshOptionsMenu();
            view.setEmptyStateVisibility(false);
            view.setDeleteAllVisibility(false);
            view.showLoading();
            postsModel.fetchPosts(this);
        }
    }

    private void setupAdapter(final List<Post> posts) {
        if (view != null) {
            if (posts.isEmpty()) {
                view.setEmptyStateVisibility(true);
            }

            adapter = new PostsAdapter(posts, this, postsRealmModel);
            view.setupRecyclerView(adapter);
            view.setDeleteAllVisibility(!posts.isEmpty());
            shouldShowReload = true;
            view.refreshOptionsMenu();
            view.hideLoading();
        }
    }

    public void updatePostStates() {
        if (view != null) {
            view.showLoading();
            List<Post> postsFromRealm = postsRealmModel.getPosts();
            setupAdapter(postsFromRealm);
        }
    }

    public void removeItem(final int position) {
        adapter.removeItemOnSwipe(position);
        if (adapter.getItems().isEmpty() && view != null) {
            view.setEmptyStateVisibility(true);
        }
    }

    public void executeDeleteAll() {
        if (view != null) {
            view.showLoading();
            view.setEmptyStateVisibility(true);
            postsRealmModel.deleteAllPosts();
            adapter.clearItems();
        }
    }

    @Override
    public void onPostsFetchSuccess(@NotNull final List<Post> posts) {
        postsRealmModel.insertPosts(posts);
        setupAdapter(posts);
    }

    @Override
    public void onError() {
        if (view != null) {
            view.setDeleteAllVisibility(!adapter.getItems().isEmpty());
            view.showErrorDialog();
            view.hideLoading();
        }
    }

    @Override
    public void onPostClicked(final Post clickedPost) {
        if (view != null) {
            navigator.goToPostDetail(view.getFragmentActivity(), clickedPost);
        }
    }

    @Override
    public void onPostFavoriteIndicatorAction(final int favoriteActionPostId, final boolean added) {
        postsRealmModel.updateFavoritePost(new FavoritePost(favoriteActionPostId), added);
    }

    @Override
    public void onRemoveItem(final int id) {
        postsRealmModel.removePost(id);
    }

    public boolean getShouldShowReload() {
        return shouldShowReload;
    }

}
