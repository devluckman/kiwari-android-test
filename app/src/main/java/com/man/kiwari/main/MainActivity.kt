package com.man.kiwari.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.man.kiwari.R
import com.man.kiwari.view.chat.RoomChatFragment
import com.man.kiwari.view.login.LoginFragment
import com.man.kiwari.view.splash.SplashFragment

class MainActivity : AppCompatActivity(), InterfaceActivity {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, SplashFragment().newInstance())
            .commit()
    }

    override fun openRoom() {
        replaceFragment(RoomChatFragment().newInstance())
    }

    override fun logout() {
        replaceFragment(LoginFragment().newInstance())
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

}
