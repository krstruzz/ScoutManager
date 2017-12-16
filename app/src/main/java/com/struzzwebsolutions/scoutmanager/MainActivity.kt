package com.struzzwebsolutions.scoutmanager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.*


/* TODO Features
    1. Add edit scout
    2. Add delete scout
    3. Add export scout
    4. Add parent info and associate with scout
    5. Add calendar and events
 */


class MainActivity : AppCompatActivity() {

    lateinit var tvPackNumValue: TextView
    var packNum = 22
    //lateinit var actionsMenu: MenuInflater
    //lateinit var ibAddScout: ImageButton
    //lateinit var ibViewScouts: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_add_scouts)
        setContentView(R.layout.activity_main)

        //getting it from xml
        tvPackNumValue = findViewById(R.id.tvPackNumValue)
        tvPackNumValue.text = packNum.toString()

        //adding a click listener to button
        findViewById<ImageButton>(R.id.ibAddScout).setOnClickListener {
            val intent = Intent(applicationContext, AddScouts::class.java)
            startActivity(intent)
             }

        findViewById<ImageButton>(R.id.ibViewScouts).setOnClickListener {
            val intent = Intent(applicationContext, ViewScouts::class.java)
            startActivity(intent)
        }

        findViewById<ImageButton>(R.id.ibBadges).setOnClickListener {
            val intent = Intent(applicationContext, ViewBadges::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var actionsMenu: MenuInflater = menuInflater
        actionsMenu.inflate(R.menu.actions_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

}

