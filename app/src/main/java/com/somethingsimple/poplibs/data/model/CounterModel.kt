package com.somethingsimple.poplibs.data.model

class CounterModel {
    private val counters = mutableListOf(0, 0, 0)

    fun increment(index: Int): Int = ++counters[index]

    fun set(index: Int, value: Int) {
        counters[index] = value
    }

}