package com.juanpablofajardo.postsapp.objects.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Object that saves the id of a read post in Realm's instance.
 * This is so we can know which posts have been read by the user, even though he closes the app.
 */
public class ReadPost extends RealmObject {

    @PrimaryKey
    private int postId;

}
