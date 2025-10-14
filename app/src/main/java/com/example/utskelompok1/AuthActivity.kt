package com.example.utskelompok1

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.utskelompok1.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // Menampilkan pesan Toast untuk memberitahu pengguna tentang akun dummy
        Toast.makeText(this, "Gunakan: pasien@email.com, dokter@email.com, atau admin@email.com", Toast.LENGTH_LONG).show()

        binding.btnAuthAction.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan password tidak boleh kosong.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Login berhasil sebagai $email", Toast.LENGTH_SHORT).show()

            // Logika navigasi berdasarkan akun dummy yang spesifik
            val intent = when (email) {
                "dokter@gmail.com" -> Intent(this, DoctorActivity::class.java)
                "admin@gmail.com" -> Intent(this, AdminActivity::class.java)
                "pasien@gmail.com" -> Intent(this, MainActivity::class.java)
                else -> {
                    // Default ke halaman pasien jika email tidak dikenali
                    Toast.makeText(this, "Email tidak dikenali, masuk sebagai Pasien.", Toast.LENGTH_SHORT).show()
                    Intent(this, MainActivity::class.java)
                }
            }
            startActivity(intent)
            finish()
        }

        binding.tvToggleMode.setOnClickListener {
            // Untuk demo UI, tombol ini hanya menampilkan Toast
            Toast.makeText(this, "Ini adalah mode demo UI.", Toast.LENGTH_SHORT).show()
        }
    }
}