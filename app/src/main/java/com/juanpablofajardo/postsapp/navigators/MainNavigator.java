package com.juanpablofajardo.postsapp.navigators;

import android.app.Activity;
import android.content.Intent;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.ui.activities.GeneralActivity;

import javax.inject.Inject;

import static com.juanpablofajardo.postsapp.ui.activities.GeneralActivity.FRAGMENT_CASE_KEY;
import static com.juanpablofajardo.postsapp.ui.activities.GeneralActivity.FRAGMENT_TITLE;
import static com.juanpablofajardo.postsapp.ui.activities.GeneralActivity.LISTS_FRAGMENT;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class MainNavigator {

    @Inject
    public MainNavigator() {
    }

    public void launchListsSection(final Activity originActivity) {
        final Intent intent = new Intent(originActivity, GeneralActivity.class);
        intent.putExtra(FRAGMENT_CASE_KEY, LISTS_FRAGMENT);
        intent.putExtra(FRAGMENT_TITLE, R.string.lists_section_title);

        originActivity.startActivity(intent);
        originActivity.finish();
    }
}
