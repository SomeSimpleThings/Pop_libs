package com.somethingsimple.poplibs.ui.imageConverter

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.somethingsimple.poplibs.PopLibsApplication
import com.somethingsimple.poplibs.R
import com.somethingsimple.poplibs.data.ImageConverter
import com.somethingsimple.poplibs.data.model.ImageToConvert
import com.somethingsimple.poplibs.databinding.FragmentImageConverterBinding
import com.somethingsimple.poplibs.ui.common.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class ImageConverterFragment : MvpAppCompatFragment(), ImageConverterView, BackButtonListener {

    private var viewBinding: FragmentImageConverterBinding? = null
    val presenter: ImageConverterPresenter by moxyPresenter {
        ImageConverterPresenter(
            PopLibsApplication.Navigation.router,
            ImageConverter(requireContext())
        )
    }

    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            presenter.chooseImage()
        } else {
            presenter.processPermissionsNotGranted()
        }
    }

    val getImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                presenter.imageSelected(ImageToConvert(null, uri))
            } ?: presenter.imageNotSelected()
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
                    }
                    else -> {
                        requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    }
                }
            }
            imageConverter.setOnClickListener {
                presenter.convertImage()
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

    override fun showConvertResult(image: ImageToConvert) {
        image.apply {
            viewBinding?.imageConverted?.setImageBitmap(bitmap)
            viewBinding?.imageConvertedText?.text = uri.toString()

        }
    }

    override fun showSelectedImage(image: ImageToConvert) {
        image.apply {
            viewBinding?.image?.setImageURI(uri)
            viewBinding?.imageText?.text = uri.toString()
        }
        viewBinding?.imageConverter?.isEnabled = true
    }

    override fun showConvertProgress(percent: Int) {
        //TODO("Not yet implemented")
    }

    override fun showConvertError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


    override fun showNoImageSelected() {
        showDialog(getString(R.string.image_not_selected), getString(R.string.please_select))
    }

    override fun showPermissionDenied() {
        showDialog(getString(R.string.perms_not_granted), getString(R.string.need_perms))
    }

    private fun showDialog(title: String, message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok)) { _, _ -> }
            .create()
            .show()
    }

    override fun backPressed(): Boolean = presenter.backPressed()

    companion object {
        @JvmStatic
        fun newInstance() =
            ImageConverterFragment()
    }
}