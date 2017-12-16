package com.struzzwebsolutions.scoutmanager

import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory
import org.jetbrains.anko.toast
import org.jetbrains.anko.longToast

/**
 * Created by keith on 11/22/17.
 */

class VerifyHostName {

    // Open SSLSocket directly to .com
    val hostUrl = "77.104.139.231"
    internal var sf = SSLSocketFactory.getDefault()
    //internal var socket = sf.createSocket("scoutmanager.struzzwebsolutions.com", 443) as SSLSocket
    internal var socket = sf.createSocket(hostUrl, 443) as SSLSocket
    internal var hv = HttpsURLConnection.getDefaultHostnameVerifier()
    internal var s = socket.session
    var success = false

    init {

        if (!hv.verify(hostUrl, s)) {
            throw SSLHandshakeException("Expected $hostUrl, found " + s.peerPrincipal)
        } else {
            success = true
        }
    }
}

    /*
    inner class AsyncTaskExample : AsyncTask<String, String, String>() {
        //var success = false

        override fun onPreExecute() {
            super.onPreExecute()
            //MyprogressBar.visibility = View.VISIBLE;
        }

        override fun doInBackground(vararg p0: String?): String {

            var Result = ""
            //It will return current data and time.
            val APIURL = "https://scoutmanager.struzzwebsolutions.com"
            var success = false

            try {

                val URL = URL(APIURL)
                val connect = URL.openConnection() as HttpURLConnection

                connect.readTimeout = 8000
                connect.connectTimeout = 8000
                connect.requestMethod = "POST"
                connect.doOutput = true
                connect.connect()

                val RESPONSECODE: Int = connect.responseCode;
                Log.d("tag", "ResponseCode" + RESPONSECODE)

                if (RESPONSECODE == 200) {
                    val tempStream: InputStream = connect.inputStream;
                    var sf = SSLSocketFactory.getDefault()
                    var socket = sf.createSocket("scoutmanager.struzzwebsolutions", 443) as SSLSocket
                    var hv = HttpsURLConnection.getDefaultHostnameVerifier()
                    var s = socket.session


                    if (!hv.verify("scoutmanager.struzzwebsolutions", s)) {
                        throw SSLHandshakeException("Expected scoutmanager.struzzwebsolutions, found " + s.peerPrincipal)
                    }

                    success = true
                }

            } catch(Ex: Exception) {
                Log.d("", "Error in doInBackground " + Ex.message)
            }
            return Result
        }


}

}// Verify that the certicate hostname is for mail.google.com
// This is due to lack of SNI support in the current SSLSocket.
// At this point SSLSocket performed certificate verificaiton and
// we have performed hostname verification, so it is safe to proceed.
// ... use socket ...

        */