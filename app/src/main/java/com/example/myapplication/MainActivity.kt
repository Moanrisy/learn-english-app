package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    setContent("Home")
                    true
                }
                R.id.forumFragment -> {
                    setContent("Forum")
                    true
                }
                R.id.settingFragment -> {
                    setContent("Search")
                    true
                }
                R.id.profileFragment -> {
                    setContent("Profile")
                    true
                }
                else -> false
            }
        }
    }

    private fun setContent(content: String) {
        setTitle(content)
        binding.tvLabel.text = content
    }
}