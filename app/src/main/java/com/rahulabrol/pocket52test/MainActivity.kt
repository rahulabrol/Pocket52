package com.rahulabrol.pocket52test

import android.os.Bundle
import com.rahulabrol.pocket52test.base.DataBindingActivity
import com.rahulabrol.pocket52test.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DataBindingActivity() {
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {

        }
    }
}