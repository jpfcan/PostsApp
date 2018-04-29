package com.juanpablofajardo.postsapp.navigators;

import android.app.Activity;
import android.content.Intent;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.objects.Post;
import com.juanpablofajardo.postsapp.ui.activities.GeneralActivity;

import javax.inject.Inject;

import static com.juanpablofajardo.postsapp.ui.activities.GeneralActivity.DETAIL_FRAGMENT;
import static com.juanpablofajardo.postsapp.ui.activities.GeneralActivity.FRAGMENT_CASE_KEY;
import static com.juanpablofajardo.postsapp.ui.activities.GeneralActivity.FRAGMENT_TITLE;
import static com.juanpablofajardo.postsapp.ui.fragments.PostDetailFragment.POST_KEY;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 *
 * Navigator to be used in post list fragments.
 * It'll be used to launch the post screen detail when the user clicks an item from the post list.
 */
public class PostListNavigator {

    @Inject
    public PostListNavigator() {
    }

    public void goToPostDetail(final Activity originActivity, final Post clickedPost) {
        Intent intent =  new Intent(originActivity, GeneralActivity.class);
        intent.putExtra(FRAGMENT_TITLE, R.string.post_detail_title);
        intent.putExtra(FRAGMENT_CASE_KEY, DETAIL_FRAGMENT);
        intent.putExtra(POST_KEY, clickedPost);

        originActivity.startActivity(intent);
    }

}
