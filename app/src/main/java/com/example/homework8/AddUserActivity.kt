package com.example.homework8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework8.databinding.ActivityUserAddBinding

class AddUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addItemBtn.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("firstName", binding.etFirstName.text.toString())
            resultIntent.putExtra("lastName", binding.etLastName.text.toString())
            resultIntent.putExtra("email", binding.etEmail.text.toString())

            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}