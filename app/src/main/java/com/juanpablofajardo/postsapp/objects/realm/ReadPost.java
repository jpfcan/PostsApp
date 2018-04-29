package com.juanpablofajardo.postsapp.objects.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class ReadPost extends RealmObject {

    @PrimaryKey
    private int postId;

}
