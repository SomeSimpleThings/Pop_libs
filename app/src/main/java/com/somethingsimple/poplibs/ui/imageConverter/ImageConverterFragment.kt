package com.somethingsimple.poplibs.ui.imageConverter

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.somethingsimple.poplibs.PopLibsApplication
import com.somethingsimple.poplibs.R
import com.somethingsimple.poplibs.databinding.FragmentImageConverterBinding
import com.somethingsimple.poplibs.ui.common.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ImageConverterFragment : MvpAppCompatFragment(), ImageConverterView, BackButtonListener {

    private var viewBinding: FragmentImageConverterBinding? = null
    val presenter: ImageConverterPresenter by moxyPresenter {
        ImageConverterPresenter(PopLibsApplication.Navigation.router)
    }

    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            presenter.chooseImage()
        } else {
            presenter.showImageChooserUnavailable()
        }
    }

    val getImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                presenter.imageSelected(uri)
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentImageConverterBinding.inflate(inflater, container, false)
        .apply {
            viewBinding = this
        }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding?.apply {
            imageChooser.setOnClickListener {
                when {
                    permissionGranted() -> {
                        presenter.chooseImage()
                    }
                    shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                        // In an educational UI, explain to the user why your app requires this
                        // permission for a specific feature to behave as expected. In this UI,
                        // include a "cancel" or "no thanks" button that allows the user to
                        // continue using your app without granting the permission.
//                    showInContextUI(...)
                    }
                    else -> {
                        requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    }
                }
                imageConverter.setOnClickListener { presenter.convertImage() }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    private fun permissionGranted() = ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED

    override fun selectImage() {
        getImageLauncher.launch("image/*")
    }

    override fun convert() {
        TODO("Not yet implemented")
    }

    override fun showPermissionDenied() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.perms_not_granted))
            .setMessage(getString(R.string.need_perms))
            .setPositiveButton(getString(R.string.ok)) { _, _ -> }
            .create()
            .show()
    }

    override fun showSelectedImage(uri: Uri) {
        viewBinding?.image?.setImageURI(uri)
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    companion object {
        @JvmStatic
        fun newInstance() =
            ImageConverterFragment()
    }
}