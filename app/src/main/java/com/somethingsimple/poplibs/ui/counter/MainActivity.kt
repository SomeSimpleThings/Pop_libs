package com.somethingsimple.poplibs.ui.counter

import android.graphics.Color.CYAN
import android.graphics.Color.RED
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.somethingsimple.poplibs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    val presenter = Presenter(this, Model())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setButtonClicks()
    }

    private fun setButtonClicks() {
        binding.btnCounter1.setOnClickListener { presenter.counterClick(0) }
        binding.btnCounter2.setOnClickListener { presenter.counterClick(1) }
        binding.btnCounter3.setOnClickListener { presenter.counterClick(2) }
    }

    override fun setButton1Text(text: String, color: Int) {
        setButtonText(binding.btnCounter1, text, color)
    }

    override fun setButton2Text(text: String, color: Int) {
        setButtonText(binding.btnCounter2, text, color)
    }

    override fun setButton3Text(text: String, color: Int) {
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