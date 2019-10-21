package com.man.kiwari.view.dashboard


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.man.kiwari.R
import com.man.kiwari.main.InterfaceActivity
import com.man.kiwari.utils.Preference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_room_chat.*

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment(), View.OnClickListener {

    private var callback: InterfaceActivity? = null

    fun newInstance(): DashboardFragment {
        return DashboardFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

        btnLogout.setOnClickListener {
            Preference.remove()
            callback!!.login()
        }
    }


    private fun setupView() {
        Picasso.get().load(Preference.getAvatar()).into(profile_image)
        name_user.text = Preference.getName()

        room_event.setOnClickListener(this)
        room_game.setOnClickListener(this)
        room_sport.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            room_sport -> callback!!.openRoom(getString(R.string.sport))
            room_event -> callback!!.openRoom(getString(R.string.event))
            room_game -> callback!!.openRoom(getString(R.string.game))
        }
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
