package com.juanpablofajardo.postsapp.utils;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * interface to be implemented in pager fragments tha need an action when they're resumed, but didn't go through the onPause/onResume default method
 */
public interface PagerFragmentLifeCycle {

    void onResumeFragment();

}
