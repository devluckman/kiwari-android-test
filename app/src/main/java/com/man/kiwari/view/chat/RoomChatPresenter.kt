package com.man.kiwari.view.chat

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.man.kiwari.App
import com.man.kiwari.model.Chats
import java.util.*

class RoomChatPresenter {
    private var path : String? = null
    private var roomView: RoomChatContract? = null

    fun fetchData (){
        val mutableList : MutableList<Chats> = mutableListOf()
        App.mReference.child("room").child(path!!).addListenerForSingleValueEvent(object : ValueEventListener{
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
                roomView!!.failedFetchData(data.message)
            }
        })
    }

    fun postData (data : Chats){
        App.mReference.child("room").child(path!!).child(Date().time.toString()).setValue(data)
        fetchData()
    }

    fun subscribe(view: RoomChatContract, roomId : String) {
        this.path = roomId
        this@RoomChatPresenter.roomView = view
    }

    fun unsubscribe() {
        this.path = null
        this@RoomChatPresenter.roomView = null
    }

}