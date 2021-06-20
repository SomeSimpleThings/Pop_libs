package com.somethingsimple.poplibs.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.somethingsimple.poplibs.PopLibsApplication.Navigation.router
import com.somethingsimple.poplibs.data.user.UsersRepoFactory
import com.somethingsimple.poplibs.databinding.FragmentGithubUsersBinding
import com.somethingsimple.poplibs.ui.PopLibsAppScreens
import com.somethingsimple.poplibs.ui.common.BackButtonListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var viewBinding: FragmentGithubUsersBinding? = null
    private var adapter: GithubUsersAdapter? = null
    private val presenter: GithubUsersPresenter by moxyPresenter {
        GithubUsersPresenter(
            UsersRepoFactory.create(),
            router,
            PopLibsAppScreens,
            AndroidSchedulers.mainThread()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentGithubUsersBinding
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
        fun newInstance(): Fragment = UsersFragment()
    }

    override fun init() {
        adapter = GithubUsersAdapter(presenter.usersListPresenter)
        viewBinding?.rvUsers?.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }

    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun addItemToList(position: Int) {
        adapter?.notifyItemInserted(position)
    }

    override fun loadingError(text: String) {
//        TODO("Not yet implemented")
    }

    override fun backPressed() = presenter.backPressed()

}