package com.mikirinkode.bookshelfapp

import android.app.Application
import com.mikirinkode.bookshelfapp.data.AppContainer
import com.mikirinkode.bookshelfapp.data.DefaultAppContainer


class BaseApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
