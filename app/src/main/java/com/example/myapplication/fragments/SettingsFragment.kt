package com.example.myapplication.fragments

import android.app.AlertDialog
import android.content.DialogInterface
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
import de.hdodenhof.circleimageview.CircleImageView

class SettingsFragment : Fragment(R.layout.fragment_settings), DialogInterface.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        val backBtnEdit : Button = rootView.findViewById(R.id.backBtnSettings)
        val buttonLogout: Button = rootView.findViewById(R.id.buttonLogout)
        val rootView2 = LayoutInflater.from(activity).inflate(R.layout.fragment_profile,container, false)
        var gmailTextView: TextView = rootView2.findViewById(R.id.gmailTextView)
        var uidTextView: TextView = rootView.findViewById(R.id.uidTextView)
        var textViewPost: TextView = rootView2.findViewById(R.id.textViewPost)
        var textViewFollowers: TextView = rootView2.findViewById(R.id.textViewFollowers)
        var textViewFollowing: TextView = rootView2.findViewById(R.id.textViewFollowing)
        var personName: TextView = rootView2.findViewById(R.id.personName)
        var personSurname: TextView = rootView2.findViewById(R.id.personSurname)
        var imageViewProfilePicture: CircleImageView = rootView2.findViewById(R.id.imageViewProfilePicture)

        val buttonSetNewPassword: Button = rootView.findViewById(R.id.buttonSetNewPassword)
        val editTextNewPassword: EditText = rootView.findViewById(R.id.editTextNewPassword)

        uidTextView.text = FirebaseAuth.getInstance().currentUser?.uid.toString()
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

        buttonLogout.setOnClickListener {
            AlertDialog.Builder(activity)
                .setTitle("Logout")
                .setMessage("Are you sure about logging out?")
                .setPositiveButton("yes", this)
                .setNegativeButton("No", this)
                .show()
                .setCanceledOnTouchOutside(false) }

        backBtnEdit.setOnClickListener {
            val intent = Intent(activity, MainActivity()::class.java)
            activity?.startActivity(intent)
        }
        return rootView
    }
    override fun onClick(dialog: DialogInterface?, position: Int) {
        val rootView = layoutInflater.inflate(R.layout.fragment_profile, null)
        val rootView2 = layoutInflater.inflate(R.layout.fragment_settings, null)
        var gmailTextView: TextView = rootView.findViewById(R.id.gmailTextView)
        var uidTextView: TextView = rootView2.findViewById(R.id.uidTextView)
        var textViewPost: TextView = rootView.findViewById(R.id.textViewPost)
        var textViewFollowers: TextView = rootView.findViewById(R.id.textViewFollowers)
        var textViewFollowing: TextView = rootView.findViewById(R.id.textViewFollowing)
        var personName: TextView = rootView.findViewById(R.id.personName)
        var personSurname: TextView = rootView.findViewById(R.id.personSurname)
        var imageViewProfilePicture: CircleImageView = rootView.findViewById(R.id.imageViewProfilePicture)
        if (position == DialogInterface.BUTTON_POSITIVE){
            uidTextView.text = FirebaseAuth.getInstance().currentUser?.uid
            textViewPost.text = "0"
            textViewFollowers.text = "0"
            textViewFollowing.text = "0"
            personName.text = "TextView"
            personSurname.text = "TextView"
            gmailTextView.text = "TextView"
            uidTextView.text = ""
            FirebaseAuth.getInstance().signOut()
            val fragment = LoginFragment()
            (activity as MainActivity).replaceFragment(fragment)
        }
        dialog?.dismiss()
    }
}


