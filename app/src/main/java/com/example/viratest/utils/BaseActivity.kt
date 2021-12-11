package com.example.viratest.utils

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    protected fun isNetworkConnected(): Boolean {
     return CheckNetwork.isNetworkAvailable(this)
    }
    protected fun ShowLongToast(message: String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
    protected fun ShowShortToast(message: String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}