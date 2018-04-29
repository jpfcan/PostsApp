package com.juanpablofajardo.postsapp.utils;

import com.juanpablofajardo.postsapp.objects.Comment;
import com.juanpablofajardo.postsapp.objects.Post;
import com.juanpablofajardo.postsapp.objects.User;
import com.juanpablofajardo.postsapp.objects.realm.FavoritePost;
import com.juanpablofajardo.postsapp.ui.adapters.detail.view_types.PostDetailCommentViewType;
import com.juanpablofajardo.postsapp.ui.adapters.detail.view_types.PostDetailPostInfoViewType;
import com.juanpablofajardo.postsapp.ui.adapters.detail.view_types.PostDetailUserInfoViewType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Class used to do transformations, this could be improved by using kotlin or Java 8 features
 * (the last one being only available from Api 24, which would leave a lot of users unable to use the app).
 */
public class TransformUtils {

    private static final String EMPTY_VALUE = "--";

    private TransformUtils() {
    }

    public static Integer[] transformFavoritePostToOnlyIdsArray(List<FavoritePost> favoritePosts) {
        Integer[] idsArray = new Integer[favoritePosts.size()];
        for (int i = 0; i < idsArray.length; i++) {
            idsArray[i] = favoritePosts.get(i).getId();
        }

        return idsArray;
    }

    public static PostDetailPostInfoViewType getPostInfoViewType(final Post post) {
        PostDetailPostInfoViewType postInfoViewType = new PostDetailPostInfoViewType();

        postInfoViewType.setTitle(post.getTitle() != null ? post.getTitle() : EMPTY_VALUE);
        postInfoViewType.setContent(post.getBody() != null ? post.getBody() : EMPTY_VALUE);

        return postInfoViewType;
    }

    public static PostDetailUserInfoViewType getPostUserInfoViewType(final User postUser) {
        PostDetailUserInfoViewType userInfoViewType = new PostDetailUserInfoViewType();

        userInfoViewType.setUserUsername(postUser.getUsername() != null ? postUser.getUsername() : EMPTY_VALUE);
        userInfoViewType.setUserName(postUser.getName() != null ? postUser.getName() : EMPTY_VALUE);
        userInfoViewType.setUserEmail(postUser.getEmail() != null ? postUser.getEmail() : EMPTY_VALUE);
        userInfoViewType.setUserPhone(postUser.getPhone() != null ? postUser.getPhone() : EMPTY_VALUE);
        userInfoViewType.setUserWebsite(postUser.getWebsite() != null ? postUser.getWebsite() : EMPTY_VALUE);

        return userInfoViewType;
    }

    public static List<PostDetailCommentViewType> getPostCommentViewTypes(final List<Comment> comments) {
        List<PostDetailCommentViewType> commentViewTypes = new ArrayList<>();

        for (Comment comment : comments) {
            PostDetailCommentViewType commentViewType = new PostDetailCommentViewType();

            commentViewType.setTitle(comment.getTitle() != null ? comment.getTitle() : EMPTY_VALUE);
            commentViewType.setContent(comment.getBody() != null ? comment.getBody() : EMPTY_VALUE);
            commentViewType.setAuthorEmail(comment.getEmail() != null ? comment.getEmail() : EMPTY_VALUE);

            commentViewTypes.add(commentViewType);
        }

        return commentViewTypes;
    }
}
