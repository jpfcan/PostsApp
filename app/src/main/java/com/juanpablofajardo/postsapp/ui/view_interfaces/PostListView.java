package com.juanpablofajardo.postsapp.ui.view_interfaces;

import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.juanpablofajardo.postsapp.ui.adapters.pagers.SimplePagerAdapter;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public interface PostListView extends BaseView {

    void setAdapterToViewPager(FragmentPagerAdapter pagerAdapter);

    FragmentManager getChildFragmentManager();

    Resources getResources();

}
