package com.example.myapplication.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.example.myapplication.R


class PostFragment : Fragment(R.layout.fragment_post) {
    companion object{
        val IMAGE_REQUEST_CODE = 100
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val rootView: View = inflater.inflate(R.layout.fragment_post, container, false)

        var ButtonChooseImage: Button = rootView.findViewById(R.id.button_choose_image)
        var postImage: ImageView = rootView.findViewById(R.id.postImage)
        ButtonChooseImage.setOnClickListener{
            openFileChooser()
        }

        return rootView
    }

    private fun openFileChooser() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val postImage: ImageView = requireView().findViewById(R.id.postImage)

        if (requestCode == IMAGE_REQUEST_CODE && resultCode == RESULT_OK)
            postImage.setImageURI(data?.data)
        else super.onActivityResult(requestCode, resultCode, data)
    }

}