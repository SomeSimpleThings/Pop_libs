package com.somethingsimple.poplibs.ui.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.somethingsimple.poplibs.PopLibsApplication.Navigation.router
import com.somethingsimple.poplibs.R
import com.somethingsimple.poplibs.data.repo.RepoRepositioryFactory
import com.somethingsimple.poplibs.data.repo.model.GithubRepo
import com.somethingsimple.poplibs.databinding.FragmentRepoDetailsBinding
import com.somethingsimple.poplibs.ui.common.BackButtonListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

private const val REPO_ID_PARAM = "repo_id"

class RepoDetailsFragment : MvpAppCompatFragment(), RepoView, BackButtonListener {
    private var param1: Int? = null
    private var viewBinding: FragmentRepoDetailsBinding? = null
    private val presenter: RepoPresenter by moxyPresenter {
        RepoPresenter(
            RepoRepositioryFactory.create(),
            router,
            AndroidSchedulers.mainThread()
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
    ): View? {
        FragmentRepoDetailsBinding.inflate(inflater, container, false).apply {
            viewBinding = this
        }.root
        return inflater.inflate(R.layout.fragment_repo_details, container, false)
    }

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
            repoName.text = repo.name
            description.text = repo.description
            language.text = repo.language
            issuesCount.text = repo.openIssues.toString()
            watchersCount.text = repo.watchersCount.toString()
        }
    }

    override fun showLoadError(message: String) {
        Toast.makeText(requireContext(), "message", Toast.LENGTH_SHORT).show()
    }

    override fun backPressed() = presenter.backPressed()
}