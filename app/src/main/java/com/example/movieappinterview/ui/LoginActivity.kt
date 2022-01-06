package com.example.movieappinterview.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.movieappinterview.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

        private lateinit var firebaseAuth: FirebaseAuth


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        //  Checks if already logged in
        checkUser()

        //Goes to register.xml for create new account
        registerTextview.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            singIn()
        }
    }
        // singin into application
    private fun singIn() {

        val loginemail = login_email.text.toString().trim()
        val loginPassword = login_password.text.toString().trim()


        if(loginemail.isEmpty()){
            Toast.makeText(this,"E-mail can not be empty!", Toast.LENGTH_SHORT).show()
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(loginemail).matches()){
            Toast.makeText(this,"Invalid E-mail adress!", Toast.LENGTH_SHORT).show()

        }
        else if(loginPassword.isEmpty()){
            Toast.makeText(this,"Password can not be empty!", Toast.LENGTH_SHORT).show()
        }
        else{
            firebaseAuth.signInWithEmailAndPassword(loginemail,loginPassword).addOnSuccessListener {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener{
                Toast.makeText(this,"User didn't find!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkUser() {

        val firebase = firebaseAuth.currentUser
        if (firebase != null){
            Toast.makeText(this,"Welcome", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}