package com.struzzwebsolutions.scoutmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONException
import org.json.JSONObject
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory

class ViewScouts : AppCompatActivity() {

    private var listView: ListView? = null
    private var scoutList: MutableList<Scout>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_scouts)

        listView = findViewById(R.id.lvViewScouts)
        scoutList = mutableListOf()
        //loadScouts()
        val stringRequest = StringRequest(Request.Method.GET,
                EndPoints.URL_GET_SCOUT,
                Response.Listener<String> { string ->
                    try {
                        val obj = JSONObject(string)
                        if (!obj.getBoolean("error")) {
                            //toast("YES")
                            val array = obj.getJSONArray(("scouts"))
                            //toast(array[0].toString())

                            for (i in 0..array.length() - 1) {
                                val objectScout = array.getJSONObject(i)
                                //toast(objectScout.getString("NAME_FIRST_CHILD"))
                                val scout = Scout(
                                        objectScout.getInt("SCOUT_ID"),
                                        objectScout.getString("NAME_FIRST_CHILD"),
                                        objectScout.getString("NAME_LAST_CHILD"),
                                        objectScout.getString("NAME_FIRST_PARENT_PRIMARY"),
                                        objectScout.getString("CONTACT_EMAIL_PARENT_PRIMARY"),
                                        objectScout.getString("NAME_FIRST_PARENT_SECONDARY"),
                                        objectScout.getString("CONTACT_EMAIL_PARENT_SECONDARY")
                                )
                                scoutList!!.add(scout)
                                val adapter = ScoutList(this@ViewScouts, scoutList!!)
                                listView!!.adapter = adapter
                            }
                        } else {
                            longToast(obj.getString("message"))
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, Response.ErrorListener { volleyError -> longToast(volleyError.message.toString()) })

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add<String>(stringRequest)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var actionsMenu: MenuInflater = menuInflater
        actionsMenu.inflate(R.menu.actions_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}