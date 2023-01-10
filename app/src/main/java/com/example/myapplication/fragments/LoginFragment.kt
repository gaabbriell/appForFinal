package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.example.myapplication.R.id.forgotPasswordText
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class LoginFragment : Fragment(R.layout.fragment_login){
    @SuppressLint("MissingInflatedId", "CommitPrefEdits")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)
        val editTextEmail : EditText = rootView.findViewById(R.id.editTextEmail)
        val editTextPassword : EditText = rootView.findViewById(R.id.editTextPassword)
        val buttonLogin : Button = rootView.findViewById(R.id.buttonLogin)
        val textGoToRegister : TextView = rootView.findViewById(R.id.textGoToRegister)
        val forgotPasswordText : TextView = rootView.findViewById(forgotPasswordText)
        val rootView2: View = LayoutInflater.from(activity).inflate(R.layout.fragment_profile,container,false)
        var gmailTextView: TextView = rootView2.findViewById(R.id.gmailTextView)

        textGoToRegister.setOnClickListener {
            val fragment = RegistrationFragment()
            (activity as MainActivity).replaceFragment(fragment)
        }
        forgotPasswordText.setOnClickListener {
            val fragment = ForgotPassword()
            (activity as MainActivity).replaceFragment(fragment)
        }

        if(FirebaseAuth.getInstance().currentUser != null && gmailTextView.text != "TextView"){
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }


        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(activity, "Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val sharedPreferences : SharedPreferences = activity?.applicationContext!!.getSharedPreferences("gmail", Context.MODE_PRIVATE)
                        val editor : SharedPreferences.Editor = sharedPreferences.edit()
                        editor.apply{
                            putString("gmail", editTextEmail.text.toString())
                        }.apply()
                        val intent = Intent(activity, MainActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                    }else{
                        Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show()
                    }

                }
        }

        return rootView
    }

}