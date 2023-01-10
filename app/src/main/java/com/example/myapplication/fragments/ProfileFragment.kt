package com.example.myapplication.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.MainActivity
import com.example.myapplication.R

class ProfileFragment : Fragment(R.layout.fragment_profile), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_profile, container, false)
        val button: Button = rootView.findViewById(R.id.settingsButton)
        var gmailTextView: TextView = rootView.findViewById(R.id.gmailTextView)
        val sharedPreferences : SharedPreferences = activity?.applicationContext!!.getSharedPreferences("gmail", Context.MODE_PRIVATE)
        var savedString : String? = sharedPreferences.getString("gmail",null)
        gmailTextView.text = savedString
        button.setOnClickListener(this)
        return rootView
    }
    override fun onClick(p0: View?) {
        val fragment = SettingsFragment()
        (activity as MainActivity).replaceFragment(fragment)
    }
}