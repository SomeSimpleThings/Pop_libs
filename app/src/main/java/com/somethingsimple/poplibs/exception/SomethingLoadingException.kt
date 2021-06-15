package com.somethingsimple.poplibs.exception


class SomethingLoadingException(something: String) : Throwable("Cannot load $something") {
}