package com.example.utskelompok1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utskelompok1.databinding.ActivityDoctorBinding

class DoctorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDoctorBinding
    private lateinit var antrianAdapter: AntrianAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar) // Penting untuk menampilkan menu
        supportActionBar?.title = "Dokter"

        setupRecyclerView()
        updateUI()

        binding.btnPanggilBerikutnya.setOnClickListener {
            Toast.makeText(this, "Memanggil pasien berikutnya...", Toast.LENGTH_SHORT).show()
        }
        binding.btnSelesai.setOnClickListener {
            Toast.makeText(this, "Pasien ditandai selesai.", Toast.LENGTH_SHORT).show()
        }
        binding.btnPindahAkhir.setOnClickListener {
            Toast.makeText(this, "Pasien dipindah ke akhir.", Toast.LENGTH_SHORT).show()
        }
        binding.switchStatusPraktek.setOnCheckedChangeListener { _, isChecked ->
            val status = if (isChecked) "Dibuka" else "Ditutup"
            Toast.makeText(this, "Pendaftaran $status", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.doctor_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_logout -> {
                startActivity(Intent(this, AuthActivity::class.java))
                finishAffinity() // Menutup semua activity dan kembali ke login
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        antrianAdapter = AntrianAdapter(emptyList()) {}
        binding.rvAntrianDokter.layoutManager = LinearLayoutManager(this)
        binding.rvAntrianDokter.adapter = antrianAdapter
    }

    private fun updateUI() {
        binding.progressBarDokter.visibility = View.GONE
        binding.rvAntrianDokter.visibility = View.VISIBLE

        val antrianList = DummyData.antrianList
        val currentlyCalled = antrianList.find { it.status == "Dipanggil" }
        binding.tvNamaPasienDipanggil.text = currentlyCalled?.namaPasien ?: "-"
        binding.tvKeluhanDipanggil.text = "Keluhan: Sakit Kepala" // Dummy text

        val waitingList = antrianList.filter { it.status == "Menunggu" }
        antrianAdapter.updateData(waitingList)
    }
}