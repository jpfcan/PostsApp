package com.juanpablofajardo.postsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.juanpablofajardo.postsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    //@BindView(R.id.content_view)
    FrameLayout contentView;

    @Nullable
    //@BindView(R.id.loading_view)
    ProgressBar loadingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
    }

    protected void setToolbarWithCloseIcon() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white);
        }
    }

    protected void setToolbarWithBackArrow() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void executeFragment(@NonNull Fragment fragmentToExecute, boolean addToBackstack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //fragmentTransaction.replace(R.id.content_view, fragmentToExecute);
        if (addToBackstack) {
            fragmentTransaction.addToBackStack(fragmentToExecute.getTag());
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    protected abstract @LayoutRes int getLayoutResource();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void showLoading() {
        if (contentView != null) {
            contentView.setVisibility(View.GONE);
        }

        if (loadingView != null) {
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoading() {
        if (contentView != null) {
            contentView.setVisibility(View.VISIBLE);
        }

        if (loadingView != null) {
            loadingView.setVisibility(View.GONE);
        }
    }

}
