package com.mittal.mydiary.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mittal.mydiary.R
import com.mittal.mydiary.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This is the "Execution" of Data Binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }
}