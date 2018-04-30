package com.juanpablofajardo.postsapp.ui.activities;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.ui.BaseActivity;
import com.juanpablofajardo.postsapp.ui.fragments.PostDetailFragment;
import com.juanpablofajardo.postsapp.ui.fragments.PostListsFragment;

import java.lang.annotation.Retention;

import static com.juanpablofajardo.postsapp.ui.fragments.PostDetailFragment.POST_KEY;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class GeneralActivity extends BaseActivity {

    public static final int LISTS_FRAGMENT = 0x0001;
    public static final int DETAIL_FRAGMENT = 0x0002;

    @Retention(SOURCE)
    @IntDef({LISTS_FRAGMENT, DETAIL_FRAGMENT})
    public @interface FragmentCases {
    }

    public static final String FRAGMENT_TITLE = "title";
    public static final String FRAGMENT_CASE_KEY = "fragmentCase";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();

            if (extras.containsKey(FRAGMENT_TITLE)) {
                setTitle(extras.getInt(FRAGMENT_TITLE));
            }

            if (extras.containsKey(FRAGMENT_CASE_KEY)) {
                @FragmentCases int fragmentCase = extras.getInt(FRAGMENT_CASE_KEY);
                switch (fragmentCase) {
                    case LISTS_FRAGMENT:
                        removeToolbarElevation();
                        executeFragment(new PostListsFragment(), false);
                        break;
                    case DETAIL_FRAGMENT:
                        if (extras.containsKey(POST_KEY)) {
                            setToolbarWithBackArrow();
                            executeFragment(PostDetailFragment.newInstance(extras.getParcelable(POST_KEY)), false);
                        }
                        break;
                }
            }
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_general;
    }

}
