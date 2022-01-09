package com.astrapay.jason_ajaib_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.astrapay.jason_ajaib_test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}