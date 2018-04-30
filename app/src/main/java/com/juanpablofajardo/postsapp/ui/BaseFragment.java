package com.juanpablofajardo.postsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.juanpablofajardo.postsapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @BindView(R.id.loading_view)
    protected View loadingView;

    protected View mContainerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

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
     * This method is to enforce dependency injection for all fragments
     */
    protected abstract void injectDependencies();

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
        if (message != null && isAdded())
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    protected void showCustomDialog(@LayoutRes int layoutResource) {
        if (isAdded()) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();

            View dialogView = inflater.inflate(layoutResource, null);
            dialogBuilder.setView(dialogView);

            AlertDialog dialog = dialogBuilder.create();
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            dialog.show();
        }
    }

}
