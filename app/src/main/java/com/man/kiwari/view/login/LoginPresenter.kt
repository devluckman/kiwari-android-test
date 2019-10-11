package com.man.kiwari.view.login

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.man.kiwari.App
import com.man.kiwari.model.Users
import com.man.kiwari.utils.Preference

open class LoginPresenter {

    private var loginView: LoginContract.View? = null

    fun postLogin(email: String, password: String) {
        App.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {

                getDataUser(App.mAuth.currentUser!!.uid)

            } else {
                loginView!!.onFailedLogin("Login Failed")
            }
        }
    }

    private fun getDataUser(uid: String) {
        App.mReference.child("users").child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                val users = data.getValue(Users::class.java)
                if (users != null) {
                    Preference.save(
                        uid = uid,
                        name = users.name!!,
                        avatar = users.avatar!!)
                    loginView!!.onSuccessLogin()
                }
            }

            override fun onCancelled(data: DatabaseError) {
                loginView!!.onFailedLogin(data.message)
            }
        })
    }

    fun subscribe(view: LoginContract.View) {
        this@LoginPresenter.loginView = view
    }

    fun unsubscribe() {
        this@LoginPresenter.loginView = null
    }

}
