package com.juanpablofajardo.postsapp.app

import com.juanpablofajardo.postsapp.ui.activities.MainActivity
import dagger.Component

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
@Component
interface AppComponent {

    fun inject(activity: MainActivity)

}