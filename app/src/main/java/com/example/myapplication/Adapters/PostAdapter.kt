package com.example.myapplication.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.AddDiscussionActivity
import com.example.myapplication.DiscussionActivity
import com.example.myapplication.Model.UserData
import com.example.myapplication.databinding.ListPostBinding

class PostAdapter(private val list: ArrayList<UserData>) :
    RecyclerView.Adapter<PostAdapter.Holder>() {

    class Holder(val binding: ListPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.disAuthor.text = list?.get(position)?.name
                binding.likeCount.text = position.toString()
                binding.likeIcon.setOnClickListener {
                    binding.likeCount.text = "1"+position.toString()
                    Toast.makeText(itemView.context, "Like clicked"+position.toString(), Toast.LENGTH_SHORT).show()
                }

                binding.commentIcon.setOnClickListener {
                    itemView.context.startActivity(Intent(itemView.context, DiscussionActivity::class.java))
                }
            }
        }
//        holder.view.dis_author.text = list?.get(position)?.name
    }

    override fun getItemCount(): Int = list?.size
}
