package com.example.week2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.week2.databinding.ActivitySignupBinding

class SignUp : AppCompatActivity() {
        private lateinit var binding: ActivitySignupBinding
        private lateinit var viewModel: SignUpViewModel
        private var account : Account = Account("Default fullName", "Default email", "Default password")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataStore.account = this.account

        binding   = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        binding.btnSignUp.setOnClickListener {
            binding.apply {
                viewModel.account.fullName = tvFullNameSignUp.text.toString().trim()
                viewModel.account.email    = tvEmailSignUp.text.toString().trim()
                viewModel.account.password = tvPasswordSignUp.text.toString().trim()
            }
            getDataToDataStore()
            Toast.makeText(this@SignUp, "SIGNUP SUCCESS!!!" , Toast.LENGTH_LONG).show()
            val intent = Intent(this@SignUp, SignIn::class.java)
            startActivity(intent)
        }
    }
    private fun getDataToDataStore()
    {
        DataStore.account.fullName = viewModel.account.fullName
        DataStore.account.email    = viewModel.account.email
        DataStore.account.password = viewModel.account.password
    }
}