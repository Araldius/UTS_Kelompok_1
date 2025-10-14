package com.example.utskelompok1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utskelompok1.databinding.ActivityAdminBinding

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    private lateinit var antrianAdapter: AntrianAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar) // Penting untuk menampilkan menu
        supportActionBar?.title = "Admin"

        setupRecyclerView()
        updateUI()

        binding.btnPanggilBerikutnyaAdmin.setOnClickListener {
            Toast.makeText(this, "Memanggil pasien berikutnya...", Toast.LENGTH_SHORT).show()
        }
        binding.btnSelesaiAdmin.setOnClickListener {
            Toast.makeText(this, "Pasien ditandai selesai.", Toast.LENGTH_SHORT).show()
        }
        binding.fabAddManual.setOnClickListener {
            Toast.makeText(this, "Membuka form tambah manual...", Toast.LENGTH_SHORT).show()
        }
    }

    // --- FUNGSI BARU UNTUK MENU DIMULAI DI SINI ---
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.admin_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_report -> {
                startActivity(Intent(this, ReportActivity::class.java))
                true
            }
            R.id.menu_manage_schedule -> {
                startActivity(Intent(this, ScheduleAdminActivity::class.java))
                true
            }
            R.id.menu_logout -> {
                startActivity(Intent(this, AuthActivity::class.java))
                finishAffinity() // Menutup semua activity dan kembali ke login
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // --- FUNGSI BARU UNTUK MENU BERAKHIR DI SINI ---

    private fun setupRecyclerView() {
        antrianAdapter = AntrianAdapter(emptyList()) {}
        binding.rvAntrianAdmin.layoutManager = LinearLayoutManager(this)
        binding.rvAntrianAdmin.adapter = antrianAdapter
    }

    private fun updateUI() {
        binding.progressBarAdmin.visibility = View.GONE
        binding.rvAntrianAdmin.visibility = View.VISIBLE

        val antrianList = DummyData.antrianList
        binding.tvTotalPasien.text = "Total Pasien: ${antrianList.size}"

        val currentlyCalled = antrianList.find { it.status == "Dipanggil" }
        binding.tvNamaPasienDipanggilAdmin.text = currentlyCalled?.namaPasien ?: "-"

        antrianAdapter.updateData(antrianList)
    }
}