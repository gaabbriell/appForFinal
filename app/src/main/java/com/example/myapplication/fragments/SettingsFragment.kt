package com.example.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.myapplication.MainActivity
import com.example.myapplication.R

class SettingsFragment : Fragment(R.layout.fragment_settings), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        val backBtnEdit : Button = rootView.findViewById(R.id.backBtnSettings)
        backBtnEdit.setOnClickListener (this)
        return rootView
    }

    override fun onClick(p0: View?) {
        val intent = Intent(getActivity(), MainActivity()::class.java)
        getActivity()?.startActivity(intent)
    }


}

