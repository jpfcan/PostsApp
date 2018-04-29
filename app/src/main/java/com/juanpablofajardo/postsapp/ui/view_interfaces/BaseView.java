package com.juanpablofajardo.postsapp.ui.view_interfaces;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Juan Pablo Fajardo Cano on 9/30/17.
 */

@SuppressWarnings("DefaultFileTemplate")
public interface BaseView {

    FragmentActivity getFragmentActivity();

    void showMessageToast(@NonNull String message);

    void showLoading();

    void hideLoading();

}
