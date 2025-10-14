package com.example.utskelompok1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.utskelompok1.databinding.ListItemScheduleAdminBinding

class ScheduleAdminAdapter(
    private var jadwalList: List<Jadwal>,
    private val isAdmin: Boolean, // Flag untuk membedakan admin dan pasien
    private val onEditClicked: (Jadwal) -> Unit
) : RecyclerView.Adapter<ScheduleAdminAdapter.JadwalViewHolder>() {

    inner class JadwalViewHolder(val binding: ListItemScheduleAdminBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
        val binding = ListItemScheduleAdminBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JadwalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JadwalViewHolder, position: Int) {
        val jadwal = jadwalList[position]
        holder.binding.tvDayName.text = jadwal.hari
        if (jadwal.status == "Buka") {
            holder.binding.tvScheduleInfo.text = "${jadwal.jamBuka} - ${jadwal.jamTutup}"
        } else {
            holder.binding.tvScheduleInfo.text = "Tutup"
        }

        // Tampilkan/sembunyikan tombol "Ubah" berdasarkan peran
        if (isAdmin) {
            holder.binding.btnEditSchedule.visibility = View.VISIBLE
            holder.binding.btnEditSchedule.setOnClickListener { onEditClicked(jadwal) }
        } else {
            holder.binding.btnEditSchedule.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = jadwalList.size

    fun updateData(newJadwalList: List<Jadwal>) {
        this.jadwalList = newJadwalList
        notifyDataSetChanged()
    }
}