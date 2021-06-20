package com.somethingsimple.poplibs.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.R
import com.somethingsimple.poplibs.data.repo.RepoRepository
import com.somethingsimple.poplibs.data.user.UsersRepository
import com.somethingsimple.poplibs.data.user.model.GithubUser
import com.somethingsimple.poplibs.databinding.FragmentUserDetailBinding
import com.somethingsimple.poplibs.schedulers.Schedulers
import com.somethingsimple.poplibs.ui.PopLibsAppScreens
import com.somethingsimple.poplibs.ui.common.BackButtonListener
import com.somethingsimple.poplibs.ui.common.BaseFragment
import com.somethingsimple.poplibs.ui.repos.GithubRepoAdapter
import moxy.ktx.moxyPresenter
import javax.inject.Inject

private const val ARG_USER_PARCELABLE = "user_parcelable"

class UserDetailFragment : BaseFragment(R.layout.fragment_user_detail), UserDetailView,
    BackButtonListener {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var userRepository: UsersRepository

    @Inject
    lateinit var repoRepository: RepoRepository

    @Inject
    lateinit var schedulers: Schedulers

    private var viewBinding: FragmentUserDetailBinding? = null
    private var adapter: GithubRepoAdapter? = null
    val presenter: UserDetailPresenter by moxyPresenter {
        UserDetailPresenter(
            userRepository,
            repoRepository,
            router,
            schedulers,
            PopLibsAppScreens
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val userId: Int = it.getInt(ARG_USER_PARCELABLE)
            presenter.showUser(userId)
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
        fun newInstance(user: Int) = UserDetailFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_USER_PARCELABLE, user)
            }
        }
    }

    override fun backPressed() = presenter.backPressed()

    override fun showUser(user: GithubUser) {
        viewBinding?.apply {
            com.bumptech.glide.Glide
                .with(requireContext())
                .load(user.avatarUrl)
                .centerCrop()
                .into(userCard.userAvatar)
            userCard.userLogin.text = user.login
            userCard.userName.text = user.name
            userCard.email.text = user.email
            userCard.company.text = user.company
            userCard.location.text = user.location
            userCard.reposCount.text = user.publicRepos.toString()
            userCard.followersCount.text = user.followers.toString()
            userCard.followingCount.text = user.following.toString()
        }
    }

    override fun showUserNotFound() {
        Toast
            .makeText(context, "user not found", Toast.LENGTH_SHORT)
            .show()
    }

    override fun initRepoList() {
        adapter = GithubRepoAdapter(presenter.reposListPresenter)
        viewBinding?.rvRepos?.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }
    }

    override fun updateRepoList() {
        adapter?.notifyDataSetChanged()
    }
}