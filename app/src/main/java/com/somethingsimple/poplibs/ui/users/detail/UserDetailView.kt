package com.somethingsimple.poplibs.ui.users.detail

import com.somethingsimple.poplibs.data.user.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserDetailView : MvpView {
    fun showUser(user: GithubUser)
    fun showUserNotFound()
}