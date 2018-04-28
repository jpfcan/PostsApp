package com.juanpablofajardo.postsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public abstract class BaseFragment extends Fragment {

    protected View mContainerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContainerView = inflater.inflate(getFragmentLayoutResource(), container, false);
        ButterKnife.bind(this, mContainerView);
        return mContainerView;
    }

    @Override
    public void onDestroyView() {
        destroyView();
        super.onDestroyView();
    }

    /**
     * Use this method to remove the presenter when the view is destroyed
     */
    protected abstract void destroyView();

    /**
     * Use this method to set the layout for the given fragment
     *
     * @return the id for the layout
     */
    protected abstract
    @LayoutRes
    int getFragmentLayoutResource();

    protected void showToastMessage(String message) {
        if (message != null && mContainerView != null && mContainerView.getContext() != null)
            Toast.makeText(mContainerView.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected void openNextActivity(Intent intent) {
        if (getActivity() != null && !getActivity().isDestroyed()) {
            startActivity(intent);
        }
    }

    protected void openNextActivity(Intent intent, @AnimRes int enterAnim, @AnimRes int exitAnima) {
        if (getActivity() != null && !getActivity().isDestroyed()) {
            startActivity(intent);
            getActivity().overridePendingTransition(enterAnim, exitAnima);
        }
    }

}
