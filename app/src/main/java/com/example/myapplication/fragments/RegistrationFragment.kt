package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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

        textGoToRegister.setOnClickListener {
            val fragment = LoginFragment()
            (activity as MainActivity).replaceFragment(fragment)
        }

        buttonRegistration.setOnClickListener {
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val personSurname = editTextPersonSurname.text.toString()
            val personName = editTextPersonName.text.toString()

            if (checkboxFemale.isChecked && checkboxMale.isChecked) {
                Toast.makeText(activity, "Please choose one gender!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if (!checkboxFemale.isChecked && !checkboxMale.isChecked) {
                Toast.makeText(activity, "Please choose gender!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(email.isEmpty() || password.isEmpty() || personSurname.isEmpty() || personName.isEmpty() ){
                Toast.makeText(activity, "Empty fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
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

        }
        return rootView
    }

}
