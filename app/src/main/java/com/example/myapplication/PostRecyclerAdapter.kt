package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostRecyclerAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostRecyclerAdapter.PostViewHolder>() {
    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        private val textViewPost = itemView.findViewById<TextView>(R.id.textViewPost)

        fun setData(post: Post) {


            Glide
                .with(itemView)
                .load(post.imageUrl)
                .into(imageView)
            textViewPost.text = post.post
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_post_with_image, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.setData(posts[position])
    }

    override fun getItemCount() = posts.size
}