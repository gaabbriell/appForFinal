package com.example.myapplication.fragments

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.NonCancellable.start


class EditFragment : Fragment(R.layout.fragment_edit) {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_edit, container, false)
        var editImage: ImageView = rootView.findViewById(R.id.editImage)
        var btnRotate: Button = rootView.findViewById(R.id.btnRotate)
        val sharedPreferences : SharedPreferences = activity?.applicationContext!!.getSharedPreferences("gmail", Context.MODE_PRIVATE)
        var savedGmail : String? = sharedPreferences.getString("gmail",null)


// 2D rotation
        var rotation = 90f
        btnRotate.setOnClickListener {

            if(rotation > 360f){
                rotation = 90f
                editImage.rotation = rotation
                rotation += 90f
            }else{
                editImage.rotation = rotation
                rotation += 90f
            }
        }
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