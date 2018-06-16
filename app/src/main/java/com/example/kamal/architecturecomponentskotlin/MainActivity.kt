package com.example.kamal.architecturecomponentskotlin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.databinding.Observable.OnPropertyChangedCallback
import android.os.Bundle
import android.widget.Toast
import com.example.kamal.architecturecomponentskotlin.core.BaseActivity
import com.example.kamal.architecturecomponentskotlin.databinding.ActivityMainBinding


class MainActivity : BaseActivity(), LoginClickCallback {

    //Variable declaration
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding
    val text: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Setting up view and view binding using Data binding
        setViewBindings()
        //Adding Text Watchers fir listening Text changes
        setPropertyChangeListeners()
    }

    /**
     * Method to set up the layout and Data bindings, Model, Lifecycle Owner and listeners
     */
    private fun setViewBindings() {
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        this.binding.model = mainActivityViewModel
        this.binding.setLifecycleOwner(this)
        this.binding.listener = this
        this.binding.executePendingBindings()
        setUpBaseViewModel(mainActivityViewModel)
    }

    /**
     * Method to register Text watchers for username and password Edittext
     */
    private fun setPropertyChangeListeners() {
        mainActivityViewModel.userName.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if(mainActivityViewModel.userName.get().toString().isEmpty())
                    mainActivityViewModel.usernameHint.set(false)
                else
                    mainActivityViewModel.usernameHint.set(true)
            }
        })

        mainActivityViewModel.password.addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if(mainActivityViewModel.password.get().toString().isEmpty())
                    mainActivityViewModel.passwordHint.set(false)
                else
                    mainActivityViewModel.passwordHint.set(true)
            }
        })

        //Setting up the Observer which will listen to sign in click one user clicks
        this.mainActivityViewModel.signInResult.observe(this, Observer {
            if (mainActivityViewModel.signInResult.value!!)
                Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "Login Fail", Toast.LENGTH_LONG).show()
        })

    }


    /**
     * Interface method handling user click on sign in button using view model
     */
    override fun signInClick() {
        mainActivityViewModel.signInClick()
    }

    /**
     * Interface method to handle cancel button click of user
     */
    override fun cancelClick() {
        finish()
    }

}

