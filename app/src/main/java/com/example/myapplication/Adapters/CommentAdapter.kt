package com.example.myapplication.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.DiscussionActivity
import com.example.myapplication.Model.UserData
import com.example.myapplication.databinding.ListCommentBinding

class CommentAdapter (private val list: ArrayList<UserData>) : RecyclerView.Adapter<CommentAdapter.Holder>() {

    class Holder(val binding: ListCommentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.Holder {
        val binding = ListCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: CommentAdapter.Holder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.commentAuthor.text = list?.get(position)?.name
                binding.commentBody.text = list?.get(position).email
            }
        }
    }

    override fun getItemCount(): Int = list?.size
}