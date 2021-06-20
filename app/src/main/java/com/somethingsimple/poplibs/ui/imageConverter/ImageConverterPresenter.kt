package com.somethingsimple.poplibs.ui.imageConverter

import com.github.terrakok.cicerone.Router
import com.somethingsimple.poplibs.data.converter.ImageConverter
import com.somethingsimple.poplibs.data.converter.ImageToConvert
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter


class ImageConverterPresenter(
    private val router: Router,
    private val imageConverter: ImageConverter,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : MvpPresenter<ImageConverterView>() {

    fun chooseImage() {
        viewState.selectImage()
    }

    fun imageNotSelected() {
        viewState.showNoImageSelected()
    }

    fun convertImage() {
        val disposable =
            imageConverter.convertImage(outputFilename = "converted.png", quality = 80)
                .subscribe(
                    ::onConvertSuccess,
                    ::onErrorConverting
                )
        compositeDisposable.add(disposable)
    }

    private fun onConvertSuccess(imageToConvert: ImageToConvert?) {
        imageToConvert?.let {
            viewState.showConvertResult(it)
        }
    }

    private fun onErrorConverting(throwable: Throwable?) {
        throwable?.localizedMessage?.let {
            viewState.showConvertError(it)
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun processPermissionsNotGranted() {
        viewState.showPermissionDenied()
    }

    fun imageSelected(imageToConvert: ImageToConvert) {
        imageConverter.mImage = imageToConvert
        viewState.showSelectedImage(imageToConvert)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }
}