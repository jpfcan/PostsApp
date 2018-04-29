package com.juanpablofajardo.postsapp.presenters;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.ui.adapters.pagers.PagerFragmentEntry;
import com.juanpablofajardo.postsapp.ui.adapters.pagers.SimplePagerAdapter;
import com.juanpablofajardo.postsapp.ui.fragments.AllPostsFragment;
import com.juanpablofajardo.postsapp.ui.fragments.FavoritePostsFragment;
import com.juanpablofajardo.postsapp.ui.view_interfaces.PostListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class PostListsPresenter implements BasePresenter<PostListView> {

    private PostListView view;

    private SimplePagerAdapter adapter;

    @Inject
    public PostListsPresenter() {
    }

    @Override
    public void setView(PostListView view) {
        this.view = view;
    }

    public void setupViewPager() {
        List<PagerFragmentEntry> fragments = new ArrayList<>();
        fragments.add(new PagerFragmentEntry(new AllPostsFragment(), R.string.all_posts_fragment_title));
        fragments.add(new PagerFragmentEntry(new FavoritePostsFragment(), R.string.favorite_posts_fragment_title));

        adapter = new SimplePagerAdapter(view.getChildFragmentManager(), fragments, view.getResources());
        view.setAdapterToViewPager(adapter);

    }
}
