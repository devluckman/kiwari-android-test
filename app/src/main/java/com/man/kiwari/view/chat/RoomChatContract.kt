package com.man.kiwari.view.chat

import com.man.kiwari.model.Chats

interface RoomChatContract {
    fun fetchData(list : List<Chats>)
}