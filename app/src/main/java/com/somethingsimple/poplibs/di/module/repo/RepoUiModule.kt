package com.somethingsimple.poplibs.di.module.repo

import com.somethingsimple.poplibs.ui.repo.RepoDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RepoUiModule {
    @ContributesAndroidInjector
    abstract fun bindUsersFragment(): RepoDetailsFragment

}