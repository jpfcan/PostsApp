package com.juanpablofajardo.postsapp.ui.adapters.detail.delegate_items;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewType;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.base.BaseDelegateItem;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.base.BaseDelegateViewHolder;
import com.juanpablofajardo.postsapp.ui.adapters.detail.view_types.PostDetailPostInfoViewType;
import com.juanpablofajardo.postsapp.utils.UIHelper;

import butterknife.BindView;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public class PostDetailPostInfoItemDelegate extends BaseDelegateItem {


    @Override
    public BaseDelegateViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PostDetailPostInfoItemViewHolder(parent);
    }

    public class PostDetailPostInfoItemViewHolder extends BaseDelegateViewHolder {

        @BindView(R.id.post_info_title)
        protected TextView tvPostTitle;

        @BindView(R.id.post_info_content)
        protected TextView tvPostContent;


        public PostDetailPostInfoItemViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_detail_post_info, parent, false));
        }

        @Override
        protected void onBindViewHolder(final ViewType viewType) {
            PostDetailPostInfoViewType postInfoViewType = (PostDetailPostInfoViewType) viewType;

            tvPostTitle.setText(postInfoViewType.getTitle());
            tvPostContent.setText(postInfoViewType.getContent());
        }
    }
}
