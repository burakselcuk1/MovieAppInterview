package com.example.movieappinterview.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.movieappinterview.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*

private lateinit var firebaseAuth: FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firebaseAuth = FirebaseAuth.getInstance()

        //Create new User
        register_button.setOnClickListener {
            val registerEmail = registerEmail.text.toString().trim()
            val registerPassword = registerPassword.text.toString().trim()


            if(registerEmail.isEmpty()){
                Toast.makeText(this,"E-mail can not be empty!", Toast.LENGTH_SHORT).show()
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(registerEmail).matches()){
                Toast.makeText(this,"Invalid E-mail adress!", Toast.LENGTH_SHORT).show()

            }
            else if(registerPassword.isEmpty()){
                Toast.makeText(this,"Password can not be empty!", Toast.LENGTH_SHORT).show()
            }
            else{
                firebaseAuth.createUserWithEmailAndPassword(registerEmail,registerPassword).addOnSuccessListener {
                    Toast.makeText(this,"User Created!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                }.addOnFailureListener {
                    Toast.makeText(this,"Error!", Toast.LENGTH_SHORT).show()

                }

        }



    }
}
}