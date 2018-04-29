package com.juanpablofajardo.postsapp.api

import com.juanpablofajardo.postsapp.objects.Post
import com.juanpablofajardo.postsapp.objects.User
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
interface ApiRetrofit {

    companion object {
        const val POSTS_URL_PATH = "posts"
        const val USERS_URL_PATH = "users"
    }

    @GET(POSTS_URL_PATH)
    fun getPosts(): Call<List<Post>>

    @GET(USERS_URL_PATH)
    fun getUsers(): Call<List<User>>

}