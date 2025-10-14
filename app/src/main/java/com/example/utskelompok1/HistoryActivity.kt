package com.example.utskelompok1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utskelompok1.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var adapter: AntrianAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Riwayat Kunjungan"

        setupRecyclerView()
        loadHistoryData()
    }

    private fun setupRecyclerView() {
        adapter = AntrianAdapter(emptyList()) {}
        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.adapter = adapter
    }

    private fun loadHistoryData() {
        // Gabungkan data hari ini dan data riwayat
        val allData = DummyData.antrianHariIni + DummyData.riwayatList

        // Filter riwayat berdasarkan ID pengguna yang sedang login
        val userHistory = allData.filter { it.userId == DummyData.currentLoggedInUserId }

        // Urutkan berdasarkan tanggal (opsional, tapi lebih baik)
        val sortedHistory = userHistory.sortedByDescending { it.tanggal }

        adapter.updateData(sortedHistory)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}