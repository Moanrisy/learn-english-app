package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.Fragments.*
import com.example.myapplication.R
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

        var intentFragment = getIntent().getExtras()?.getString("frgToLoad")
        var postTitle = getIntent().getExtras()?.getString("title")
        var postContent = getIntent().getExtras()?.getString("content")


        when (intentFragment) {
            "forum" -> {
                val bundle = Bundle()
                bundle.putString("title", postTitle)
                bundle.putString("content", postContent)
                val forumFragment = ForumFragment()
                forumFragment.arguments = bundle

                binding.bottomNav.menu.getItem(1).setChecked(true)
                supportFragmentManager.inTransaction {
                    add(R.id.frameLayout, forumFragment)
                }
            }
            else -> {
                supportFragmentManager.inTransaction {
//                    add(R.id.frameLayout, HomeFragment())
                    add(R.id.frameLayout, MenuFragment())
                }
            }
        }


        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    supportFragmentManager.inTransaction {
//                        replace(R.id.frameLayout, HomeFragment())
                        replace(R.id.frameLayout, MenuFragment())
                    }
                    setContent("Home")
                    true
                }
                R.id.forumFragment -> {
                    supportFragmentManager.inTransaction {
                        replace(R.id.frameLayout, YoutubeFragment())
                    }
                    setContent("Youtube")
                    true
                }
//                R.id.forumFragment -> {
//                    supportFragmentManager.inTransaction {
//                        replace(R.id.frameLayout, ForumFragment())
//                    }
//                    setContent("Forum")
//                    true
//                }
                R.id.settingFragment -> {
                    supportFragmentManager.inTransaction {
                        replace(R.id.frameLayout, SettingFragment())
                    }
                    setContent("")
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