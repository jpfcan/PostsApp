package com.juanpablofajardo.postsapp.callbacks

import com.juanpablofajardo.postsapp.objects.User

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Listener that'll be used when the service returns a User
 */
interface UserListener : ServiceEvent {
    fun onUserFetchSuccess(user: User)
}