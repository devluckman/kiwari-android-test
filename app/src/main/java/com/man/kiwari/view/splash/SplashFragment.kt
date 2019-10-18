package com.man.kiwari.view.splash


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.man.kiwari.R
import com.man.kiwari.main.InterfaceActivity
import com.man.kiwari.utils.Preference

/**
 * A simple [Fragment] subclass.
 */
class SplashFragment : Fragment() {

    private var callback: InterfaceActivity? = null

    fun newInstance() : SplashFragment{
        return SplashFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Handler().postDelayed({
            if (Preference.getUid() != Preference.empty){
                callback!!.operDahsboard()
            }else{
                callback!!.login()
            }
        },3000)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as InterfaceActivity
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }
}
