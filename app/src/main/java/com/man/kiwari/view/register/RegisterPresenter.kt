package com.man.kiwari.view.register

import com.man.kiwari.App
import com.man.kiwari.utils.Preference

class RegisterPresenter {

    private var registerView: RegisterContract? = null

    fun postRegisterUser(username : String, email : String, password : String){
        App.mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                postDataUser(App.mAuth.currentUser!!.uid, username, email)
            }else{
                registerView!!.onFailedLogin("Register Failed")
            }
        }
    }


    private fun postDataUser(uid : String, username : String, email : String){
        val avatar = "https://api.adorable.io/avatars/160/jarjit@mail.com.png"
        val hashData = HashMap<String, String>()
        hashData["avatar"] = avatar
        hashData["email"] = email
        hashData["name"] = username

        App.mReference.child("users").child(uid).setValue(hashData).addOnCompleteListener {
            if (it.isSuccessful){
                Preference.save(
                    uid = uid,
                    name = username,
                    avatar = avatar)
                registerView!!.onSuccessLogin()
            }else{
                registerView!!.onFailedLogin("Failed in Insert Data User")
            }
        }
    }

    fun subscribe(view: RegisterContract) {
        this@RegisterPresenter.registerView = view
    }

    fun unsubscribe() {
        this@RegisterPresenter.registerView = null
    }


}