package com.example.utskelompok1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.utskelompok1.databinding.ActivityScheduleAdminBinding

class ScheduleAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleAdminBinding
    private lateinit var adapter: ScheduleAdminAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupRecyclerView()
        updateUI()
    }

    private fun setupRecyclerView() {
        // isAdmin: true, karena ini halaman admin
        adapter = ScheduleAdminAdapter(emptyList(), true) { jadwal ->
            showEditDialog(jadwal)
        }
        binding.rvScheduleAdmin.layoutManager = LinearLayoutManager(this)
        binding.rvScheduleAdmin.adapter = adapter
    }

    private fun updateUI() {
        adapter.updateData(DummyData.jadwalList)
    }

    private fun showEditDialog(jadwal: Jadwal) {
        AlertDialog.Builder(this)
            .setTitle("Ubah Jadwal ${jadwal.hari}")
            .setMessage("Ini adalah dialog untuk mengubah jadwal. (Demo)")
            .setPositiveButton("Simpan") { _, _ ->
                Toast.makeText(this, "Jadwal ${jadwal.hari} diperbarui! (Demo)", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}