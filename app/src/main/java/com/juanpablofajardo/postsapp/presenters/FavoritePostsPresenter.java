package com.juanpablofajardo.postsapp.presenters;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.models.realm.PostsRealmModel;
import com.juanpablofajardo.postsapp.objects.Post;
import com.juanpablofajardo.postsapp.objects.realm.FavoritePost;
import com.juanpablofajardo.postsapp.ui.adapters.posts.PostsAdapter;
import com.juanpablofajardo.postsapp.ui.adapters.posts.PostsAdapter.PostsAdapterListener;
import com.juanpablofajardo.postsapp.ui.view_interfaces.FavoritePostsView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class FavoritePostsPresenter implements BasePresenter<FavoritePostsView>, PostsAdapterListener {

    private FavoritePostsView view;
    private PostsAdapter adapter;

    private PostsRealmModel postsRealmModel;

    private boolean shouldShowReload = false;


    @Inject
    public FavoritePostsPresenter(final PostsRealmModel postsRealmModel) {
        this.postsRealmModel = postsRealmModel;
    }

    @Override
    public void setView(FavoritePostsView view) {
        this.view = view;
    }

    public void fetchAllFavoritesFromDB() {
        shouldShowReload = false;
        view.refreshOptionsMenu();
        view.showLoading();
        try {
            setupAdapter(postsRealmModel.getFavoritePosts());
        } catch (Exception e) {
            //TODO show error screen
            view.hideLoading();
        }

    }

    private void setupAdapter(List<Post> favoritePosts) {
        if (favoritePosts.isEmpty()) {
            //TODO empty state
            view.showMessageToast("There are no favorites");
        } else {
            adapter = new PostsAdapter(favoritePosts, this, postsRealmModel);
            view.setupRecyclerView(adapter);
        }
        shouldShowReload = true;
        view.refreshOptionsMenu();
        view.hideLoading();
    }

    public void removeItem(int position) {
        adapter.removeItemOnSwipe(position);
    }

    @Override
    public void onPostClicked(Post clickedPost) {
        //TODO implement
    }

    @Override
    public void onPostFavoriteIndicatorAction(int favoriteActionPostId, boolean added) {
        postsRealmModel.updateFavoritePost(new FavoritePost(favoriteActionPostId), added);
        if (!added) {
            view.showFavoriteAlertDialog();
        }
    }

    @Override
    public void onRemoveItem(int id) {
        postsRealmModel.removePost(id);
    }

    public boolean getShouldShowReload() {
        return shouldShowReload;
    }
}
