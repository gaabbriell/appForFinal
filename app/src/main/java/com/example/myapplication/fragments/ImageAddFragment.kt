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
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.fragments.ImageAddFragment.Companion.IMAGE_REQUEST_CODE

class ImageAddFragment : Fragment(R.layout.fragment_image_add) {
    private lateinit var imageUri: Uri
    companion object{
        val IMAGE_REQUEST_CODE = 100
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_image_add, container, false)
        val backBtnEdit : Button = rootView.findViewById(R.id.backBtnEdit)
        val uploadImgBtn: Button = rootView.findViewById(R.id.uploadImgBtn)
        var addImage: ImageView = rootView.findViewById(R.id.addImage)
        backBtnEdit.setOnClickListener {
            val intent = Intent(activity, MainActivity()::class.java)
            activity?.startActivity(intent)
        }

        uploadImgBtn.setOnClickListener {
            openFileChooser()
        }

        return rootView
    }
    private fun openFileChooser() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PostFragment.IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val addImage: ImageView = requireView().findViewById(R.id.addImage)
        val rootView2 = layoutInflater.inflate(R.layout.fragment_edit,null)
        var editImage: ImageView = rootView2.findViewById(R.id.editImage)

        if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            addImage.setImageURI(imageUri)
            var sharedPreferences : SharedPreferences = activity?.applicationContext!!.getSharedPreferences("gmail", Context.MODE_PRIVATE)
            var editor : SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply{
                putString("imageUri", imageUri.toString())
            }.apply()
        } else super.onActivityResult(requestCode, resultCode, data)
    }
}


