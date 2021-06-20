package com.somethingsimple.poplibs.ui

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.somethingsimple.poplibs.R
import com.somethingsimple.poplibs.ui.common.BackButtonListener
import com.somethingsimple.poplibs.ui.common.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val navigator = AppNavigator(this, R.id.container)

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: router.newRootScreen(PopLibsAppScreens.users())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
    }
}
