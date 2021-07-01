package com.somethingsimple.poplibs.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.R
import com.somethingsimple.poplibs.data.user.UsersRepository
import com.somethingsimple.poplibs.databinding.FragmentGithubUsersBinding
import com.somethingsimple.poplibs.schedulers.Schedulers
import com.somethingsimple.poplibs.ui.PopLibsAppScreens
import com.somethingsimple.poplibs.ui.common.BackButtonListener
import com.somethingsimple.poplibs.ui.common.BaseFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class UsersFragment : BaseFragment(R.layout.fragment_github_users), UsersView, BackButtonListener {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var userRepository: UsersRepository

    @Inject
    lateinit var schedulers: Schedulers


    private var viewBinding: FragmentGithubUsersBinding? = null
    private var adapter: GithubUsersAdapter? = null
    private val presenter: GithubUsersPresenter by moxyPresenter {
        GithubUsersPresenter(
            userRepository,
            router,
            PopLibsAppScreens,
            schedulers
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