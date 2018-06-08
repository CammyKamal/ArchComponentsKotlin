package com.example.kamal.architecturecomponentskotlin

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    var title: ObservableField<String>
    var userName: ObservableField<String>
    var password: ObservableField<String>
    var usernameHint: ObservableField<Boolean>
    var passwordHint: ObservableField<Boolean>
    val signInResult: MutableLiveData<Boolean> = MutableLiveData()


    init {
        title = ObservableField()
        title.set("Kamal")
        userName = ObservableField()
        password = ObservableField()
        usernameHint = ObservableField()
        passwordHint = ObservableField()
    }


    fun signInClick() {
        if (!userName.get().toString().equals("") && userName.get().toString().equals("kamal")) {
            if (!password.get().toString().equals("") && password.get().toString().equals("123")) {

                signInResult.postValue(true)
            }
        }
    }

    fun cancelClick() {

    }

}