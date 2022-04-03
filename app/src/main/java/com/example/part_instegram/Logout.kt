package com.example.part_instegram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.parse.ParseUser

class Logout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)
        findViewById<Button>(R.id.rvlogout).setOnClickListener {

            val currentUser = ParseUser.getCurrentUser()


            if(currentUser!=null){
                ParseUser.logOut()
                startActivity(Intent(this@Logout, LoginActivity::class.java))
                finish()
            } }
    }


}