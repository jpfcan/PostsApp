package com.juanpablofajardo.postsapp.presenters

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 */
interface BasePresenter<T> {

    fun setView(view: T)

}