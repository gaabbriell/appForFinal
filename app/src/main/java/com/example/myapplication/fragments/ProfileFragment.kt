package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.MainActivity
import com.example.myapplication.R

class ProfileFragment : Fragment(R.layout.fragment_profile), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_profile, container, false)
        val button: Button = rootView.findViewById(R.id.settingsButton)
        button.setOnClickListener(this)
        return rootView
    }
    override fun onClick(p0: View?) {
        val fragment = SettingsFragment()
        (activity as MainActivity).replaceFragment(fragment)
    }
}