package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R

class HomeFragment : Fragment(R.layout.fragment_home), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val rootView : View = inflater.inflate(R.layout.fragment_home, container, false)
        val button : Button = rootView.findViewById(R.id.button)
        button.setOnClickListener(this)
        return rootView
    }

    override fun onClick(p0: View?) {
        val fragment = BlankFragment()
        (activity as MainActivity).replaceFragment(fragment)
    }


}