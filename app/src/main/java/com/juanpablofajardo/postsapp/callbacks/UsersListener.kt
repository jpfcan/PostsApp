package com.juanpablofajardo.postsapp.callbacks

import com.juanpablofajardo.postsapp.objects.User

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
interface UsersListener: ServiceEvent {
    fun onUsersFetchSuccess(users: List<User>)
}