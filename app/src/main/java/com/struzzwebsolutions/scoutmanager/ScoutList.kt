package com.struzzwebsolutions.scoutmanager

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.view.*
import java.text.FieldPosition
import org.jetbrains.anko.toast

/**
 * Created by keith on 12/3/17.
 */
class ScoutList (private val context: Activity, internal var scouts: List<Scout>): ArrayAdapter<Scout>(context, R.layout.layout_list_scout, scouts) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_scout, null, true)

        val tvCount = listViewItem.findViewById<TextView>(R.id.tvCount)
        val tvFirstName = listViewItem.findViewById<TextView>(R.id.tvScoutNameFirst)
        val tvLastName = listViewItem.findViewById<TextView>(R.id.tvScoutNameLast)
        val tvFirstNameParentPrimary = listViewItem.findViewById<TextView>(R.id.tvPrimaryParentValue)
        val tvEmailParentPrimary = listViewItem.findViewById<TextView>(R.id.tvPrimaryParentEmail)
        val tvFirstNameParentSecondary = listViewItem.findViewById<TextView>(R.id.tvSecondaryParentValue)
        val tvEmailParentSecondary = listViewItem.findViewById<TextView>(R.id.tvSecondaryParentEmail)

        val scout = scouts[position]
        tvCount.text = (position + 1).toString() + "."
        tvFirstName.text = scout.first_name
        tvLastName.text = scout.last_name
        if (scout.parent_name_primary.isNullOrBlank() || scout.parent_name_primary=="null") {
            tvFirstNameParentPrimary.text = "(?)"
        }else{
            tvFirstNameParentPrimary.text = scout.parent_name_primary
        }

        tvEmailParentPrimary.text = scout.parent_email_primary ?: "(?)"
        tvFirstNameParentSecondary.text = scout.parent_name_secondary ?: "(?)"
        tvEmailParentSecondary.text = scout.parent_email_secondary ?: "(?)"


        return listViewItem
    }
}

