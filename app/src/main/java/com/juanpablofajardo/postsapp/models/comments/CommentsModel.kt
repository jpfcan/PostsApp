package com.juanpablofajardo.postsapp.models.comments

import com.juanpablofajardo.postsapp.app.AppManager
import com.juanpablofajardo.postsapp.callbacks.CommentsListener
import com.juanpablofajardo.postsapp.objects.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Model class to be injected in the presenters that need to fetch Comments in a post from service
 */
class CommentsModel @Inject constructor() {

    fun fetchPostComments(id: Int, listener: CommentsListener) {
        AppManager.RETROFIT_INSTANCE.getPostComments(id).enqueue(object: Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful && response.body() != null) {
                    listener.onCommentsFetchSuccess(response.body()!!)
                } else {
                    listener.onError()
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable?) {
                listener.onError()
            }
        })
    }

}