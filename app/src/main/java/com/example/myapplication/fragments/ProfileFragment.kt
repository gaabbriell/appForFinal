package com.example.myapplication.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import de.hdodenhof.circleimageview.CircleImageView
import org.w3c.dom.Text

class ProfileFragment : Fragment(R.layout.fragment_profile), View.OnClickListener {
    private lateinit var dbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_profile, container, false)
        val button: Button = rootView.findViewById(R.id.settingsButton)
        var gmailTextView: TextView = rootView.findViewById(R.id.gmailTextView)
        var textViewPost: TextView = rootView.findViewById(R.id.textViewPost)
        var textViewFollowers: TextView = rootView.findViewById(R.id.textViewFollowers)
        var textViewFollowing: TextView = rootView.findViewById(R.id.textViewFollowing)

        var personGender: TextView = rootView.findViewById(R.id.personGender)
        var personName: TextView = rootView.findViewById(R.id.personName)
        var personSurname: TextView = rootView.findViewById(R.id.personSurname)
        var imageViewProfilePicture: CircleImageView = rootView.findViewById(R.id.imageViewProfilePicture)
        val sharedPreferences : SharedPreferences = activity?.applicationContext!!.getSharedPreferences("gmail", Context.MODE_PRIVATE)
        var savedGmail : String? = sharedPreferences.getString("gmail",null)


        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        val mail = savedGmail?.replace(("[^\\w\\d ]").toRegex(), "")
        dbRef.child(mail.toString()).get().addOnSuccessListener {
            if(it.exists()){
                var userGender = it.child("userGender").value
                var userName = it.child("userName").value
                personName.text = userName.toString()
                var userSurname = it.child("userSurname").value
                personSurname.text = userSurname.toString()
                personGender.text = userGender.toString()
                if(personGender.text == "Male"){
                    imageViewProfilePicture.setImageResource(R.drawable.ic_baseline_man_24)
                }else if(personGender.text == "Female"){
                    imageViewProfilePicture.setImageResource(R.drawable.ic_baseline_woman_24)
                }
            }

        }

        var postCount = 0
        var followersCount = 0
        var followingCount = 0

        textViewPost.text = postCount.toString()
        textViewFollowers.text = followersCount.toString()
        textViewFollowing.text = followingCount.toString()

        gmailTextView.text = savedGmail


        button.setOnClickListener(this)
        return rootView
    }
    override fun onClick(p0: View?) {
        val fragment = SettingsFragment()
        (activity as MainActivity).replaceFragment(fragment)
    }
}