package com.juanpablofajardo.postsapp.ui.adapters.detail.delegate_items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewType;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.base.BaseDelegateItem;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.base.BaseDelegateViewHolder;
import com.juanpablofajardo.postsapp.ui.adapters.detail.view_types.PostDetailUserInfoViewType;
import com.juanpablofajardo.postsapp.utils.UIHelper;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public class PostDetailUserInfoItemDelegate extends BaseDelegateItem {

    public interface UserInfoListener {
        void onUserEmailClick(final String email);
        void onUserWebsiteClick(final String website);
    }

    private final UserInfoListener listener;

    public PostDetailUserInfoItemDelegate(UserInfoListener listener) {
        this.listener = listener;
    }

    @Override
    public BaseDelegateViewHolder onCreateViewHolder(ViewGroup parent) {
        return new PostDetailUserInfoItemViewHolder(parent);
    }

    public class PostDetailUserInfoItemViewHolder extends BaseDelegateViewHolder {

        @BindView(R.id.post_info_user_username)
        protected TextView tvPostUserUsername;

        @BindView(R.id.post_info_user_name)
        protected TextView tvPostUserName;

        @BindView(R.id.post_info_user_email)
        protected TextView tvPostUserEmail;

        @BindView(R.id.post_info_user_phone)
        protected TextView tvPostUserPhone;

        @BindView(R.id.post_info_user_website)
        protected TextView tvPostUserWebsite;

        @BindString(R.string.empty_value_hyphens)
        protected String emptyValue;


        public PostDetailUserInfoItemViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_detail_user_info, parent, false));
        }

        @Override
        protected void onBindViewHolder(final ViewType viewType) {
            PostDetailUserInfoViewType userInfoViewType = (PostDetailUserInfoViewType) viewType;

            tvPostUserUsername.setText(itemView.getResources().getString(R.string.user_title_placeholder, userInfoViewType.getUserUsername()));
            tvPostUserName.setText(userInfoViewType.getUserName());
            tvPostUserPhone.setText(userInfoViewType.getUserPhone());

            if (userInfoViewType.getUserEmail().equals(emptyValue)) {
                tvPostUserEmail.setText(userInfoViewType.getUserEmail());
            } else {
                tvPostUserEmail.setText(UIHelper.underlineText(userInfoViewType.getUserEmail()));
                tvPostUserEmail.setOnClickListener(v -> listener.onUserEmailClick(userInfoViewType.getUserEmail()));
            }

            if (userInfoViewType.getUserWebsite().equals(emptyValue)) {
                tvPostUserWebsite.setText(userInfoViewType.getUserWebsite());
            } else {
                tvPostUserWebsite.setText(UIHelper.underlineText(userInfoViewType.getUserWebsite()));
                tvPostUserWebsite.setOnClickListener(v -> listener.onUserWebsiteClick(userInfoViewType.getUserWebsite()));
            }

        }
    }
}
