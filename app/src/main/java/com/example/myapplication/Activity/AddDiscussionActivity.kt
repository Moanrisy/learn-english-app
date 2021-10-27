package com.example.myapplication.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.Fragments.SettingFragment
import com.example.myapplication.databinding.ActivityAddDiscussionBinding

class AddDiscussionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddDiscussionBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_add_discussion)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val myFragment = SettingFragment()


        binding.postDiscussion.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("frgToLoad", "forum")
            i.putExtra("title", binding.disTitle.text.toString())
            i.putExtra("content", binding.disContent.text.toString())
            startActivity(i)

//            val bundle = Bundle()
//            bundle.putString("message", "titleeaa ala")
//            myFragment.arguments = bundle
//            fragmentTransaction.add(R.id.frameLayout, myFragment).commit()
        }
    }
}