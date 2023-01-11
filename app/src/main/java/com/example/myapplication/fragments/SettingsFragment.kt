package com.example.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

        val buttonSetNewPassword: Button = rootView.findViewById(R.id.buttonSetNewPassword)
        val editTextNewPassword: EditText = rootView.findViewById(R.id.editTextNewPassword)


        buttonSetNewPassword.setOnClickListener{
            val newPassword = editTextNewPassword.text.toString()
            if (newPassword.length >= 8){
                FirebaseAuth.getInstance().currentUser?.updatePassword(newPassword)
                    ?.addOnCompleteListener{task ->
                        if(task.isSuccessful){
                            Toast.makeText(activity, "Success!", Toast.LENGTH_SHORT).show()
                        }else {
                            Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }



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

