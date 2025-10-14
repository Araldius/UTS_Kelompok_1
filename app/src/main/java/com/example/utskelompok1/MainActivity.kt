package com.example.utskelompok1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utskelompok1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var antrianAdapter: AntrianAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Antrian Hari Ini"

        setupRecyclerView()
        updateUI()

        binding.fabAddQueue.setOnClickListener {
            startActivity(Intent(this, QueueFormActivity::class.java))
        }

        binding.btnViewSchedule.setOnClickListener {
            startActivity(Intent(this, ScheduleActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        antrianAdapter = AntrianAdapter(emptyList()) { antrian ->
            if (antrian.userId == DummyData.currentLoggedInUserId && antrian.status == "Menunggu") {
                AlertDialog.Builder(this)
                    .setTitle("Konfirmasi")
                    .setMessage("Batalkan antrian nomor ${antrian.nomorAntrian}?")
                    .setPositiveButton("Ya") { _, _ -> Toast.makeText(this, "Antrian dibatalkan! (Demo)", Toast.LENGTH_SHORT).show() }
                    .setNegativeButton("Tidak", null)
                    .show()
            }
        }
        binding.rvAntrian.layoutManager = LinearLayoutManager(this)
        binding.rvAntrian.adapter = antrianAdapter
    }

    private fun updateUI() {
        binding.progressBar.visibility = View.GONE

        val antrianList = DummyData.antrianHariIni
        if (antrianList.isEmpty()){
            binding.tvEmptyState.visibility = View.VISIBLE
            binding.rvAntrian.visibility = View.GONE
        } else {
            binding.tvEmptyState.visibility = View.GONE
            binding.rvAntrian.visibility = View.VISIBLE
            antrianAdapter.updateData(antrianList)
        }

        val currentlyCalled = antrianList.find { it.status == "Dipanggil" }
        binding.tvNomorDilayani.text = currentlyCalled?.nomorAntrian?.toString() ?: "-"

        val yourQueue = antrianList.find { it.userId == DummyData.currentLoggedInUserId }
        binding.tvNomorAnda.text = yourQueue?.nomorAntrian?.toString() ?: "-"

        binding.tvEstimasiWaktu.text = "Estimasi 10 menit lagi"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                return true
            }
            R.id.menu_logout -> {
                startActivity(Intent(this, AuthActivity::class.java))
                finishAffinity()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}