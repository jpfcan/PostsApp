package com.juanpablofajardo.postsapp.presenters.lists;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.models.realm.PostsRealmModel;
import com.juanpablofajardo.postsapp.navigators.PostListNavigator;
import com.juanpablofajardo.postsapp.objects.Post;
import com.juanpablofajardo.postsapp.objects.realm.FavoritePost;
import com.juanpablofajardo.postsapp.presenters.BasePresenter;
import com.juanpablofajardo.postsapp.ui.adapters.posts.PostsAdapter;
import com.juanpablofajardo.postsapp.ui.adapters.posts.PostsAdapter.PostsAdapterListener;
import com.juanpablofajardo.postsapp.ui.view_interfaces.FavoritePostsView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Presenter for the fragment that shows favorite posts, it manages all logic, from fetching/saving data from/to Realm to launching detail screen.
 */
public class FavoritePostsPresenter implements BasePresenter<FavoritePostsView>, PostsAdapterListener {

    private FavoritePostsView view;
    private PostsAdapter adapter;

    private PostsRealmModel postsRealmModel;

    private PostListNavigator navigator;

    private boolean shouldShowReload = false;


    @Inject
    public FavoritePostsPresenter(final PostsRealmModel postsRealmModel, final PostListNavigator navigator) {
        this.postsRealmModel = postsRealmModel;
        this.navigator = navigator;
    }

    @Override
    public void setView(final FavoritePostsView view) {
        this.view = view;
    }

    public void fetchAllFavoritesFromDB() {
        if (view != null) {
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
    }

    private void setupAdapter(final List<Post> favoritePosts) {
        if (view != null) {
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
    }

    public void removeItem(final int position) {
        adapter.removeItemOnSwipe(position);
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
        if (!added && view != null) {
            view.showFavoriteAlertDialog();
        }
    }

    @Override
    public void onRemoveItem(final int id) {
        postsRealmModel.removePost(id);
    }

    public boolean getShouldShowReload() {
        return shouldShowReload;
    }
}
