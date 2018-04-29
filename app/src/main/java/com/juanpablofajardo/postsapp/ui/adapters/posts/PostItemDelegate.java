package com.juanpablofajardo.postsapp.ui.adapters.posts;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewType;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.base.BaseDelegateItem;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.base.BaseDelegateViewHolder;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class PostItemDelegate extends BaseDelegateItem {

    public interface PostItemListener {
        void onPostViewTypeClicked(PostViewType postViewType);
        void onFavoriteIndicatorClick(PostViewType postViewType);
    }

    private final PostItemListener listener;

    public PostItemDelegate(PostItemListener listener) {
        this.listener = listener;
    }

    @Override
    public BaseDelegateViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PostItemViewHolder(parent);
    }

    public class PostItemViewHolder extends BaseDelegateViewHolder {

        @BindView(R.id.list_item_not_read_indicator)
        protected View notReadIndicator;

        @BindView(R.id.list_item_title)
        protected TextView postTitle;

        @BindView(R.id.list_item_content_preview)
        protected TextView postPreview;

        @BindView(R.id.list_item_favorite_indicator)
        protected ImageView favoriteAction;


        public PostItemViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_post, parent, false));
        }

        @Override
        protected void onBindViewHolder(ViewType viewType) {
            final PostViewType postViewType = (PostViewType) viewType;

            notReadIndicator.setVisibility(postViewType.isRead() ? View.INVISIBLE : View.VISIBLE);

            postTitle.setText(postViewType.getTitle());
            postPreview.setText(postViewType.getContent());

            favoriteAction.setImageResource(postViewType.isFavorite() ? R.drawable.ic_favorite_filled : R.drawable.ic_favorite_not_filled);
            favoriteAction.setOnClickListener(view -> listener.onFavoriteIndicatorClick(postViewType));

            itemView.setOnClickListener(v -> listener.onPostViewTypeClicked(postViewType));
        }

    }

}
