package com.man.kiwari.view.login

class LoginContract {

    interface View{
        fun onSuccessLogin()

        fun onFailedLogin(msg : String)
    }

}