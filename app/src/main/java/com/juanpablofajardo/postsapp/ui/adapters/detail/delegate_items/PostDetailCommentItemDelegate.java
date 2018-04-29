package com.juanpablofajardo.postsapp.ui.adapters.detail.delegate_items;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewType;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.base.BaseDelegateItem;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.base.BaseDelegateViewHolder;
import com.juanpablofajardo.postsapp.ui.adapters.detail.view_types.PostDetailCommentViewType;

import butterknife.BindView;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public class PostDetailCommentItemDelegate extends BaseDelegateItem {

    @Override
    public BaseDelegateViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PostDetailCommentItemViewHolder(parent);
    }

    public class PostDetailCommentItemViewHolder extends BaseDelegateViewHolder {

        @BindView(R.id.post_detail_comment_title)
        protected TextView tvCommentTitle;

        @BindView(R.id.post_detail_comment_content)
        protected TextView tvCommentContent;

        @BindView(R.id.post_detail_comment_by)
        protected TextView tvCommentBy;

        public PostDetailCommentItemViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post_detail_comment, parent, false));
        }

        @Override
        protected void onBindViewHolder(ViewType viewType) {
            PostDetailCommentViewType commentViewType = (PostDetailCommentViewType) viewType;

            tvCommentTitle.setText(commentViewType.getTitle());
            tvCommentContent.setText(commentViewType.getContent());
            tvCommentBy.setText(commentViewType.getAuthorEmail());
        }
    }
}
