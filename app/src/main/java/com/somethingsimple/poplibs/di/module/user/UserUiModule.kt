package com.somethingsimple.poplibs.di.module.user

import com.somethingsimple.poplibs.ui.user.UserDetailFragment
import com.somethingsimple.poplibs.ui.users.UsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserUiModule {

    @ContributesAndroidInjector
    abstract fun bindUsersFragment(): UsersFragment

    @ContributesAndroidInjector
    abstract fun bindUserFragment(): UserDetailFragment

}