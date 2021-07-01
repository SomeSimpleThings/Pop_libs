package com.somethingsimple.poplibs.ui

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.somethingsimple.poplibs.ui.repo.RepoDetailsFragment
import com.somethingsimple.poplibs.ui.user.UserDetailFragment
import com.somethingsimple.poplibs.ui.users.UsersFragment

interface IScreens {
    fun users(): Screen
    fun userDetails(userId: Int): Screen
    fun repoDetails(repoId: Int): Screen

}

object PopLibsAppScreens : IScreens {

    override fun users() = FragmentScreen { UsersFragment.newInstance() }

    override fun userDetails(userId: Int): Screen =
        FragmentScreen { UserDetailFragment.newInstance(userId) }

    override fun repoDetails(repoId: Int): Screen = FragmentScreen {
        RepoDetailsFragment.newInstance(repoId)
    }

}