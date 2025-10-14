package com.example.utskelompok1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.utskelompok1.databinding.ActivityReportBinding
import java.text.SimpleDateFormat
import java.util.*

class ReportActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Laporan Harian"

        updateUI()
    }

    private fun updateUI() {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        binding.tvReportDate.text = "Laporan untuk tanggal: ${dateFormat.format(Date())}"

        // Menggunakan data antrianHariIni untuk laporan harian
        val antrianList = DummyData.antrianHariIni

        val total = antrianList.size
        val selesai = antrianList.count { it.status == "Selesai" }
        // Menunggu sekarang mencakup yang "Menunggu" dan "Dipanggil"
        val menunggu = antrianList.count { it.status == "Menunggu" || it.status == "Dipanggil" }

        binding.tvTotalPatients.text = total.toString()
        binding.tvPatientsDone.text = selesai.toString()
        binding.tvPatientsWaiting.text = menunggu.toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        // Fungsi untuk tombol kembali di toolbar
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}