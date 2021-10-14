package com.example.myapplication.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.UserData
import com.example.myapplication.databinding.ListPostBinding

class PostAdapter(private val list: Array<UserData>) :
    RecyclerView.Adapter<PostAdapter.Holder>() {

    private lateinit var postBinding: ListPostBinding

    class Holder(val binding: ListPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        postBinding = binding
        return Holder(binding)
//        return Holder(LayoutInflater.from(parent.context).inflate(binding))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder) {
            with(list[position]) {
                postBinding.disAuthor.text = list?.get(position)?.name
                postBinding.likeCount.text = position.toString()
                postBinding.commentCount.setOnClickListener {
                    postBinding.commentCount.text = "1"+position.toString()
                    Toast.makeText(itemView.context, "Like clicked"+position.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
//        holder.view.dis_author.text = list?.get(position)?.name
    }

    override fun getItemCount(): Int = list?.size
}
