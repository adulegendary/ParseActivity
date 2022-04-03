package com.example.part_instegram

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(ParseUser.getCurrentUser() != null){
            gotMainActivity()
        }

   findViewById<Button>(R.id.rvlogin).setOnClickListener{
       val username= findViewById<EditText>(R.id.UserNameId).text.toString()
        val password=findViewById<EditText>(R.id.passwordID).text.toString()
        loginUser(username,password)

    }
    findViewById<Button>(R.id.signUp).setOnClickListener{
        val username= findViewById<EditText>(R.id.UserNameId).text.toString()
        val password=findViewById<EditText>(R.id.passwordID).text.toString()
        signUp(username,password)
    }


    }
    private fun signUp(username: String, password: String) {
        val user = ParseUser()

// Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                gotMainActivity()
                Log.e(TAG,"On successfully")
                // Hooray! Let them use the app now.
            } else {
                Toast.makeText(this,"Well Error in SignUp",Toast.LENGTH_SHORT).show()
                e.printStackTrace()
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
            }
        }
    }

    private  fun loginUser(username:String,password:String){
        ParseUser.logInInBackground(username ,password, ({user,e->

                if (user != null) {
                    Log.i(TAG,"On Action",)
                    gotMainActivity()
                } else {
                    Log.i(TAG,"On Failure",)
                   e.printStackTrace()
                    Toast.makeText(this,"Error while login",Toast.LENGTH_LONG).show()
                }
            }))
    }

    private fun gotMainActivity(){
        val intent=Intent(this@LoginActivity,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
  companion object{
      const val TAG="LoginActivity"
  }

}
