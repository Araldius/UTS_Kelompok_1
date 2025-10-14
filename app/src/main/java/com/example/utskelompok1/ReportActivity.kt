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

        updateUI()
    }

    private fun updateUI() {
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        binding.tvReportDate.text = "Laporan untuk tanggal: ${dateFormat.format(Date())}"

        val antrianList = DummyData.antrianList
        val total = antrianList.size
        val selesai = antrianList.count { it.status == "Selesai" }
        val menunggu = antrianList.count { it.status == "Menunggu" || it.status == "Dipanggil" }

        binding.tvTotalPatients.text = total.toString()
        binding.tvPatientsDone.text = selesai.toString()
        binding.tvPatientsWaiting.text = menunggu.toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}