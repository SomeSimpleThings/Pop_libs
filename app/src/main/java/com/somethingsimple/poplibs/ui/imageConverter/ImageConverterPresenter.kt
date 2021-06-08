package com.somethingsimple.poplibs.ui.imageConverter

import android.net.Uri
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class ImageConverterPresenter(
    private val router: Router
) : MvpPresenter<ImageConverterView>() {


    fun chooseImage() {
        viewState.selectImage()
    }

    fun convertImage() {
        viewState.convert()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun showImageChooserUnavailable() {
        viewState.showPermissionDenied()
    }

    fun imageSelected(uri: Uri) {
        viewState.showSelectedImage(uri)
    }
}