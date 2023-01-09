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

class RegistrationFragment : Fragment(R.layout.fragment_registration){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_registration, container, false)
        val buttonRegistration : Button = rootView.findViewById(R.id.buttonRegistration)
        val textGoToRegister : TextView = rootView.findViewById(R.id.textGoToRegister)
        val editTextEmail: EditText = rootView.findViewById(R.id.editTextEmail)
        val editTextPassword: EditText = rootView.findViewById(R.id.editTextPassword)
        textGoToRegister.setOnClickListener {
            val fragment = LoginFragment()
            (activity as MainActivity).replaceFragment(fragment)
        }
        buttonRegistration.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(activity, "Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val fragment = LoginFragment()
                        (activity as MainActivity).replaceFragment(fragment)
                    } else{
                        Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        return rootView
    }
}