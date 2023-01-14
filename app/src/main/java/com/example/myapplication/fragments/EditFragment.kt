package com.example.myapplication.fragments

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
import androidx.fragment.app.Fragment
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
        var btnRotate: Button = rootView.findViewById(R.id.btnRotate)
        var btnRotateByX:Button = rootView.findViewById(R.id.btnRotateByX)
        var btnRotateByY:Button = rootView.findViewById(R.id.btnRotateByY)
        var btnFadeIn:Button = rootView.findViewById(R.id.btnFadeIn)
        var btnFadeOut:Button = rootView.findViewById(R.id.btnFadeOut)
        var btnZoomIn: Button = rootView.findViewById(R.id.btnZoomIn)
        var btnZoomOut: Button = rootView.findViewById(R.id.btnZoomOut)


// Zoom in/out
        btnZoomIn.setOnClickListener{
            editImage.startAnimation(AnimationUtils.loadAnimation(
                context,
                R.anim.zoom_in
            ))
        }

        btnZoomOut.setOnClickListener{
            editImage.startAnimation(AnimationUtils.loadAnimation(
                context,
                R.anim.zoom_out
            ))
        }


// Fade in/out
        btnFadeIn.setOnClickListener{
                editImage.startAnimation(AnimationUtils.loadAnimation(
                    context,
                    R.anim.fade_in
                ))
        }
        btnFadeOut.setOnClickListener{
            editImage.startAnimation(AnimationUtils.loadAnimation(
                context,
                R.anim.fade_out
            ))
        }

// 3D rotation
        btnRotateByY.setOnClickListener{
            editImage.animate().apply {
                duration =1000
                rotationYBy(360f)
            }.start()
        }
        btnRotateByX.setOnClickListener{
            editImage.animate().apply {
                duration =1000
                rotationXBy(360f)
            }.start()
        }

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
