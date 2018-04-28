package com.juanpablofajardo.postsapp.ui.adapters.delegate.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewType;

import butterknife.ButterKnife;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 * <p>
 * All ViewHolders must extend from this.
 */

public abstract class BaseDelegateViewHolder extends RecyclerView.ViewHolder {

    public BaseDelegateViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    /**
     * Use this method to set all the information from the given {@link ViewType} into the ViewHolder
     *
     * @param viewType
     */
    protected abstract void onBindViewHolder(ViewType viewType);
}
