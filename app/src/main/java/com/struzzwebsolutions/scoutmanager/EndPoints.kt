package com.struzzwebsolutions.scoutmanager

/**
 * Created by keith on 11/20/17.
 */
object EndPoints {
    //private val URL_ROOT = "https://77.104.139.231/scoutmanager/db_scripts/index.php?op="
    private val URL_ROOT = "https://struzzwebsolutions.com/scoutmanager/db_scripts/index.php?op="
    val URL_ADD_SCOUT = URL_ROOT + "addscout&user=writer"
    val URL_GET_SCOUT = URL_ROOT + "getscouts&user=reader"
    val URL_GET_BADGE = URL_ROOT + "getbadges&user=reader"
    val DB_USER = "writer"
}