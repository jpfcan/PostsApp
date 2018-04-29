package com.juanpablofajardo.postsapp.ui.adapters.pagers;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class PagerFragmentEntry {

    private final Fragment fragment;
    private final
    @StringRes
    int title;

    public PagerFragmentEntry(Fragment fragment, @StringRes int title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public
    @StringRes
    int getTitle() {
        return title;
    }

    public String getId() {
        return fragment.getClass().getSimpleName();
    }

}
