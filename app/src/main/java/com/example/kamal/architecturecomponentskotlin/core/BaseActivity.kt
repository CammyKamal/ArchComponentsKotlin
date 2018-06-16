package com.example.kamal.architecturecomponentskotlin.core

import android.arch.lifecycle.Observer
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.FrameLayout
import com.example.kamal.architecturecomponentskotlin.utils.Utils


//Base Activity : This will contain all the common functions which a activity require's
open class BaseActivity : AppCompatActivity() {

    lateinit var viewModel: BaseViewModel
    private var internetReceiver: InternetConnectivityBroadcast
    lateinit var mSnackbar: Snackbar
    var context: Context

    init {
        internetReceiver = InternetConnectivityBroadcast()
        context = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(internetReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    /**
     *  Method to set view model object in Base activity
     */
    open fun setUpBaseViewModel(baseViewModel: BaseViewModel) {
        this.viewModel = baseViewModel
        //registering internet error observe listener
        internetChangeObserve()
    }

    //Method to register for internet error value change
    fun internetChangeObserve() {
        viewModel.internetError.observe(this, Observer<Boolean> {
            var internetMessage: String

            if (!viewModel.internetError.value!!) {
                internetMessage = "No Internet Connection"
            } else {
                internetMessage = "Internet Connected"
            }
            mSnackbar = Snackbar.make(getWindow().getDecorView().getRootView(), internetMessage, Snackbar.LENGTH_LONG)
            val view = mSnackbar.getView()
            val params = view.getLayoutParams() as FrameLayout.LayoutParams
            params.gravity = Gravity.BOTTOM
            view.setLayoutParams(params)
            mSnackbar.show()
        })
    }


    //Broadcast receiver to listen internet changes
    inner class InternetConnectivityBroadcast : BroadcastReceiver() {


        override fun onReceive(context: Context?, intent: Intent?) {
            //Calling Util method to check if internet is available or not
            if (Utils.isConnectedOrConnecting(context!!)) {
                viewModel.internetError.value = true
            } else {
                viewModel.internetError.value = false
            }

        }
    }
}
