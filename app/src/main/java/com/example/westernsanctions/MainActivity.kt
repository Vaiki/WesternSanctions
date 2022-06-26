package com.example.westernsanctions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.example.westernsanctions.ui.MyViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView:TextView = findViewById(R.id.tv_sanctions)
        viewModel.getResultPersonalSanctions()
        viewModel.personalSanctionsLiveData.observe(this,{textView.text = it.joinToString()})

    }
}
