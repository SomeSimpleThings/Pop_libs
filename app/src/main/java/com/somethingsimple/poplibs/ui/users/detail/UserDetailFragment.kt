package com.somethingsimple.poplibs.ui.users.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.somethingsimple.poplibs.PopLibsApplication.Navigation.router
import com.somethingsimple.poplibs.data.user.UsersRepoFactory
import com.somethingsimple.poplibs.data.user.model.GithubUser
import com.somethingsimple.poplibs.databinding.FragmentUserDetailBinding
import com.somethingsimple.poplibs.ui.common.BackButtonListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val ARG_USER_PARCELABLE = "user_parcelable"

class UserDetailFragment : MvpAppCompatFragment(), UserDetailView, BackButtonListener {
    private var viewBinding: FragmentUserDetailBinding? = null
    val presenter: UserDetailPresenter by moxyPresenter {
        UserDetailPresenter(
            UsersRepoFactory.create(),
            router,
            AndroidSchedulers.mainThread()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val user: GithubUser? = it.getParcelable(ARG_USER_PARCELABLE)
            user?.let { user1 -> presenter.showUser(user1) }
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

    override fun showUserNotFound() {
        Toast
            .makeText(context, "user not found", Toast.LENGTH_SHORT)
            .show()
    }
}