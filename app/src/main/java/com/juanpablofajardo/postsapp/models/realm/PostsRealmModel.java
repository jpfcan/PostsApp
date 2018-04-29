package com.juanpablofajardo.postsapp.models.realm;

import com.juanpablofajardo.postsapp.objects.Post;
import com.juanpablofajardo.postsapp.objects.realm.FavoritePost;
import com.juanpablofajardo.postsapp.objects.realm.ReadPost;
import com.juanpablofajardo.postsapp.utils.TransformUtils;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Model class to be injected in the presenters that need to fetch/save posts from/to Realm instance
 */
public class PostsRealmModel {

    private static final String POST_ID_KEY = "postId";
    private static final String ID_KEY = "id";

    @Inject
    public PostsRealmModel() {
    }

    public void insertPosts(List<Post> post) {
        Realm.getDefaultInstance().executeTransaction(realm -> realm.insertOrUpdate(post));
    }

    public List<Post> getPosts() {
        return Realm.getDefaultInstance().where(Post.class).findAll();
    }

    public void updateFavoritePost(FavoritePost post, boolean added) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            if (added) {
                realm.insert(post);
            } else {
                FavoritePost favoritePost = realm.where(FavoritePost.class).equalTo(POST_ID_KEY, post.getId()).findFirst();
                if (favoritePost != null) {
                    RealmObject.deleteFromRealm(favoritePost);
                }
            }
        });
    }

    public List<Post> getFavoritePosts() {
        Integer[] idsArray = TransformUtils.transformFavoritePostToOnlyIdsArray(getFavoritePostsIds());
        return Realm.getDefaultInstance().where(Post.class).in(ID_KEY, idsArray).findAll();
    }

    private List<FavoritePost> getFavoritePostsIds() {
        return Realm.getDefaultInstance().where(FavoritePost.class).findAll();
    }

    public boolean checkIfRead(int idToCheck) {
        return Realm.getDefaultInstance().where(ReadPost.class).equalTo(POST_ID_KEY, idToCheck).findFirst() != null;
    }

    public boolean checkIfFavorite(int idToCheck) {
        return Realm.getDefaultInstance().where(FavoritePost.class).equalTo(POST_ID_KEY, idToCheck).findFirst() != null;
    }

    public void removePost(int id) {
        Realm.getDefaultInstance().executeTransaction(realm -> {
            Post post = realm.where(Post.class).equalTo(ID_KEY, id).findFirst();
            if (post != null) {
                RealmObject.deleteFromRealm(post);
            }

            FavoritePost favoritePost = realm.where(FavoritePost.class).equalTo(POST_ID_KEY, id).findFirst();
            if (favoritePost != null) {
                RealmObject.deleteFromRealm(favoritePost);
            }

            ReadPost readPost = realm.where(ReadPost.class).equalTo(POST_ID_KEY, id).findFirst();
            if (readPost != null) {
                RealmObject.deleteFromRealm(readPost);
            }
        });
    }
}
