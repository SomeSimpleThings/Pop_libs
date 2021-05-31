package com.somethingsimple.poplibs.ui.users.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.somethingsimple.poplibs.PopLibsApplication
import com.somethingsimple.poplibs.databinding.FragmentUserDetailBinding
import com.somethingsimple.poplibs.ui.AndroidScreens
import com.somethingsimple.poplibs.ui.common.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailFragment : MvpAppCompatFragment(), UserDetailView, BackButtonListener {
    private var viewBinding: FragmentUserDetailBinding? = null
    val presenter: UserDetailPresenter by moxyPresenter {
        UserDetailPresenter(
            PopLibsApplication.INSTANCE.router,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUserDetailBinding
        .inflate(inflater, container, false)
        .apply {
            viewBinding = this
        }
        .root

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = UserDetailFragment()
    }

    override fun backPressed() = presenter.backPressed()
    override fun showUser() {
        TODO("Not yet implemented")
    }
}