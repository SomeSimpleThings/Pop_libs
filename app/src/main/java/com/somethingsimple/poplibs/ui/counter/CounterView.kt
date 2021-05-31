package com.somethingsimple.poplibs.ui.counter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CounterView : MvpView {
    fun setButtonOneText(text: String, color: Int)
    fun setButtonTwoText(text: String, color: Int)
    fun setButtonThreeText(text: String, color: Int)
}
