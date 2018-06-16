package com.example.kamal.architecturecomponentskotlin.core

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData

//baseview model class meant to be inherited by Child view model's
open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    // Mutable data to observe for value changes
     var internetError: MutableLiveData<Boolean>


    //Initializing variables here
    init {
        internetError=MutableLiveData()
        internetError.value = true
    }


}