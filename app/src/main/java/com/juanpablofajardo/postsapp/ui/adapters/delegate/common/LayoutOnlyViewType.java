package com.juanpablofajardo.postsapp.ui.adapters.delegate.common;

import android.support.annotation.LayoutRes;

import com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewType;

import static com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewTypeConstants.LAYOUT_ONLY_VIEWTYPE;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public class LayoutOnlyViewType implements ViewType {

    private final @LayoutRes int layoutId;

    public LayoutOnlyViewType(int layoutId) {
        this.layoutId = layoutId;
    }

    public int getLayoutId() {
        return layoutId;
    }

    @Override
    public int getViewType() {
        return LAYOUT_ONLY_VIEWTYPE;
    }
}
