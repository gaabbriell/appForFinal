package com.example.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth

class SettingsFragment : Fragment(R.layout.fragment_settings), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        val backBtnEdit : Button = rootView.findViewById(R.id.backBtnSettings)
        val buttonLogout: Button = rootView.findViewById(R.id.buttonLogout)
        val rootView2 = LayoutInflater.from(activity).inflate(R.layout.fragment_profile,null)
        var gmailTextView: TextView = rootView2.findViewById(R.id.gmailTextView)
        var uidTextView: TextView = rootView.findViewById(R.id.uidTextView)

        uidTextView.text = FirebaseAuth.getInstance().currentUser?.uid

        buttonLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            gmailTextView.text = "TextView"
            uidTextView.text = ""
            val fragment = LoginFragment()
            (activity as MainActivity).replaceFragment(fragment)
        }

        backBtnEdit.setOnClickListener (this)
        return rootView
    }

    override fun onClick(p0: View?) {
        val intent = Intent(getActivity(), MainActivity()::class.java)
        getActivity()?.startActivity(intent)
    }


}

