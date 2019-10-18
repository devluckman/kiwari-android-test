package com.man.kiwari.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.man.kiwari.R
import com.man.kiwari.view.chat.RoomChatFragment
import com.man.kiwari.view.login.LoginFragment
import com.man.kiwari.view.register.RegisterFragment
import com.man.kiwari.view.splash.SplashFragment
import android.app.ProgressDialog
import android.view.Gravity
import androidx.transition.Slide


class MainActivity : AppCompatActivity(), InterfaceActivity {

    private var dialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialog = ProgressDialog(this)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, SplashFragment().newInstance())
            .commit()
    }

    override fun openRoom() {
        replaceFragment(RoomChatFragment().newInstance())
    }

    override fun login() {
        replaceFragment(LoginFragment().newInstance())
    }

    override fun register() {
        replaceFragment(RegisterFragment().newInstance())
    }

    private fun replaceFragment(fragment: Fragment) {
        fragment.enterTransition = Slide(Gravity.END)
        fragment.exitTransition = Slide(Gravity.START)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun showLoading() {
        dialog!!.setMessage("Doing something, please wait.")
        dialog!!.show()
    }

    override fun dismissLoading() {
        dialog!!.dismiss()
    }

}
