package com.juanpablofajardo.postsapp.models.posts

import com.juanpablofajardo.postsapp.app.AppManager
import com.juanpablofajardo.postsapp.callbacks.PostsListener
import com.juanpablofajardo.postsapp.objects.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Model class to be injected in the presenters that need to fetch posts from service
 */
class PostsModel @Inject constructor() {

    fun fetchPosts(listener: PostsListener) {
        AppManager.RETROFIT_INSTANCE.getPosts().enqueue(object: Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful && response.body() != null) {
                    listener.onPostsFetchSuccess(response.body()!!)
                } else {
                    listener.onError()
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable?) {
                listener.onError()
            }
        })
    }

}