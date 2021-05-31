package com.somethingsimple.poplibs.ui.counter

import com.somethingsimple.poplibs.data.model.CounterModel
import moxy.MvpPresenter

class Presenter(val model: CounterModel) : MvpPresenter<CounterView>() {

    fun counterOneClick() {
        getNextValue(0).apply {
            viewState.setButtonOneText(this.toString(), getColor(this))
        }
    }

    fun counterTwoClick() {
        getNextValue(1).apply {
            viewState.setButtonTwoText(this.toString(), getColor(this))
        }
    }

    fun counterThreeClick() {
        getNextValue(2).apply {
            viewState.setButtonThreeText(this.toString(), getColor(this))
        }
    }

    private fun getColor(counter: Int) = if (counter > 3) RED else WHITE

    private fun getNextValue(counterId: Int): Int = model.increment(counterId)

    companion object {
        private const val RED = 0
        private const val WHITE = 1
    }
}