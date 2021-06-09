package com.somethingsimple.poplibs.ui.imageConverter

import com.somethingsimple.poplibs.data.converter.ImageToConvert
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ImageConverterView : MvpView {

    fun selectImage()
    fun showConvertResult(image: ImageToConvert)
    fun showPermissionDenied()
    fun showSelectedImage(image: ImageToConvert)
    fun showNoImageSelected()
    fun showConvertProgress(percent: Int)
    fun showConvertError(message: String)
}