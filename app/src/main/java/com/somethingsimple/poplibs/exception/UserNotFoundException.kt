package com.somethingsimple.poplibs.exception

class UserNotFoundException(id: String) : Throwable("user with id $id not found") {
}