package com.example.utskelompok1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.utskelompok1.databinding.ActivityQueueFormBinding

class QueueFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQueueFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQueueFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSubmitQueue.setOnClickListener {
            Toast.makeText(this, "Antrian berhasil didaftarkan! (Demo)", Toast.LENGTH_SHORT).show()
            finish()
        }
        binding.btnTakePhoto.setOnClickListener {
            Toast.makeText(this, "Membuka kamera... (Demo)", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}