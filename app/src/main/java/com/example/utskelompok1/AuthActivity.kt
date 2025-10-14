package com.example.utskelompok1

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.utskelompok1.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding
    private var isRegisterMode = false // Flag untuk melacak mode UI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // Reset user yang login setiap kali kembali ke halaman ini
        DummyData.currentLoggedInUserId = null

        Toast.makeText(this, "Coba login: given@gmail.com (pass: given123)", Toast.LENGTH_LONG).show()

        // Listener untuk tombol utama (bisa Login atau Daftar)
        binding.btnAuthAction.setOnClickListener {
            handleAuthAction()
        }

        // Listener untuk teks di bawah tombol (untuk ganti mode)
        binding.tvToggleMode.setOnClickListener {
            isRegisterMode = !isRegisterMode // Balikkan mode
            updateUiForMode()
        }
    }

    private fun handleAuthAction() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, getString(R.string.auth_error_empty_fields), Toast.LENGTH_SHORT).show()
            return
        }

        if (isRegisterMode) {
            // Logika untuk Registrasi
            Toast.makeText(this, getString(R.string.auth_register_success), Toast.LENGTH_SHORT).show()
            // Set ID pengguna baru (tidak ada di daftar antrian)
            DummyData.currentLoggedInUserId = "new_user_id"
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            // Logika untuk Login
            when {
                email == "given@gmail.com" && password == "given123" -> {
                    DummyData.currentLoggedInUserId = "given_id"
                    loginSuccess("Given Morthen")
                }
                email == "devin@gmail.com" && password == "devin123" -> {
                    DummyData.currentLoggedInUserId = "devin_id"
                    loginSuccess("Devin Wongosari")
                }
                email == "dokter@gmail.com" && password == "12345678" -> {
                    startActivity(Intent(this, DoctorActivity::class.java))
                    finish()
                }
                email == "admin@gmail.com" && password == "12345678" -> {
                    startActivity(Intent(this, AdminActivity::class.java))
                    finish()
                }
                else -> {
                    Toast.makeText(this, "Email atau password salah.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun loginSuccess(name: String) {
        Toast.makeText(this, "Selamat datang, $name!", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun updateUiForMode() {
        if (isRegisterMode) {
            // Atur UI untuk mode Registrasi
            binding.authTitle.text = getString(R.string.auth_register_title)
            binding.btnAuthAction.text = getString(R.string.auth_register_button)
            binding.tvToggleMode.text = Html.fromHtml(getString(R.string.auth_login_prompt), Html.FROM_HTML_MODE_LEGACY)
        } else {
            // Atur UI untuk mode Login
            binding.authTitle.text = getString(R.string.auth_title)
            binding.btnAuthAction.text = getString(R.string.auth_login_button)
            binding.tvToggleMode.text = Html.fromHtml(getString(R.string.auth_register_prompt), Html.FROM_HTML_MODE_LEGACY)
        }
    }
}