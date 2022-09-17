package com.example.mydiary.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.mydiary.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity(){

    var hello : String? = "Hello world"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(1000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }

        calculate(1,2, ::subtract)
    }

    private fun sum(a: Int, b: Int) : Int{
        return a+b
    }

    private fun subtract(a: Int, b: Int) : Int{
        return a-b
    }

    private fun multiply(a: Int, b: Int) : Int{
        return a*b
    }

    /*
    Higher order function
     */
    private fun calculate(a:Int, b:Int, fn: (Int, Int) -> Int) : Int {
        return fn(a,b)
    }


}