package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth

class RegistrationFragment : Fragment(R.layout.fragment_registration){
    @SuppressLint("MissingInflatedId")
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
        val checkboxMale : CheckBox = rootView.findViewById(R.id.checkboxMale)
        val checkboxFemale : CheckBox = rootView.findViewById(R.id.checkboxFemale)
        val editTextPersonName : EditText = rootView.findViewById(R.id.editTextPersonName)
        val editTextPersonSurname : EditText = rootView.findViewById(R.id.editTextPersonSurname)

        if(checkboxMale.isChecked){
            checkboxFemale.isChecked = false
        }else if(checkboxFemale.isChecked){
            checkboxMale.isChecked = false
        }

        textGoToRegister.setOnClickListener {
            val fragment = LoginFragment()
            (activity as MainActivity).replaceFragment(fragment)
        }

        buttonRegistration.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val personSurname = editTextPersonSurname.text.toString()
            val personName = editTextPersonName.text.toString()

            if(email.isEmpty() || password.isEmpty() || personSurname.isEmpty() || personName.isEmpty() ){
                Toast.makeText(activity, "Empty fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->

                    val sharedPreferences : SharedPreferences = activity?.applicationContext!!.getSharedPreferences("gmail", Context.MODE_PRIVATE)
                    val editor : SharedPreferences.Editor = sharedPreferences.edit()
                    editor.apply{
                        putString("userName", editTextPersonName.text.toString())
                        putString("userSurname", editTextPersonSurname.text.toString())
                        if (checkboxMale.isChecked){
                            putString("gender", checkboxMale.text.toString())
                        }else if(checkboxFemale.isChecked){
                            putString("gender", checkboxFemale.text.toString())
                        }
                    }.apply()

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
