package com.juanpablofajardo.postsapp.models.users

import com.juanpablofajardo.postsapp.app.AppManager
import com.juanpablofajardo.postsapp.callbacks.UserListener
import com.juanpablofajardo.postsapp.objects.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Model class to be injected in the presenters that need to fetch Users from service
 */
class UsersModel @Inject constructor() {

    fun fetchUser(id: Int, listener: UserListener) {
        AppManager.RETROFIT_INSTANCE.getUser(id).enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful && response.body() != null) {
                    listener.onUserFetchSuccess(response.body()!!)
                } else {
                    listener.onError()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable?) {
                listener.onError()
            }
        })
    }

}