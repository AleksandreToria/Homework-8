package com.example.homework8

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework8.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val email = intent.getStringExtra("email")
        val position = intent.getIntExtra("position", -1)

        binding.etFirstName.setText(firstName)
        binding.etLastName.setText(lastName)
        binding.etEmail.setText(email)

        binding.updateItem.setOnClickListener {
            val updatedFirstName = binding.etFirstName.text.toString()
            val updatedLastName = binding.etLastName.text.toString()
            val updatedEmail = binding.etEmail.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra("position", position)
            resultIntent.putExtra("firstName", updatedFirstName)
            resultIntent.putExtra("lastName", updatedLastName)
            resultIntent.putExtra("email", updatedEmail)

            setResult(UsersActivity.RESULT_UPDATE, resultIntent)
            finish()
        }
    }
}
