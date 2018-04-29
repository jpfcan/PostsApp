package com.juanpablofajardo.postsapp.presenters

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Defined interface to be used in evry presenter so that it enforces the setView method
 */
interface BasePresenter<T> {

    fun setView(view: T)

}