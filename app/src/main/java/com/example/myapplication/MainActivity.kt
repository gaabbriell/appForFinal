package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.fragments.LoginFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottomNavView)
        navController = findNavController(R.id.nav_host_fragment)

        bottomNavigationView.setupWithNavController(navController)

        val intent = Intent(this, LoginFragment::class.java)
        startActivity(intent)
        finish()


//        var user = FirebaseAuth.getInstance().currentUser
//        val intent = Intent(this, MainActivity::class.java)
//
//            if(user != null) {
//                startActivity(intent)
//                finish()
//            }else{
//                val intent = Intent(this, LoginFragment::class.java)
//                startActivity(intent)
//                finish()
//
//            }
        }
}
