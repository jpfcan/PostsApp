package com.juanpablofajardo.postsapp.presenters.detail;

import android.support.annotation.DrawableRes;

import com.juanpablofajardo.postsapp.R;
import com.juanpablofajardo.postsapp.callbacks.CommentsListener;
import com.juanpablofajardo.postsapp.callbacks.UserListener;
import com.juanpablofajardo.postsapp.models.comments.CommentsModel;
import com.juanpablofajardo.postsapp.models.posts.PostsModel;
import com.juanpablofajardo.postsapp.models.realm.PostsRealmModel;
import com.juanpablofajardo.postsapp.models.realm.UsersRealmModel;
import com.juanpablofajardo.postsapp.models.users.UsersModel;
import com.juanpablofajardo.postsapp.navigators.PostDetailNavigator;
import com.juanpablofajardo.postsapp.objects.Comment;
import com.juanpablofajardo.postsapp.objects.Post;
import com.juanpablofajardo.postsapp.objects.User;
import com.juanpablofajardo.postsapp.objects.realm.FavoritePost;
import com.juanpablofajardo.postsapp.objects.realm.ReadPost;
import com.juanpablofajardo.postsapp.presenters.BasePresenter;
import com.juanpablofajardo.postsapp.ui.adapters.delegate.common.LayoutOnlyViewType;
import com.juanpablofajardo.postsapp.ui.adapters.detail.PostDetailAdapter;
import com.juanpablofajardo.postsapp.ui.adapters.detail.PostDetailAdapter.PostDetailAdapterListener;
import com.juanpablofajardo.postsapp.ui.view_interfaces.PostDetailView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 *
 * Presenter for the fragment that shows a Post's details.
 * It's in charge of setting data to the view, calling service for user and comments information and favorite action post.
 */
public class PostDetailPresenter implements BasePresenter<PostDetailView>, PostDetailAdapterListener {

    private PostDetailView view;

    private PostDetailNavigator navigator;

    private UsersModel usersModel;
    private UsersRealmModel usersRealmModel;
    private PostsRealmModel postsRealmModel;
    private CommentsModel commentsModel;

    private Post post;
    private User postUser;

    private PostDetailAdapter detailAdapter;

    private boolean isFavorite;


    @Inject
    public PostDetailPresenter(final PostDetailNavigator postDetailNavigator, final UsersModel usersModel, final UsersRealmModel usersRealmModel,
                               final PostsRealmModel postsRealmModel, final CommentsModel commentsModel) {
        this.navigator = postDetailNavigator;
        this.usersModel = usersModel;
        this.usersRealmModel = usersRealmModel;
        this.postsRealmModel = postsRealmModel;
        this.commentsModel = commentsModel;
    }

    @Override
    public void setView(PostDetailView view) {
        this.view = view;
    }

    public void setPost(final Post post) {
        this.post = post;
        this.isFavorite = postsRealmModel.checkIfFavorite(post.getId());
        postsRealmModel.addReadPost(new ReadPost(post.getId()));
    }

    public void setupPostDetailDelegateAdapter() {
        if (view != null) {
            detailAdapter = new PostDetailAdapter(this);
            detailAdapter.addPostInfoItem(post);

            view.setDetailAdapter(detailAdapter);
            getUserInformationFromDB();
            getCommentsFromService();
        }
    }

    private void getUserInformationFromDB() {
        try {
            User user = usersRealmModel.getUserById(post.getUserId());
            if (user == null) {
                getUserInformationFromService();
            } else {
                setupPostDetailUserInfo(user);
            }
        } catch (Exception e) {
            getUserInformationFromService();
        }
    }

    private void getUserInformationFromService() {
        usersModel.fetchUser(post.getUserId(), new UserListener() {
            @Override
            public void onUserFetchSuccess(@NotNull User user) {
                setupPostDetailUserInfo(user);
            }

            @Override
            public void onError() {
                detailAdapter.removeUserInfoLoader();
                if (view != null) {
                    view.showMessageToast(view.getResources().getString(R.string.error_fetching_user_info));
                }
            }
        });
    }

    private void setupPostDetailUserInfo(final User postUser) {
        this.postUser = postUser;
        detailAdapter.addPostUserInfo(this.postUser);
    }

    private void getCommentsFromService() {
        commentsModel.fetchPostComments(post.getId(), new CommentsListener() {
            @Override
            public void onCommentsFetchSuccess(List<Comment> comments) {
                detailAdapter.addComments(comments);
            }

            @Override
            public void onError() {
                detailAdapter.removeCommentsLoader();
                if (view != null) {
                    view.showMessageToast(view.getResources().getString(R.string.error_fetching_comments));
                }
            }
        });
    }

    public void onFavoriteItemAction() {
        this.isFavorite = !isFavorite;
        postsRealmModel.updateFavoritePost(new FavoritePost(post.getId()), isFavorite);
        if (view != null) {
            view.refreshOptionsMenu();
        }
    }

    @Override
    public void onDetailItemEmailClick(final String email) {
        if (navigator != null && view != null && view.getFragmentActivity() != null) {
            navigator.launchEmailIntent(view.getFragmentActivity(), email);
        }
    }

    @Override
    public void onDetailItemWebsiteClick(final String website) {
        if (navigator != null && view != null && view.getFragmentActivity() != null) {
            navigator.launchBrowserIntent(view.getFragmentActivity(), website);
        }
    }

    public boolean getShouldShowFavorite() {
        return post != null;
    }

    public @DrawableRes int getFavoriteStateIcon() {
        return isFavorite ? R.drawable.ic_favorite_filled_white : R.drawable.ic_favorite_not_filled_white;
    }
}
