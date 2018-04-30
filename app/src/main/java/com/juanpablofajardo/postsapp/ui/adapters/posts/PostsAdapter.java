package com.juanpablofajardo.postsapp.ui.adapters.posts;

import android.support.v4.util.SparseArrayCompat;

import com.juanpablofajardo.postsapp.models.realm.PostsRealmModel;
import com.juanpablofajardo.postsapp.objects.Post;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.base.BaseDelegateAdapter;
import com.juanpablofajardo.postsapp.ui.adapters.posts.PostItemDelegate.PostItemListener;

import java.util.ArrayList;
import java.util.List;


import static com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewTypeConstants.POST_VIEW_TYPE;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */

public class PostsAdapter extends BaseDelegateAdapter implements PostItemListener {

    public interface PostsAdapterListener {
        void onPostClicked(Post clickedPost);
        void onPostFavoriteIndicatorAction(int favoriteActionPostId, boolean added);
        void onRemoveItem(int id);
    }

    private final List<Post> originalList;

    private final PostsAdapterListener listener;
    private final PostsRealmModel postsRealmModel;


    public PostsAdapter(List<Post> posts, PostsAdapterListener listener, PostsRealmModel postsRealmModel) {
        super(new SparseArrayCompat<>(), new ArrayList<>());

        delegates.put(POST_VIEW_TYPE, new PostItemDelegate(this));

        this.originalList = posts;
        this.listener = listener;
        this.postsRealmModel = postsRealmModel;

        items.addAll(getViewTypesFromPosts());
    }

    private List<PostViewType> getViewTypesFromPosts() {
        List<PostViewType> viewTypes = new ArrayList<>();

        for (Post post : originalList) {
             if (post != null) {
                 PostViewType postViewType =  new PostViewType(post.getId(), post.getTitle(), post.getBody());
                 postViewType.setRead(postsRealmModel.checkIfRead(post.getId()));
                 postViewType.setFavorite(postsRealmModel.checkIfFavorite(post.getId()));

                 viewTypes.add(postViewType);
             }
        }

        return viewTypes;
    }

    private Post getClickedPostById(int id) {
        for (Post post : originalList) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public void removeItemOnSwipe(int position) {
        PostViewType viewTypeToRemove = (PostViewType) getItem(position);
        removeViewType(viewTypeToRemove);
        listener.onRemoveItem(viewTypeToRemove.getId());
    }

    @Override
    public void onPostViewTypeClicked(PostViewType postViewType) {
        Post clickedPost = getClickedPostById(postViewType.getId());
        if (clickedPost != null) {
            listener.onPostClicked(clickedPost);
        }
    }

    @Override
    public void onFavoriteIndicatorClick(PostViewType postViewType) {
        boolean newFavoriteState = !postViewType.isFavorite();
        postViewType.setFavorite(newFavoriteState);
        notifyViewTypeChanged(postViewType);
        listener.onPostFavoriteIndicatorAction(postViewType.getId(), newFavoriteState);
    }
}
