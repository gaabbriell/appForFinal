package com.example.myapplication.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MainActivity
import com.example.myapplication.Post
import com.example.myapplication.PostRecyclerAdapter
import com.example.myapplication.R

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: PostRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val rootView : View = inflater.inflate(R.layout.fragment_home, container, false)
        val button : Button = rootView.findViewById(R.id.button)
        recyclerView = rootView.findViewById(R.id.recycleView)
        recyclerAdapter = PostRecyclerAdapter(getPersonData())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdapter
        button.setOnClickListener {
            val fragment = BlankFragment()
            (activity as MainActivity).replaceFragment(fragment)
        }
        return rootView
    }

    private fun getPersonData(): List<Post> {
        val sharedPreferences : SharedPreferences = activity?.applicationContext!!.getSharedPreferences("gmail", Context.MODE_PRIVATE)
        var savedImage: String? = sharedPreferences.getString("ImageUrl",null)
        var savedPost: String? = sharedPreferences.getString("PostText",null)
        var savedPosition: Int = sharedPreferences.getInt("PositionNumber",0)
        var post = ArrayList<Post>()
            post.add(
                Post(
                    imageUrl = savedImage,
                    post = savedPost
                )
            )
        return post
    }


}