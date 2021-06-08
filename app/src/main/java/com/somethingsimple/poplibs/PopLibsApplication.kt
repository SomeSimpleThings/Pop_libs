package com.somethingsimple.poplibs

import android.app.Application
import com.github.terrakok.cicerone.Cicerone

class PopLibsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
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