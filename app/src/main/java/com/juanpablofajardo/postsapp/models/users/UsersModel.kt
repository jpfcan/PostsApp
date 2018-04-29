package com.juanpablofajardo.postsapp.models.users

import com.juanpablofajardo.postsapp.app.AppManager
import com.juanpablofajardo.postsapp.callbacks.UsersListener
import com.juanpablofajardo.postsapp.objects.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
class UsersModel @Inject constructor() {

    fun fetchUsers(listener: UsersListener) {
        AppManager.RETROFIT_INSTANCE.getUsers().enqueue(object: Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful && response.body() != null) {
                    listener.onUsersFetchSuccess(response.body()!!)
                } else {
                    listener.onError()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable?) {
                listener.onError()
            }
        })
    }

}