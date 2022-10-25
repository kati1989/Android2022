package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChoseContactActivity : AppCompatActivity(), ContactAdapter.OnContactListener {

    lateinit var listView: ListView
    lateinit var contactList : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chose_contact)

        listView = findViewById(R.id.contact_list_view)
        contactList = arrayListOf<String>( "2342424","2342342","324234234","234234234",
            "2342424","2342342","324234234","234234234")
        var conAdapter: ContactAdapter = ContactAdapter(this, contactList,this)
        listView.adapter = conAdapter;
    }

    override fun onContactClick(position: Int) {
        val intent= Intent(this,DisplayMessageActivity::class.java).apply{
            putExtra("username",contactList.get(position))
        }
        startActivity(intent)    }
}