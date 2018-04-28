package com.juanpablofajardo.postsapp.app

import android.app.Application
import com.juanpablofajardo.postsapp.api.ApiRetrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
class AppManager: Application() {

    companion object {
        lateinit var DAGGER_COMPONENT: AppComponent
        lateinit var RETROFIT_INSTANCE: ApiRetrofit
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }


    override fun onCreate() {
        super.onCreate()
        setupDagger()
        setupRetrofit()
    }

    private fun setupRetrofit() {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build()
        RETROFIT_INSTANCE = retrofit.create(ApiRetrofit::class.java)
    }

    private fun setupDagger() {
        DAGGER_COMPONENT = DaggerAppComponent.create()
    }
}