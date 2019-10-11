package com.man.kiwari.utils

import com.orhanobut.hawk.Hawk

object Preference {

    val USERS = "NAME"
    val AVATAR = "AVATAR"
    val UID = "UID"
    val empty = "empty"

    fun save(uid : String, name : String, avatar : String){
        Hawk.put(USERS, name)
        Hawk.put(UID, uid)
        Hawk.put(AVATAR, avatar)
    }

    fun remove(){
        Hawk.delete(USERS)
        Hawk.delete(UID)
        Hawk.delete(AVATAR)
    }



    fun getUid() : String = Hawk.get(UID, empty)

    fun getName() : String = Hawk.get(USERS)

    fun getAvatar() : String = Hawk.get(AVATAR)

}