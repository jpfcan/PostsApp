package com.juanpablofajardo.postsapp.ui.adapters.detail;

import android.support.v4.util.SparseArrayCompat;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.objects.Comment;
import com.juanpablofajardo.postsapp.objects.Post;
import com.juanpablofajardo.postsapp.objects.User;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewType;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.base.BaseDelegateAdapter;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.common.LayoutOnlyItemDelegate;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.common.LayoutOnlyViewType;
import com.juanpablofajardo.postsapp.ui.adapters.detail.delegate_items.PostDetailCommentItemDelegate;
import com.juanpablofajardo.postsapp.ui.adapters.detail.delegate_items.PostDetailPostInfoItemDelegate;
import com.juanpablofajardo.postsapp.ui.adapters.detail.delegate_items.PostDetailUserInfoItemDelegate;
import com.juanpablofajardo.postsapp.ui.adapters.detail.delegate_items.PostDetailUserInfoItemDelegate.UserInfoListener;
import com.juanpablofajardo.postsapp.ui.adapters.detail.view_types.PostDetailPostInfoViewType;
import com.juanpablofajardo.postsapp.ui.adapters.detail.view_types.PostDetailUserInfoViewType;
import com.juanpablofajardo.postsapp.utils.TransformUtils;

import java.util.ArrayList;
import java.util.List;

import static com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewTypeConstants.LAYOUT_ONLY_VIEWTYPE;
import static com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewTypeConstants.POST_DETAIL_COMMENT_VIEW_TYPE;
import static com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewTypeConstants.POST_DETAIL_POST_INFO_VIEW_TYPE;
import static com.juanpablofajardo.postsapp.ui.adapters.delegate.ViewTypeConstants.POST_DETAIL_USER_INFO_VIEW_TYPE;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 */
public class PostDetailAdapter extends BaseDelegateAdapter implements UserInfoListener {

    private LayoutOnlyViewType userInfoLoading = new LayoutOnlyViewType(R.layout.item_post_detail_loading);
    private LayoutOnlyViewType commentsHeader = new LayoutOnlyViewType(R.layout.item_post_detail_comments_header);
    private LayoutOnlyViewType commentsLoading = new LayoutOnlyViewType(R.layout.item_post_detail_loading);


    public interface PostDetailAdapterListener {
        void onDetailItemEmailClick(final String email);
        void onDetailItemWebsiteClick(final String website);
    }

    private final PostDetailAdapterListener listener;

    public PostDetailAdapter(PostDetailAdapterListener listener) {
        super(new SparseArrayCompat<>(), new ArrayList<>());

        this.listener = listener;

        this.delegates.put(POST_DETAIL_POST_INFO_VIEW_TYPE, new PostDetailPostInfoItemDelegate());
        this.delegates.put(POST_DETAIL_USER_INFO_VIEW_TYPE, new PostDetailUserInfoItemDelegate(this));
        this.delegates.put(POST_DETAIL_COMMENT_VIEW_TYPE, new PostDetailCommentItemDelegate());
        this.delegates.put(LAYOUT_ONLY_VIEWTYPE, new LayoutOnlyItemDelegate());
    }

    public void addPostInfoItem(final Post post) {
        this.items.add(TransformUtils.getPostInfoViewType(post));
        this.items.add(userInfoLoading);
        this.items.add(commentsHeader);
        this.items.add(commentsLoading);
    }

    public void addPostUserInfo(final User postUser) {
        addViewType(TransformUtils.getPostUserInfoViewType(postUser), getItemPosition(userInfoLoading));
        removeViewType(userInfoLoading);
    }

    public void addComments(List<Comment> comments) {
        addViewTypeRange(TransformUtils.getPostCommentViewTypes(comments));
        removeViewType(commentsLoading);
    }

    @Override
    public void onUserEmailClick(final String email) {
        listener.onDetailItemEmailClick(email);
    }

    @Override
    public void onUserWebsiteClick(final String website) {
        listener.onDetailItemWebsiteClick(website);
    }
}
