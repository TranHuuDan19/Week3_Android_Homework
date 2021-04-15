package com.example.week2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.week2.databinding.ActivitySigninBinding

class SignIn: AppCompatActivity() {
        private lateinit var binding : ActivitySigninBinding
        private lateinit var viewModel: SignInViewModel
        private var account : Account = Account("Default fullName", "default email", "Default password")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_signin)
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        binding.account = viewModel.account

        binding.apply {
            btnSignIn.setOnClickListener {
                viewModel.account.email    = tvEmailSignIn.text.toString().trim()
                viewModel.account.password = tvPassSignIn.text.toString().trim()

                if (viewModel.account?.email.compareTo(DataStore.account.email.trim()) == 0 && viewModel.account.password?.compareTo( DataStore.account.password.trim()) == 0 ) {
                    Toast.makeText(this@SignIn, "LOGIN SUCCESS!!!", Toast.LENGTH_LONG).show()
                    moveDataToProfile()
                }
                else
                    Toast.makeText(this@SignIn, "LOGIN FAILED", Toast.LENGTH_LONG).show()
                invalidateAll()
            }
        }
    }
    private fun moveDataToProfile()
    {
        val intent = Intent(this@SignIn, Profile::class.java)
        val bundle = Bundle()
        val userInformation = UserInformationData(email = viewModel.account.email, fullName = DataStore.account.fullName.trim(), phoneNumber = "123456789")
        bundle.putParcelable("userInformation", userInformation)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}