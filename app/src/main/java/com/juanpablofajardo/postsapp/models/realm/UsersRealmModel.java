package com.juanpablofajardo.postsapp.models.realm;

import com.juanpablofajardo.postsapp.objects.User;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
public class UsersRealmModel {

    private static final String  ID_KEY = "id";

    @Inject
    public UsersRealmModel() {
    }

    public User getUserById(int id) {
        return Realm.getDefaultInstance().where(User.class).equalTo(ID_KEY, id).findFirst();
    }

    public List<User> getAllUsers() {
        return Realm.getDefaultInstance().where(User.class).findAll();
    }

    public void updateUsers(List<User> usersToInsert) {
        Realm.getDefaultInstance().executeTransaction(realm -> realm.insertOrUpdate(usersToInsert));
    }

}
