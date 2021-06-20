package com.somethingsimple.poplibs.ui.repo

import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface RepoView : MvpView {

    fun showRepoDetails(repo: GithubRepo)
    fun showLoadError(message: String)
}