package com.example.myapplication.Fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.TypedArray
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.Tasks.TapPairTask.TapPairActivity
import com.example.myapplication.Tasks.TranslateSentenceTask.TSTaskActivity
import com.example.myapplication.Tasks.WordTask.WordTaskActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.FragmentHomeBinding

// TODO: Rename parameter arguments, choose names that match
// This is home fragment
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false)

        val sharedPref: SharedPreferences? = context?.getSharedPreferences("app", Context.MODE_PRIVATE)
        val editor = sharedPref?.edit()

        binding.perkenalan.setOnClickListener(){
//            Toast.makeText(activity, "new day", Toast.LENGTH_SHORT).show()
//            binding.basicBar.setProgress(20F)
//            binding.basicBar.backgroundProgressColor = ContextCompat.getColor(requireContext().applicationContext, R.color.blue_background)

            editor?.putString("chapter", "11")?.apply()
            val intent = Intent(this.context, TapPairActivity::class.java)
            startActivity(intent)
        }

        binding.salam.setOnClickListener {
            editor?.putString("chapter", "11")?.apply()
            val intent = Intent(this.context, WordTaskActivity::class.java)
            startActivity(intent)
        }

        binding.angka.setOnClickListener {
            editor?.putString("chapter", "11")?.apply()
            val intent = Intent(this.context, TSTaskActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}