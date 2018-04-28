package com.juanpablofajardo.postsapp.callbacks

import com.juanpablofajardo.postsapp.objects.Post

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
interface PostsListener: ServiceEvent{
    fun onPostsFetchSuccess(posts: List<Post>)
}