package com.somethingsimple.poplibs.ui.main

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.somethingsimple.poplibs.PopLibsApplication
import com.somethingsimple.poplibs.R
import com.somethingsimple.poplibs.databinding.ActivityMainBinding
import com.somethingsimple.poplibs.ui.AndroidScreens
import com.somethingsimple.poplibs.ui.common.BackButtonListener
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter(
            PopLibsApplication.INSTANCE.router,
            AndroidScreens()
        )
    }
    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        PopLibsApplication.INSTANCE.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        PopLibsApplication.INSTANCE.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}
