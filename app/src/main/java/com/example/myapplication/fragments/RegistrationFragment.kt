package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase

class RegistrationFragment : Fragment(R.layout.fragment_registration){
    @SuppressLint("MissingInflatedId")
    private lateinit var dbRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var database: DatabaseReference
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
            val mail = editTextEmail.text.toString()
            val changedMail = mail.replace(("[^\\w\\d ]").toRegex(), "")
            val password = editTextPassword.text.toString()
            val userSurname = editTextPersonSurname.text.toString()
            val userName = editTextPersonName.text.toString()
            val maleValue = "Male"
            val femaleValue = "Female"
            dbRef = FirebaseDatabase.getInstance().getReference("Users")
            if (checkboxFemale.isChecked && checkboxMale.isChecked) {
                Toast.makeText(activity, "Please choose one gender!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if (!checkboxFemale.isChecked && !checkboxMale.isChecked) {
                Toast.makeText(activity, "Please choose your gender!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(mail.isEmpty() || password.isEmpty() || userSurname.isEmpty() || userName.isEmpty() ){
                Toast.makeText(activity, "Empty fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else if(checkboxMale.isChecked){

                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail,password)
                            .addOnCompleteListener { task ->
                                if(task.isSuccessful){
                                    val userGender = maleValue
                                    val userId = dbRef.push().key!!
                                    val user = User(userId,userName,userSurname,userGender,mail,password)
                                    dbRef.child(changedMail).setValue(user)
                                        .addOnCompleteListener {
                                            Toast.makeText(activity, "Successfully registered", Toast.LENGTH_SHORT).show()
                                            val fragment = LoginFragment()
                                            (activity as MainActivity).replaceFragment(fragment)
                                        }.addOnFailureListener { error ->
                                            Toast.makeText(activity, "Error ${error.message}", Toast.LENGTH_SHORT).show()
                                        }
                                }else{
                                    Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show()
                                }
                            }

            }else{
                val userGender = femaleValue
                val userId = dbRef.push().key!!
                val user = User(userId,userName,userSurname,userGender,mail,password)
                dbRef.child(changedMail).setValue(user)
                    .addOnCompleteListener {
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail,password)
                            .addOnCompleteListener { task ->
                                if(task.isSuccessful){
                                    val fragment = LoginFragment()
                                    (activity as MainActivity).replaceFragment(fragment)
                                }else{
                                    Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show()
                                }
                            }
                        Toast.makeText(activity, "Successfully registered", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener { error ->
                        Toast.makeText(activity, "Error ${error.message}", Toast.LENGTH_SHORT).show()
                    }


            }

        }
        return rootView
    }

}
