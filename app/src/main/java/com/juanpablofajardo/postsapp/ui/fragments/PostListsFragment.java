package com.juanpablofajardo.postsapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.app.AppManager;
import com.juanpablofajardo.postsapp.presenters.PostListsPresenter;
import com.juanpablofajardo.postsapp.ui.BaseFragment;
import com.juanpablofajardo.postsapp.ui.view_interfaces.PostListView;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class PostListsFragment extends BaseFragment implements PostListView {

    @BindView(R.id.post_lists_view_pager)
    protected ViewPager listsViewPager;

    @BindView(R.id.post_lists_tab_layout)
    protected TabLayout listsTabLayout;

    @Inject
    protected PostListsPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.DAGGER_COMPONENT.inject(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.setView(this);
        presenter.setupViewPager();
        presenter.setupTabSelectedListener();
    }

    @Override
    public void setAdapterToViewPager(FragmentPagerAdapter pagerAdapter) {
        if (isAdded()) {
            listsViewPager.setAdapter(pagerAdapter);
        }
    }

    @Override
    public void setTabSelectedListener(OnTabSelectedListener tabSelectedListener) {
        if (isAdded()) {
            listsTabLayout.addOnTabSelectedListener(tabSelectedListener);
        }
    }

    @Override
    public void showMessageToast(@NonNull String message) {
        if (isAdded()) {
            showToastMessage(message);
        }
    }

    @Override
    public void showLoading() {
        //NO-OP
    }

    @Override
    public void hideLoading() {
        //NO-OP
    }

    @Override
    protected void destroyView() {
        presenter.setView(null);
    }

    @Override
    public FragmentActivity getFragmentActivity() {
        return getActivity();
    }

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_posts_lists;
    }

}
