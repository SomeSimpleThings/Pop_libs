package com.somethingsimple.poplibs.ui.imageConverter

import android.net.Uri
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class ImageConverterPresenter(
    private val router: Router
) : MvpPresenter<ImageConverterView>() {

    private var mUri: Uri? = null

    fun chooseImage() {
        viewState.selectImage()
    }

    fun convertImage() {
        mUri?.let {
            viewState.convert()
        }
            ?: viewState.showNoImageSelected()
    }

    fun backPressed(): Boolean {
        router.exit()
        mUri = null
        return true
    }

    fun showImageChooserUnavailable() {
        viewState.showPermissionDenied()
    }

    fun imageSelected(uri: Uri) {
        mUri = uri
        viewState.showSelectedImage(uri)
    }
}