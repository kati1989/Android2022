package com.example.quizapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

class ContactAdapter(private val context: Context, private val arrayList: java.util.ArrayList<String>,
                     var onContactListener: OnContactListener) : BaseAdapter() {

    private lateinit var firstline: TextView
    private lateinit var secondline: TextView

    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false)
        firstline = convertView.findViewById(R.id.firstLine)
        secondline = convertView.findViewById(R.id.secondLine)

        firstline.text =  arrayList[position]
        secondline.text ="Mobile: "+ arrayList[position]

        convertView.setOnClickListener(View.OnClickListener {
                onContactListener.onContactClick(position);
        })

        return convertView
    }


    interface OnContactListener {
        fun onContactClick(position: Int)
    }
}


