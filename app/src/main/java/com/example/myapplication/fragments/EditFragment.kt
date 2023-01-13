package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.findFragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R


class EditFragment : Fragment(R.layout.fragment_edit) {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_edit, container, false)
        var editImage: ImageView = rootView.findViewById(R.id.editImage)

        return rootView
    }

    override fun onResume() {
        super.onResume()
        var editImage: ImageView = requireView().findViewById(R.id.editImage)
        var sharedPreferences : SharedPreferences = activity?.applicationContext!!.getSharedPreferences("gmail", Context.MODE_PRIVATE)
        var savedImageUri : String? = sharedPreferences.getString("imageUri",null)
        var myUri: Uri = Uri.parse(savedImageUri)
        editImage.setImageURI(myUri)
    }


}