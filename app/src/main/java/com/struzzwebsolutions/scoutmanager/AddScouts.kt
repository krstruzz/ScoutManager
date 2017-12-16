package com.struzzwebsolutions.scoutmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import java.util.*
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.jetbrains.anko.longToast
import org.json.JSONException
import org.json.JSONObject


class AddScouts : AppCompatActivity() {

    //edittext and spinner
    private var etScoutFirstName: EditText? = null
    private var etScoutLastName: EditText? = null
    //private var spinnerPack: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_scouts)

        //getting it from xml
        etScoutFirstName = findViewById(R.id.etNameFirstScout)
        etScoutLastName = findViewById(R.id.etNameLastScout)
        //spinnerPack = findViewById(R.id.spinnerPack)

        //adding a click listener to button
        findViewById<Button>(R.id.buAddScout).setOnClickListener { clickAddScout() }
        findViewById<Button>(R.id.buCancel).setOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var actionsMenu: MenuInflater = menuInflater
        actionsMenu.inflate(R.menu.actions_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //adding a new record to database
    private fun clickAddScout() {
        //getting the record values
        val first_name = etScoutFirstName?.text.toString()
        val last_name = etScoutLastName?.text.toString()
        //val pack = spinnerPack?.selectedItem.toString()

        val stringRequest = object : StringRequest(Request.Method.POST, EndPoints.URL_ADD_SCOUT,
                Response.Listener<String> { response ->
                    try {
                        val obj = JSONObject(response)
                        longToast(obj.getString("message"))
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { volleyError -> longToast(volleyError.message.toString()) }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("NAME_FIRST_CHILD", first_name)
                params.put("NAME_LAST_CHILD", last_name)
                return params
            }
        }
        VolleySingleton.instance?.addToRequestQueue(stringRequest)
    }
}

