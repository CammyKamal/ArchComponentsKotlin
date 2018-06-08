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


    lateinit var viewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding
    val text: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewBindings()
        setTextWatchers()
        this.viewModel.signInResult.observe(this, Observer {
            if (viewModel.signInResult.value!!)
                Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show()
            else
                Toast.makeText(this, "Login Fail", Toast.LENGTH_LONG).show()
        })
    }

    private fun setTextWatchers() {
        binding.usernameEt.addTextChangedListener(this)
        binding.passwordEt.addTextChangedListener(this)
    }

    private fun setViewBindings() {
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        this.binding.model = viewModel
        this.binding.setLifecycleOwner(this)
        this.binding.listener = this

        this.binding.executePendingBindings()
    }

    override fun afterTextChanged(s: Editable?) {
        if (s!!.hashCode() == binding.usernameEt.text.hashCode()) {
            viewModel.userName.set(s.toString())
            if (s.toString().length == 0) viewModel.usernameHint.set(false) else viewModel.usernameHint.set(true)
        } else {
            viewModel.password.set(s.toString())
            if (s.toString().length == 0) viewModel.passwordHint.set(false) else viewModel.passwordHint.set(true)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        Log.e("", "")
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        Log.e("", "")
    }


    override fun signInClick() {
        viewModel.signInClick()
    }

    override fun cancelClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

