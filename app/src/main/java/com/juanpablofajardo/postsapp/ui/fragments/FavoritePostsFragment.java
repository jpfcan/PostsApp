package com.juanpablofajardo.postsapp.ui.fragments;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.ui.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class FavoritePostsFragment extends BaseFragment {

    @Override
    protected void destroyView() {

    }

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_favorite_posts;
    }
}
