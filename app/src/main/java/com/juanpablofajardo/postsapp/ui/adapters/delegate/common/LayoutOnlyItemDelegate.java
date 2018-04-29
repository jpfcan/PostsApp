package com.juanpablofajardo.postsapp.ui.adapters.delegate.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewType;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.base.BaseDelegateItem;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.base.BaseDelegateViewHolder;

import butterknife.BindView;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public class LayoutOnlyItemDelegate extends BaseDelegateItem {

    @Override
    public BaseDelegateViewHolder onCreateViewHolder(ViewGroup parent) {
        return new LayoutOnlyItemViewHolder(parent);
    }

    public class LayoutOnlyItemViewHolder extends BaseDelegateViewHolder {

        @BindView(R.id.layout_only_content)
        protected ViewGroup layoutOnlyContent;

        public LayoutOnlyItemViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout_only, parent, false));
        }

        @Override
        protected void onBindViewHolder(ViewType viewType) {
            LayoutOnlyViewType layoutOnlyViewType = (LayoutOnlyViewType) viewType;
            layoutOnlyContent.addView(LayoutInflater.from(itemView.getContext()).inflate(layoutOnlyViewType.getLayoutId(), layoutOnlyContent, false));
        }
    }
}
