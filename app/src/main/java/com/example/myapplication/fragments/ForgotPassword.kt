package com.example.myapplication.fragments

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

class ForgotPassword : Fragment(R.layout.fragment_forgot_password) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_forgot_password, container, false)
        val buttonSendOnEmail: Button = rootView.findViewById(R.id.buttonSendOnEmail)
        val editTextEmail: EditText = rootView.findViewById(R.id.editTextEmail)
        val textGoToLogIn: TextView = rootView.findViewById(R.id.textGoToLogIn)

        textGoToLogIn.setOnClickListener {
            val fragment = LoginFragment()
            (activity as MainActivity).replaceFragment(fragment)
        }

        buttonSendOnEmail.setOnClickListener{
            val email = editTextEmail.text.toString()

            if(email.isNotEmpty()){
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if(task.isSuccessful){
                            Toast.makeText(activity, "Check E-mail", Toast.LENGTH_LONG).show()
                            val fragment = LoginFragment()
                            (activity as MainActivity).replaceFragment(fragment)
                        }else{
                            Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(activity, "Empty!", Toast.LENGTH_SHORT).show()
            }
        }
        return rootView
    }
}