package com.example.kamal.architecturecomponentskotlin

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.example.kamal.architecturecomponentskotlin.core.BaseViewModel

class MainActivityViewModel(application: Application) : BaseViewModel(application) {

    // Varibale declaration
    var title: ObservableField<String>
    var userName: ObservableField<String>
    var password: ObservableField<String>
    var usernameHint: ObservableField<Boolean>
    var passwordHint: ObservableField<Boolean>
    val signInResult: MutableLiveData<Boolean> = MutableLiveData()


    /**
     *  Initializing values in below block
     */
    init {
        title = ObservableField()
        title.set("Kamal")
        userName = ObservableField()
        password = ObservableField()
        usernameHint = ObservableField()
        passwordHint = ObservableField()
    }


    /**
     *  Method to handle sign in click, which will be called from UI via interface method
     *  This method compares value entered by user in edit text with static user name and password as of now
     */
    fun signInClick() {
        if (!userName.get().toString().equals("") && userName.get().toString().equals("kamal")) {
            if (!password.get().toString().equals("") && password.get().toString().equals("123")) {
                //Posting value in Sign in result, which will in turn call Onchanged() method which is being Observed in the main activity
                signInResult.postValue(true)
            }
        }
    }

}