package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.Fragments.ForumFragment
import com.example.myapplication.Fragments.HomeFragment
import com.example.myapplication.Fragments.ProfileFragment
import com.example.myapplication.Fragments.SettingFragment
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.()
    -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_main)
        supportFragmentManager.inTransaction {
            add(R.id.frameLayout, HomeFragment())
        }

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    supportFragmentManager.inTransaction {
                        replace(R.id.frameLayout, HomeFragment())
                    }
                    setContent("Home")
                    true
                }
                R.id.forumFragment -> {
                    supportFragmentManager.inTransaction {
                        replace(R.id.frameLayout, ForumFragment())
                    }
                    setContent("Forum")
                    true
                }
                R.id.settingFragment -> {
                    supportFragmentManager.inTransaction {
                        replace(R.id.frameLayout, SettingFragment())
                    }
                    setContent("Search")
                    true
                }
                R.id.profileFragment -> {
                    supportFragmentManager.inTransaction {
                        replace(R.id.frameLayout, ProfileFragment())
                    }
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