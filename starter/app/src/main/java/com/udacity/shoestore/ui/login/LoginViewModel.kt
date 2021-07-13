package com.udacity.shoestore.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel : ViewModel() {

    var login: String = ""
    var password: String = ""

    private val _userHasLoggedIn = MutableLiveData<Boolean>()
    val userHasLoggedIn: LiveData<Boolean>
        get() = _userHasLoggedIn

    init {
        _userHasLoggedIn.value = false
    }

    fun onLogin() {
        _userHasLoggedIn.value = true
    }
}