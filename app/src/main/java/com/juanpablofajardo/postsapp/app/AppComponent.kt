package com.juanpablofajardo.postsapp.app

import com.juanpablofajardo.postsapp.ui.activities.MainActivity
import com.juanpablofajardo.postsapp.ui.fragments.AllPostsFragment
import com.juanpablofajardo.postsapp.ui.fragments.FavoritePostsFragment
import com.juanpablofajardo.postsapp.ui.fragments.PostDetailFragment
import com.juanpablofajardo.postsapp.ui.fragments.PostListsFragment
import dagger.Component

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Interface for the Dagger component in charge of injecting dependencies
 */
@Component
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: PostListsFragment)

    fun inject(fragment: AllPostsFragment)

    fun inject(fragment: FavoritePostsFragment)

    fun inject(fragment: PostDetailFragment)

}