package com.juanpablofajardo.postsapp.ui.activities

import android.os.Bundle
import android.os.Handler
import com.juanpablofajardo.postsapp.R
import com.juanpablofajardo.postsapp.app.AppManager
import com.juanpablofajardo.postsapp.navigators.MainNavigator
import com.juanpablofajardo.postsapp.ui.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    companion object {
        val SPLASH_TIME = 1500L
    }

    /*
    @Inject lateinit var usersModel: UsersModel
    @Inject lateinit var postsModel: PostsModel

    @Inject lateinit var usersRealmModel: UsersRealmModel
    @Inject lateinit var postsRealmModel: PostsRealmModel
    */
    @Inject lateinit var navigator: MainNavigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.DAGGER_COMPONENT.inject(this)

        //fetchUsers()
        Handler().postDelayed({navigator.launchListsSection(this)}, SPLASH_TIME)
    }

    // FOR THE SPECIFIC TEST PURPOSE THIS WOULD BE USEFUL, GIVEN THAT WE WOULD ONLY HAVE A LIMITED AMOUNT OF USERS (10)
    // AND THE AMOUNT OF POSTS (100) ISN'T TOO MUCH

    /*private fun fetchUsers() {
        usersModel.fetchUsers(object : UsersListener {
            override fun onUsersFetchSuccess(users: List<User>) {
                usersRealmModel.updateUsers(users)
                fetchPosts()
            }

            override fun onError() {
                //TODO maybe do nothing(?)
                fetchPosts()
            }
        })
    }

    private fun fetchPosts() {
        postsModel.fetchPosts(object : PostsListener {
            override fun onPostsFetchSuccess(posts: List<Post>) {
                postsRealmModel.insertPosts(posts)
                navigator.launchListsSection(this@MainActivity)
            }

            override fun onError() {
                //TODO show error dialog
            }
        })
    }*/

    override fun getLayoutResource() = R.layout.activity_main

}
