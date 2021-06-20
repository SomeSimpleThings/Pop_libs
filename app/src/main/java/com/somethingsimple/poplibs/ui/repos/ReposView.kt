package com.somethingsimple.poplibs.ui.repos

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ReposView : MvpView {
    fun init()
    fun updateList()
    fun addItemToList(position: Int)
    fun loadingError(text: String)
}