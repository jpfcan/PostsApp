package com.juanpablofajardo.postsapp.ui.activities

import android.content.DialogInterface
import android.content.DialogInterface.OnDismissListener
import android.os.Bundle
import com.juanpablofajardo.postsapp.R
import com.juanpablofajardo.postsapp.app.AppManager
import com.juanpablofajardo.postsapp.callbacks.PostsListener
import com.juanpablofajardo.postsapp.models.posts.PostsModel
import com.juanpablofajardo.postsapp.models.realm.PostsRealmModel
import com.juanpablofajardo.postsapp.navigators.MainNavigator
import com.juanpablofajardo.postsapp.objects.Post
import com.juanpablofajardo.postsapp.ui.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    /*
    @Inject lateinit var usersModel: UsersModel
    @Inject lateinit var usersRealmModel: UsersRealmModel
    */

    @Inject lateinit var postsModel: PostsModel
    @Inject lateinit var postsRealmModel: PostsRealmModel

    @Inject lateinit var navigator: MainNavigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.DAGGER_COMPONENT.inject(this)

        fetchPosts()
    }

    // FOR THE SPECIFIC TEST PURPOSE THIS WOULD BE USEFUL, GIVEN THAT WE WOULD ONLY HAVE A LIMITED AMOUNT OF USERS (10)

    /*
    private fun fetchUsers() {
        usersModel.fetchUsers(object : UsersListener {
            override fun onUsersFetchSuccess(users: List<User>) {
                usersRealmModel.updateUsers(users)
                fetchPosts()
            }

            override fun onError() {
                fetchPosts()
            }
        })
    }
    */

    private fun fetchPosts() {
        postsModel.fetchPosts(object : PostsListener {
            override fun onPostsFetchSuccess(posts: List<Post>) {
                setFirstTwentyAsUnRead(posts)
                postsRealmModel.insertPosts(posts)
                navigator.launchListsSection(this@MainActivity)
            }

            override fun onError() {
                navigator.showConnectionErrorDialog(this@MainActivity, object : OnDismissListener {
                    override fun onDismiss(dialog: DialogInterface?) {
                        navigator.launchListsSection(this@MainActivity)
                    }
                })
            }
        })
    }

    //This is as required by the test specifications
    //As an asumption, it is done only when the posts are fetched from service
    private fun setFirstTwentyAsUnRead(posts: List<Post>) {
        postsRealmModel.removeReadPosts(posts.subList(0, 20))
    }

    override fun getLayoutResource() = R.layout.activity_main

}
