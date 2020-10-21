package com.example.week3.network

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMServer : FirebaseMessagingService() {

    override fun onMessageReceived(p0: RemoteMessage) {

        Log.d("Tag", "Receive message=${p0.notification?.title}")

    }

    override fun onNewToken(token: String) {
        Log.d( "MyFirebaseMsgService", "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token: String?) {
        Log.d("MyFirebaseMsgService", "sendRegistrationTokenToServer($token)")
    }

}