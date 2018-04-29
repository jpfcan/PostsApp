package com.juanpablofajardo.postsapp.ui.adapters.posts;

import android.support.v4.util.SparseArrayCompat;

import com.juanpablofajardo.postsapp.objects.Post;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.base.BaseDelegateAdapter;
import com.juanpablofajardo.postsapp.ui.adapters.posts.PostItemDelegate.PostItemListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewTypeConstants.POST_VIEW_TYPE;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class PostsAdapter extends BaseDelegateAdapter implements PostItemListener {

    private final List<Post> originalList;

    public PostsAdapter(List<Post> posts) {
        super(new SparseArrayCompat<>(), new ArrayList<>());

        delegates.put(POST_VIEW_TYPE, new PostItemDelegate(this));

        originalList = posts;
        items.addAll(getViewTypesFromPosts());
    }

    private List<PostViewType> getViewTypesFromPosts() {
         return originalList.stream().map(post -> {
             if (post != null) {
                 PostViewType postViewType = new PostViewType(post.getId(), post.getTitle(), post.getBody());
                 postViewType.setFavorite(false);
                 postViewType.setRead(false);

                 return postViewType;
             } else return null;
         }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public void onPostViewTypeClicked(PostViewType postViewType) {

    }

    @Override
    public void onFavoriteIndicatorClick(PostViewType postViewType) {

    }
}
