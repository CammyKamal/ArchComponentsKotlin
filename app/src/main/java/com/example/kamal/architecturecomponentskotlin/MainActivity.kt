package com.example.kamal.architecturecomponentskotlin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.kamal.architecturecomponentskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LoginClickCallback, TextWatcher {

    //Variable declaration
    lateinit var viewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding
    val text: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Setting up view and view binding using Data binding
        setViewBindings()
        //Adding Text Watchers fir listening Text changes
        setTextWatchers()
        //Setting up the Observer which will listen to sign in click one user clicks
        this.viewModel.signInResult.observe(this, Observer {
            if (viewModel.signInResult.value!!)
                Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "Login Fail", Toast.LENGTH_LONG).show()
        })
    }

    /**
     * Method to register Text watchers for username and password Edittext
     */
    private fun setTextWatchers() {
        binding.usernameEt.addTextChangedListener(this)
        binding.passwordEt.addTextChangedListener(this)
    }


    /**
     * Method to set up the layout and Data bindings, Model, Lifecycle Owner and listeners
     */
    private fun setViewBindings() {
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        this.binding.model = viewModel
        this.binding.setLifecycleOwner(this)
        this.binding.listener = this
        this.binding.executePendingBindings()
    }

    /**
     * Overriding Text watcher method to listen when user types
     */
    override fun afterTextChanged(s: Editable?) {
        //Matching hashcodes to check in which Edittext user has entered/Deleted the value
        if (s!!.hashCode() == binding.usernameEt.text.hashCode()) {
            //Setting value entered to user name in the Username observable field in the View Model
            viewModel.userName.set(s.toString())
            // If user has entered value and setting user hint as true which will behave as floating Text and
            //which is also observe in the xml by these hint text views
            if (s.toString().length == 0) viewModel.usernameHint.set(false) else viewModel.usernameHint.set(true)
        } else {
            //Setting value entered to password in the password observable field in the View Model
            viewModel.password.set(s.toString())
            // If user has entered value and setting password hint as true which will behave as floating Text and
            //which is also observe in the xml by these hint text views
            if (s.toString().length == 0) viewModel.passwordHint.set(false) else viewModel.passwordHint.set(true)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        //nothing to implement here
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        //nothing to implement here
    }


    /**
     * Interface method handling user click on sign in button using view model
     */
    override fun signInClick() {
        viewModel.signInClick()
    }

    /**
     * Interface method to handle cancel button click of user
     */
    override fun cancelClick() {
        finish()
    }

}

