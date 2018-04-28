package com.juanpablofajardo.postsapp.ui.adapters.delegate.base;

import com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewDelegateAdapter;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewType;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 * <p>
 * All items to be used in {@link android.support.v7.widget.RecyclerView} with
 * {@link ViewDelegateAdapter}
 * must extend from this.
 */

public abstract class BaseDelegateItem implements ViewDelegateAdapter<BaseDelegateViewHolder, ViewType> {

    @Override
    public void onBindViewHolder(BaseDelegateViewHolder holder, ViewType item) {
        holder.onBindViewHolder(item);
    }

}
