package com.somethingsimple.poplibs

import android.annotation.SuppressLint
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.somethingsimple.poplibs.di.DaggerPopLibsComponent
import com.somethingsimple.poplibs.schedulers.DefaultSchedulers
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PopLibsApplication : DaggerApplication() {

    @SuppressLint("StaticFieldLeak")
    object ContextHolder {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        ContextHolder.context = applicationContext
    }

    override fun applicationInjector(): AndroidInjector<PopLibsApplication> =
        DaggerPopLibsComponent.builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()

                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
            }
            .withSchedulers(DefaultSchedulers)
            .build()


    companion object {
        internal lateinit var INSTANCE: PopLibsApplication
            private set
    }
}