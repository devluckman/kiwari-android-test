package com.man.kiwari.main

import android.app.ProgressDialog
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import com.man.kiwari.R
import com.man.kiwari.view.chat.RoomChatFragment
import com.man.kiwari.view.dashboard.DashboardFragment
import com.man.kiwari.view.login.LoginFragment
import com.man.kiwari.view.register.RegisterFragment
import com.man.kiwari.view.splash.SplashFragment


class MainActivity : AppCompatActivity(), InterfaceActivity {

    private var dialog: ProgressDialog? = null
    private var state = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialog = ProgressDialog(this)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, SplashFragment().newInstance())
            .commit()
    }

    override fun openRoom(id : String) {
        state = id
        val bundle = Bundle()
        bundle.putString("id", id)
        addFragment(RoomChatFragment().newInstance(bundle))
    }

    override fun login() {
        replaceFragment(LoginFragment().newInstance())
    }

    override fun register() {
        replaceFragment(RegisterFragment().newInstance())
    }

    override fun operDahsboard() {
        replaceFragment(DashboardFragment().newInstance())
    }

    private fun addFragment(fragment: Fragment){
        fragment.enterTransition = Slide(Gravity.END)
        fragment.exitTransition = Slide(Gravity.START)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, fragment).addToBackStack(fragment.javaClass.simpleName)
            .commit()
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


    override fun onBackPressed() {
        super.onBackPressed()
        when(state){
            getString(R.string.event) -> operDahsboard()
            getString(R.string.sport) -> operDahsboard()
            getString(R.string.game) -> operDahsboard()
        }
    }
}
