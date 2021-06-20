package com.somethingsimple.poplibs.ui.counter

import android.graphics.Color.CYAN
import android.graphics.Color.RED
import android.os.Bundle
import android.widget.Button
import com.somethingsimple.poplibs.data.counter.model.CounterModel
import com.somethingsimple.poplibs.databinding.ActivityCounterBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class CounterActivity : MvpAppCompatActivity(), CounterView {

    private lateinit var binding: ActivityCounterBinding
    private val presenter by moxyPresenter { Presenter(CounterModel()) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButtonClicks()
    }

    private fun setButtonClicks() {
        binding.btnCounter1.setOnClickListener { presenter.counterOneClick() }
        binding.btnCounter2.setOnClickListener { presenter.counterTwoClick() }
        binding.btnCounter3.setOnClickListener { presenter.counterThreeClick() }
    }

    override fun setButtonOneText(text: String, color: Int) {
        setButtonText(binding.btnCounter1, text, color)

    }

    override fun setButtonTwoText(text: String, color: Int) {
        setButtonText(binding.btnCounter2, text, color)

    }

    override fun setButtonThreeText(text: String, color: Int) {
        setButtonText(binding.btnCounter3, text, color)

    }

    private fun setButtonText(button: Button, text: String, color: Int) {
        button.apply {
            setText(text)
            setTextColor(getButtonColor(color))
        }
    }

    private fun getButtonColor(color: Int) = if (color == 0) RED else CYAN


}