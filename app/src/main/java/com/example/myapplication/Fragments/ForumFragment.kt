package com.example.myapplication.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.Adapters.PostAdapter
import com.example.myapplication.Model.UserData
import com.example.myapplication.R
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
    val listUsers = arrayOf(
        UserData("joko", "joko123", "joko1@joko.com"),
        UserData("jaka", "joko456", "joko2@joko.com"),
        UserData("jiki", "joko789", "joko3@joko.com")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentForumBinding.inflate(inflater, container, false)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = PostAdapter(listUsers)
        }
        return binding.root
    }
}