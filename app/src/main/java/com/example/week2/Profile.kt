package com.example.week2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val textViewEmail       = findViewById<TextView>(R.id.tvEmailProfile)
        val textViewFullName    = findViewById<TextView>(R.id.tvFullNameProfile)
        val textViewPhoneNumber = findViewById<TextView>(R.id.tvPhoneProfile)
        val bundle = intent.extras

        bundle?.let {
            val userInformation      = bundle.getParcelable<UserInformationData>("userInformation")
            textViewEmail.text       = String.format("%s", userInformation?.email)
            textViewFullName.text    = String.format("%s", userInformation?.fullName)
            textViewPhoneNumber.text = String.format("%s", userInformation?.phoneNumber)
        }
        dialogProfileF(textViewFullName,    "FullName")
        dialogProfileF(textViewPhoneNumber, "Phone Number")
        dialogProfileF(textViewEmail,       "Email")
    }

    fun dialogProfileF(TextviewDPF: TextView, title: String) {
        TextviewDPF.setOnClickListener {
            val builder         = AlertDialog.Builder(this)
            val inflater        = layoutInflater
            val dialogLayout    = inflater.inflate(R.layout.dialog_profile, null)
            val editTextProfile = dialogLayout.findViewById<TextView>(R.id.edit_userInformation)
            with(builder) {
                setTitle(title)
                setPositiveButton("Submit")
                { dialog, _ ->
                    TextviewDPF.text = editTextProfile.text.toString()
                    Toast.makeText(this@Profile, "Successful!!!", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                setNegativeButton("Cancel")
                { dialog, _ ->
                    Toast.makeText(this@Profile, "GoodBye", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                setView(dialogLayout)
                show()
            }
        }
    }
}