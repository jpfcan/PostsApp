package com.juanpablofajardo.postsapp.ui.activities;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.ui.BaseActivity;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class GeneralActivity extends BaseActivity {

    public static final int LISTS_FRAGMENT = 0x0001;

    @Retention(SOURCE)
    @IntDef({LISTS_FRAGMENT})
    public @interface FragmentCases {
    }

    public static final String SHOW_CLOSE_KEY = "showClose";
    public static final String FRAGMENT_TITLE = "title";
    public static final String FRAGMENT_CASE_KEY = "fragmentCase";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent() != null && getIntent().getExtras() != null) {
            if (getIntent().getExtras().containsKey(FRAGMENT_TITLE)) {
                setTitle(getIntent().getExtras().getInt(FRAGMENT_TITLE));
            }

            if (getIntent().getExtras().containsKey(FRAGMENT_CASE_KEY)) {
                @FragmentCases int fragmentCase = getIntent().getExtras().getInt(FRAGMENT_CASE_KEY);
                switch (fragmentCase) {
                    case LISTS_FRAGMENT:
                        //executeFragment(new StickerListFragment(), false);
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
