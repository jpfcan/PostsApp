package com.juanpablofajardo.postsapp.callbacks

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 *
 * Base service callback, so that the onError mehtod doesn't have to be repeated.
 * Every service callback must extend from this.
 */
interface ServiceEvent {
    fun onError();
}