package com.juanpablofajardo.postsapp.ui.view_interfaces;

import android.content.res.Resources;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public interface PostListView extends BaseView {

    void setAdapterToViewPager(FragmentPagerAdapter pagerAdapter);

    void setTabSelectedListener(OnTabSelectedListener tabSelectedListener);

    FragmentManager getChildFragmentManager();

    Resources getResources();

}
