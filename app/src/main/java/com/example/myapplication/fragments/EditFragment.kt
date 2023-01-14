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
import androidx.fragment.app.Fragment
import androidx.lifecycle.repeatOnLifecycle
import com.example.myapplication.R
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
        var btnRotateByX:Button = rootView.findViewById(R.id.btnRotateByX)
        var btnRotateByY:Button = rootView.findViewById(R.id.btnRotateByY)
        var btnScaleX: Button = rootView.findViewById(R.id.btnScaleX)
        var btnScaleY: Button = rootView.findViewById(R.id.btnScaleY)


        btnScaleX.setOnClickListener{
            val animations = arrayOf(4f).map { translation ->
                ObjectAnimator.ofFloat(editImage, "scaleX", translation).apply {
                    duration = 800
                    repeatCount = ObjectAnimator.INFINITE
                    repeatMode = ObjectAnimator.RESTART
                }
            }
            val set = AnimatorSet()
            set.playTogether(animations)
            set.start()
        }


        btnScaleY.setOnClickListener{
            val animations = arrayOf(4f).map { translation ->
                ObjectAnimator.ofFloat(editImage, "scaleY", translation).apply {
                    duration = 800
                    repeatCount = ObjectAnimator.INFINITE
                    repeatMode = ObjectAnimator.RESTART
                }
            }
            val set = AnimatorSet()
            set.playTogether(animations)
            set.start()
        }


// 3D rotation
        btnRotateByY.setOnClickListener{
            val animations = arrayOf(360f).map { translation ->
                ObjectAnimator.ofFloat(editImage, "rotationY", translation).apply {
                    duration = 1000
                    repeatCount = ObjectAnimator.INFINITE
                    repeatMode = ObjectAnimator.RESTART
                }
            }
            val set = AnimatorSet()
            set.playTogether(animations)
            set.start()
        }
        btnRotateByX.setOnClickListener{
            val animations = arrayOf(360f).map { translation ->
                ObjectAnimator.ofFloat(editImage, "rotationX", translation).apply {
                    duration = 1000
                    repeatCount = ObjectAnimator.INFINITE
                    repeatMode = ObjectAnimator.RESTART
                }
            }
            val set = AnimatorSet()
            set.playTogether(animations)
            set.start()
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