package com.somethingsimple.poplibs.ui.users

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.somethingsimple.poplibs.data.model.GithubUser
import com.somethingsimple.poplibs.ui.users.detail.UserDetailFragment

interface IScreens {
    fun users(): Screen
    fun userDetails(user: GithubUser): Screen
}

object PopLibsAppScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun userDetails(user: GithubUser): Screen =
        FragmentScreen { UserDetailFragment.newInstance(user) }
}