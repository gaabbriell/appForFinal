package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Post
import com.example.myapplication.PostRecyclerAdapter
import com.example.myapplication.R


class PostFragment : Fragment(R.layout.fragment_post) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: PostRecyclerAdapter
    companion object{
        val IMAGE_REQUEST_CODE = 100
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val rootView: View = inflater.inflate(R.layout.fragment_post, container, false)
        var buttonPublish: Button = rootView.findViewById(R.id.buttonPublish)
        var editTextImageUrl: EditText = rootView.findViewById(R.id.editTextImageUrl)
        var editTextPostText: EditText = rootView.findViewById(R.id.editTextPostText)
        buttonPublish.setOnClickListener {
            val sharedPreferences : SharedPreferences = activity?.applicationContext!!.getSharedPreferences("gmail", Context.MODE_PRIVATE)
            val editor : SharedPreferences.Editor = sharedPreferences.edit()
            var x = 0
            editor.apply{
                putString("ImageUrl", editTextImageUrl.text.toString())
                putString("PostText", editTextPostText.text.toString())
                putInt("PositionNumber", x)
            }.apply()
            editTextImageUrl.text = null
            editTextPostText.text = null
            x += 1
        }

        return rootView
    }
}