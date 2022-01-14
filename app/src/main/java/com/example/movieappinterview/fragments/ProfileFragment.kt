package com.example.movieappinterview.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.movieappinterview.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_movie_details.*
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

        // firebase instance connection
        database = FirebaseDatabase.getInstance().getReference("users")
        auth = FirebaseAuth.getInstance()
        // Current user id
        val uid = auth.currentUser?.uid
        val ordersRef = database.child("$uid")

        // Get contact data from Firebase and equals to ui components
        var getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                // Check user information is empty or not
                if (snapshot.exists()){
                    var email = snapshot.child("email").getValue()
                    emaill.setText(email.toString())
                    var username = snapshot.child("username").getValue()
                    usernamee.setText(username.toString())

                    var phone = snapshot.child("phone").getValue()
                    phonee.setText(phone.toString())
                    var url = snapshot.child("imageUrl").getValue()

                    // For profile picture
                    context?.let {
                        Glide.with(this@ProfileFragment)
                            .load(url)
                            .into(circle_image_profile)
                        user_profile_picture.visibility = View.INVISIBLE
                    }
                }else{


                    val builder = AlertDialog.Builder(requireContext())
                    builder.setPositiveButton("Okay"){_, _ ->
                    }
                    builder.setNegativeButton(""){_, _ ->}
                    builder.setTitle("Plaease add your contact information")
                    builder.setMessage("Click update button and add your profile informations")
                    builder.create().show()
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }
        }
        ordersRef.addValueEventListener(getData)
        ordersRef.addListenerForSingleValueEvent(getData)
    }
}