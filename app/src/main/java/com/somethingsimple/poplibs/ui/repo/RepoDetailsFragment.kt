package com.somethingsimple.poplibs.ui.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.R
import com.somethingsimple.poplibs.data.repo.RepoRepository
import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import com.somethingsimple.poplibs.databinding.FragmentRepoDetailsBinding
import com.somethingsimple.poplibs.schedulers.Schedulers
import com.somethingsimple.poplibs.ui.common.BackButtonListener
import com.somethingsimple.poplibs.ui.common.BaseFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

private const val REPO_ID_PARAM = "repo_id"

class RepoDetailsFragment : BaseFragment(R.layout.fragment_repo_details), RepoView,
    BackButtonListener {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var repoRepository: RepoRepository

    @Inject
    lateinit var schedulers: Schedulers

    private var param1: Int? = null
    private var viewBinding: FragmentRepoDetailsBinding? = null
    private val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter(
            repoRepository,
            router,
            schedulers
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(REPO_ID_PARAM).apply {
                presenter.getRepoInfo(this)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        FragmentRepoDetailsBinding.inflate(inflater, container, false).apply {
            viewBinding = this
        }.root

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            RepoDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(REPO_ID_PARAM, param1)
                }
            }
    }

    override fun showRepoDetails(repo: GithubRepo) {
        viewBinding?.apply {
            rname.text = repo.name
            rdescription.text = repo.description
            language.text = repo.language
            issuesCount.text = repo.openIssues.toString()
            watchersText.text = repo.watchersCount.toString()
        }
    }

    override fun showLoadError(message: String) {
        Toast.makeText(requireContext(), "message", Toast.LENGTH_SHORT).show()
    }

    override fun backPressed() = presenter.backPressed()
}