package com.man.kiwari.view.chat


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.man.kiwari.R
import com.man.kiwari.adapter.ChatAdapter
import com.man.kiwari.main.InterfaceActivity
import com.man.kiwari.model.Chats
import com.man.kiwari.utils.Preference
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_room_chat.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class RoomChatFragment : Fragment(), RoomChatContract {


    private var callback: InterfaceActivity? = null
    private lateinit var presenter: RoomChatPresenter
    private var rvAdapter: ChatAdapter? = null
    fun newInstance(): RoomChatFragment {
        return RoomChatFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPresenter()

        setupView()

        chat_input.setOnEditorActionListener { textView, i, keyEvent ->
            val msg = Chats()
            msg.message = chat_input.text.toString()
            msg.name = Preference.getName()
            msg.date = Date().time.toString()
            msg.uid = Preference.getUid()
            presenter.postData(msg)
            closeKeyboard()
            return@setOnEditorActionListener true
        }

        btnLogout.setOnClickListener {
            Preference.remove()
            callback!!.login()
        }
    }

    private fun setupPresenter() {
        presenter = RoomChatPresenter()
        presenter.subscribe(this)
    }

    private fun setupView() {
        Picasso.get().load(Preference.getAvatar()).into(profile_image)
        name_user.text = Preference.getName()
        presenter.fetchData()
    }

    override fun fetchData(list: List<Chats>) {
        if (list.isNotEmpty()) {
            rvAdapter = ChatAdapter(list, context!!)
            rv_message.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = rvAdapter
                smoothScrollToPosition(list.size - 1)
            }
        }
    }

    override fun failedFetchData(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    private fun closeKeyboard() {
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(chat_input.windowToken, 0)
        chat_input.setText("")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as InterfaceActivity
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
        presenter.unsubscribe()
    }

}
