package com.juanpablofajardo.postsapp.ui.adapters.pagers;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class SimplePagerAdapter extends FragmentPagerAdapter {

    private int numberOfPages;
    private List<PagerFragmentEntry> mPagerFragments;
    private Resources mResources;

    public SimplePagerAdapter(FragmentManager fm, @NonNull List<PagerFragmentEntry> pagerFragments, Resources resources) {
        super(fm);
        this.mPagerFragments = pagerFragments;
        this.numberOfPages = pagerFragments.size();
        this.mResources = resources;
    }

    @Override
    public Fragment getItem(int position) {
        return this.mPagerFragments.get(position).getFragment();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        PagerFragmentEntry fe = this.mPagerFragments.get(position);
        return mResources.getString(fe.getTitle());
    }

    @Override
    public int getCount() {
        return this.numberOfPages;
    }

}
