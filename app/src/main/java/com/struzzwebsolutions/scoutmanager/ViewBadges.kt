package com.struzzwebsolutions.scoutmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.jetbrains.anko.longToast
import org.json.JSONException
import org.json.JSONObject

class ViewBadges : AppCompatActivity() {

    private var listView: ListView? = null
    private var badgeList: MutableList<Badge>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_badges)

        listView = findViewById(R.id.lvViewBadges)
        badgeList = mutableListOf()

        val stringRequest = StringRequest(Request.Method.GET,
                EndPoints.URL_GET_BADGE,
                Response.Listener<String> { string   ->
                    try {
                        val obj = JSONObject(string)
                        if (!obj.getBoolean("error")) {
                            val array = obj.getJSONArray(("badges"))

                            for (i in 0..array.length() - 1) {
                                val objectBadge = array.getJSONObject(i)

                                val badge = Badge(
                                        objectBadge.getInt("BADGE_ID"),
                                        objectBadge.getString("BADGE_NAME"),
                                        objectBadge.getString("BADGE_DESC"),
                                        objectBadge.getString("SCOUT_RANK"),
                                        objectBadge.getString("REQUIREMENT_TYPE")
                                )
                                badgeList!!.add(badge)
                                val adapter = BadgeList(this@ViewBadges, badgeList!!)
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

        /* TODO Add onClick to new view showing badge details/requirements
        findViewById<LinearLayout>(R.id.llLayoutListBadge).setOnClickListener {

        }
        */
    }
}
