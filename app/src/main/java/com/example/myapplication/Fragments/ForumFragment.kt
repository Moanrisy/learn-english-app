package com.example.myapplication.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Adapters.PostAdapter
import com.example.myapplication.AddDiscussionActivity
import com.example.myapplication.Model.UserData
import com.example.myapplication.databinding.FragmentForumBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ForumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForumFragment : Fragment() {

    private lateinit var adapter: PostAdapter
    var listUsers = ArrayList<UserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Toast.makeText(activity, this.arguments?.getString("title"), Toast.LENGTH_SHORT).show()
        listUsers.add(UserData("joko", "joko123", "joko1@joko.com"))
        listUsers.add(UserData("xah", "joko123", "joko1@joko.com"))
        listUsers.add(UserData("zz", "joko123", "joko1@joko.com"))

        val title = this.arguments?.getString("title")
        val content = this.arguments?.getString("content")

        if (title != null) {
            listUsers.add(UserData(title, content, "j"))
        }

        val binding = FragmentForumBinding.inflate(inflater, container, false)
        binding.fab.setOnClickListener {
            startActivity(Intent(activity, AddDiscussionActivity::class.java))

        }
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = PostAdapter(listUsers)
        }
        return binding.root
    }
}