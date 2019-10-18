package com.man.kiwari.service

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.content.Intent
import android.util.Log


open class FirebaseCloudMessagingService : FirebaseMessagingService() {

    var TAG = "FIREBASE MESSAGING"

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage != null) {
            Log.d(TAG, "From: " + remoteMessage.from)
        }

        if (remoteMessage!!.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData())

//            startActivity(Intent(this, NotifikasiActivity::class.java))

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {

            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body)
        }


    }

    override fun onNewToken(s: String?) {
        super.onNewToken(s)
    }


}