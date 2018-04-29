package com.juanpablofajardo.postsapp.utils;

import com.juanpablofajardo.postsapp.objects.realm.FavoritePost;

import java.util.List;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class TransformUtils {

    private TransformUtils() {
    }

    public static Integer[] transformFavoritePostToOnlyIdsArray(List<FavoritePost> favoritePosts) {
        Integer[] idsArray = new Integer[favoritePosts.size()];
        for (int i = 0; i < idsArray.length; i++) {
            idsArray[i] = favoritePosts.get(i).getId();
        }

        return idsArray;
    }
}
