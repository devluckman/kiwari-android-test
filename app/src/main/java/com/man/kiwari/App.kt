package com.man.kiwari

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import com.orhanobut.hawk.Hawk

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Hawk.init(this).build()

        FirebaseMessaging.getInstance().isAutoInitEnabled = true

        mReference = FirebaseDatabase.getInstance().reference

        mAuth = FirebaseAuth.getInstance()
    }


    companion object{
        lateinit var mAuth : FirebaseAuth
        lateinit var mReference : DatabaseReference
    }
}