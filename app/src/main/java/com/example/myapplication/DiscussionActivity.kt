package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapters.CommentAdapter
import com.example.myapplication.Model.UserData
import com.example.myapplication.databinding.ActivityAddDiscussionBinding
import com.example.myapplication.databinding.ActivityDiscussionBinding
import com.example.myapplication.databinding.ActivityMainBinding

class DiscussionActivity : AppCompatActivity() {

    var listUsers = ArrayList<UserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDiscussionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listUsers.add(UserData("joko", "joko123", "joko1@joko.com"))
        listUsers.add(UserData("xah", "joko123", "joko1@joko.com"))
        listUsers.add(UserData("zz", "joko123", "joko1@joko.com"))

        binding.commentsRecyclerview.setHasFixedSize(true)
        binding.commentsRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CommentAdapter(listUsers)
        }
    }
}