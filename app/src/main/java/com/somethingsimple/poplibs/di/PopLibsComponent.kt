package com.somethingsimple.poplibs.di

import android.content.Context
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.PopLibsApplication
import com.somethingsimple.poplibs.di.module.MainModule
import com.somethingsimple.poplibs.di.module.repo.RepoModule
import com.somethingsimple.poplibs.di.module.user.UserModule
import com.somethingsimple.poplibs.schedulers.Schedulers
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainModule::class,
        RepoModule::class,
        UserModule::class
    ]
)
interface PopLibsComponent : AndroidInjector<PopLibsApplication> {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun withContext(context: Context): Builder

        @BindsInstance
        fun withRouter(router: Router): Builder

        @BindsInstance
        fun withNavigatorHolder(navigatorHolder: NavigatorHolder): Builder

        @BindsInstance
        fun withSchedulers(schedulers: Schedulers): Builder

        fun build(): PopLibsComponent

    }
}