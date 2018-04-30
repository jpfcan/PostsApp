package com.juanpablofajardo.postsapp.app;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by Juan Pablo Fajardo Cano on 4/29/18.
 *
 * this class is to be used if the app adds any new object that'll be stored in Realm, or if an existing one is modified.
 * When changes are made, the REALM_SCHEMA_VERSION value in {@link AppManager} needs to be incremented by one,
 * and the respective changes should be implemented here, otherwise, the app will crash.
 */
public class AppRealmMigration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        //Here we'll add new fields if the AlbumSticker model changes
        /*if (oldVersion == 0) {
            //Add the new fields or tables
            oldVersion ++;
        }*/
    }

}