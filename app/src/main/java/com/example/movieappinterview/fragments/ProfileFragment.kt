package com.example.movieappinterview.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.movieappinterview.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_profile.*

private lateinit var database: DatabaseReference
private lateinit var auth : FirebaseAuth

class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Goes to UpdateProfileFragment
        updateUser.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToUpdateProfileFragment()
            Navigation.findNavController(it).navigate(action)
        }

        database = FirebaseDatabase.getInstance().getReference("users")
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        val ordersRef = database.child("$uid")

        var getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                var username = snapshot.child("username").getValue()
                usernamee.setText(username.toString())
                var email = snapshot.child("email").getValue()
                emaill.setText(email.toString())
                var phone = snapshot.child("phone").getValue()
                phonee.setText(phone.toString())


                usernameee.setText(username.toString())
            }
            override fun onCancelled(error: DatabaseError) {
            }
        }
        ordersRef.addValueEventListener(getData)
        ordersRef.addListenerForSingleValueEvent(getData)



    }
}