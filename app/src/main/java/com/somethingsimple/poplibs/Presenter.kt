package com.somethingsimple.poplibs

class Presenter(val view: MainView, val model: Model) {

    fun counterClick(id: Int) {
        model.increment(id).let {
            val color = if (it > 3) RED else WHITE
            when (id) {
                0 -> view.setButton1Text("$it", color)
                1 -> view.setButton2Text("$it", color)
                2 -> view.setButton3Text("$it", color)
            }
        }
    }

    companion object {
        private const val RED = 0
        private const val WHITE = 1
    }
}