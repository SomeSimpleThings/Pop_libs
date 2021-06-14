package com.somethingsimple.poplibs

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone

class PopLibsApplication : Application() {

    @SuppressLint("StaticFieldLeak")
    object ContextHolder {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        ContextHolder.context = applicationContext
    }

    companion object {
        internal lateinit var INSTANCE: PopLibsApplication
            private set
    }

    object Navigation {

        private val cicerone by lazy { Cicerone.create() }

        val router get() = cicerone.router
        val navigatorHolder get() = cicerone.getNavigatorHolder()

    }
}