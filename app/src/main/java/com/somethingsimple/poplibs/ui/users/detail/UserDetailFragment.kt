package com.somethingsimple.poplibs.ui.users.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.somethingsimple.poplibs.PopLibsApplication
import com.somethingsimple.poplibs.data.model.GithubUser
import com.somethingsimple.poplibs.databinding.FragmentUserDetailBinding
import com.somethingsimple.poplibs.ui.common.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val ARG_USER_PARCELABLE = "user_parcelable"

class UserDetailFragment : MvpAppCompatFragment(), UserDetailView, BackButtonListener {
    private var viewBinding: FragmentUserDetailBinding? = null
    private var user: GithubUser? = null
    val presenter: UserDetailPresenter by moxyPresenter {
        UserDetailPresenter(
            PopLibsApplication.INSTANCE.router,
            user
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable(ARG_USER_PARCELABLE)
        }
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
        fun newInstance(user: GithubUser) = UserDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_USER_PARCELABLE, user)
            }
        }
    }

    override fun backPressed() = presenter.backPressed()

    override fun showUser(user: GithubUser) {
        viewBinding?.userLogin?.text = user.login
    }
}