package com.somethingsimple.poplibs.ui

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.somethingsimple.poplibs.data.user.model.GithubUser
import com.somethingsimple.poplibs.ui.imageConverter.ImageConverterFragment
import com.somethingsimple.poplibs.ui.repo.RepoDetailsFragment
import com.somethingsimple.poplibs.ui.user.UserDetailFragment
import com.somethingsimple.poplibs.ui.users.UsersFragment

interface IScreens {
    fun users(): Screen
    fun userDetails(user: GithubUser): Screen
    fun repoDetails(repoId: Int): Screen
    fun imageConverter(): Screen

}

object PopLibsAppScreens : IScreens {

    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun userDetails(user: GithubUser): Screen =
        FragmentScreen { UserDetailFragment.newInstance(user) }

    override fun repoDetails(repoId: Int): Screen = FragmentScreen {
        RepoDetailsFragment.newInstance(repoId)
    }

    override fun imageConverter(): Screen = FragmentScreen { ImageConverterFragment.newInstance() }

}