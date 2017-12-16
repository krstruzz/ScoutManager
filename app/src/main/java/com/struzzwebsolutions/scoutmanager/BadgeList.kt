package com.struzzwebsolutions.scoutmanager

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.struzzwebsolutions.scoutmanager.R.id.left

/**
 * Created by keith on 12/15/17.
 */
class BadgeList (private val context: Activity, internal var badges: List<Badge>): ArrayAdapter<Badge>(context, R.layout.layout_list_badge, badges) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.layout_list_badge, null, true)

        val tvBadge = listViewItem.findViewById<TextView>(R.id.tvBadge)
        val imgBadge = listViewItem.findViewById<ImageView>(R.id.imgBadge)

        val badge = badges[position]
        tvBadge.text = badge.badge_name
        // set image view to appropriate badge image
        when (badge.badge_name) {
            "My Tiger Jungle" -> imgBadge.setImageResource(R.mipmap.img_badge_backyardjungle)
            "Tiger Circles: Duty to God" -> imgBadge.setImageResource(R.mipmap.img_badge_dutytogod)
            "Games Tigers Play" -> imgBadge.setImageResource(R.mipmap.img_badge_gamestigersplay)
            "Team Tiger" -> imgBadge.setImageResource(R.mipmap.img_badge_teamtiger)
            "Tiger Bites" -> imgBadge.setImageResource(R.mipmap.img_badge_tigerbites)
            "Tigers in the Wild" -> imgBadge.setImageResource(R.mipmap.img_badge_tigersinthewild)
        }

        return listViewItem
    }
}