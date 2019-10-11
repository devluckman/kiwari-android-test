package com.man.kiwari.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.man.kiwari.R
import com.man.kiwari.model.Chats
import com.man.kiwari.utils.DateHelper
import kotlinx.android.synthetic.main.row_chat.view.*

class ChatAdapter(var list: List<Chats>, var context: Context) :
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_chat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.user.text = list[position].name
        holder.message.text = list[position].message
        holder.date.text = DateHelper.getDate(list[position].date!!.toLong()) +" "+ DateHelper.getTime(list[position].date!!.toLong())
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val user = view.item_username
        val message = view.item_message
        val date = view.item_date

    }
}