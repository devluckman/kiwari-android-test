package com.man.kiwari.view.chat

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.man.kiwari.App
import com.man.kiwari.model.Chats
import com.man.kiwari.view.login.LoginContract
import java.util.*

class RoomChatPresenter {

    private var roomView: RoomChatContract? = null

    fun fetchData (){
        val mutableList : MutableList<Chats> = mutableListOf()
        App.mReference.child("chats").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(data: DataSnapshot) {

                val list = data.children
                list.forEach {
                    val key = it.key
                    val message = data.child(key!!).getValue(Chats::class.java)
                    mutableList.add(message!!)
                }
                roomView!!.fetchData(mutableList)
            }

            override fun onCancelled(data: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    fun postData (data : Chats){
        App.mReference.child("chats").child(Date().time.toString()).setValue(data)
        fetchData()
    }

    fun subscribe(view: RoomChatContract) {
        this@RoomChatPresenter.roomView = view
    }

    fun unsubscribe() {
        this@RoomChatPresenter.roomView = null
    }

}