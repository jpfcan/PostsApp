package com.juanpablofajardo.postsapp.api

import com.juanpablofajardo.postsapp.objects.Comment
import com.juanpablofajardo.postsapp.objects.Post
import com.juanpablofajardo.postsapp.objects.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Interface to be used by retrofit for service calls
 */
interface ApiRetrofit {

    companion object {
        //PATHS
        const val POSTS_URL_PATH = "posts"
        const val USER_URL_PATH = "users/{id}"
        const val POST_COMMENTS_PATH = "posts/{id}/comments"

        //PATH KEYS
        const val ID_PATH_KEY = "id"
    }

    @GET(POSTS_URL_PATH)
    fun getPosts() : Call<List<Post>>

    @GET(USER_URL_PATH)
    fun getUser(@Path(ID_PATH_KEY) id: Int) : Call<User>

    @GET(POST_COMMENTS_PATH)
    fun getPostComments(@Path(ID_PATH_KEY) id: Int) : Call<List<Comment>>

}