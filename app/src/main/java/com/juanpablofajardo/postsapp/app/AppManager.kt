package com.juanpablofajardo.postsapp.app

import android.app.Application
import com.juanpablofajardo.postsapp.api.ApiRetrofit
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Application class which defines and initializes necessary instances, in this case, Retrofit for the service calls, Dagger for dependency injection and Realm for persistance.
 */
class AppManager: Application() {

    companion object {
        lateinit var DAGGER_COMPONENT: AppComponent
        lateinit var RETROFIT_INSTANCE: ApiRetrofit
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        val REALM_SCHEMA_VERSION: Int = 0;
    }


    override fun onCreate() {
        super.onCreate()
        setupDagger()
        setupRetrofit()
        setupRealm()
    }

    private fun setupDagger() {
        DAGGER_COMPONENT = DaggerAppComponent.create()
    }

    /**
     * For Retrofit I added a logging interceptor so I'm able to check the requests and responses in the log while working
     */
    private fun setupRetrofit() {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build()
        RETROFIT_INSTANCE = retrofit.create(ApiRetrofit::class.java)
    }

    private fun setupRealm() {
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(REALM_SCHEMA_VERSION.toLong())
                .migration(AppRealmMigration())
                .build()
        Realm.setDefaultConfiguration(configuration)
    }

}