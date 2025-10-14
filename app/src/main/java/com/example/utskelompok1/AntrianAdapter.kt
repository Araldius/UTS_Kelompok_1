package com.example.utskelompok1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.utskelompok1.databinding.ListItemAntrianBinding

class AntrianAdapter(
    private var antrianList: List<Antrian>,
    private val onItemClicked: (Antrian) -> Unit
) : RecyclerView.Adapter<AntrianAdapter.AntrianViewHolder>() {

    inner class AntrianViewHolder(val binding: ListItemAntrianBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AntrianViewHolder {
        val binding = ListItemAntrianBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AntrianViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AntrianViewHolder, position: Int) {
        val antrian = antrianList[position]
        val context = holder.itemView.context

        holder.binding.apply {
            tvNomorAntrian.text = antrian.nomorAntrian.toString()
            tvNamaPasien.text = antrian.namaPasien
            // Format status dengan tanggal
            tvStatus.text = "${antrian.status}  •  ${antrian.tanggal}"

            when (antrian.status) {
                "Dipanggil" -> tvStatus.setTextColor(ContextCompat.getColor(context, R.color.green_primary))
                "Selesai" -> tvStatus.setTextColor(ContextCompat.getColor(context, R.color.status_selesai))
                else -> tvStatus.setTextColor(ContextCompat.getColor(context, R.color.status_menunggu))
            }

            // Periksa ID pengguna yang sedang login
            if (antrian.userId == DummyData.currentLoggedInUserId) {
                root.strokeWidth = 4
                root.strokeColor = ContextCompat.getColor(context, R.color.green_light)
            } else {
                root.strokeWidth = 0
            }
        }
        holder.itemView.setOnClickListener { onItemClicked(antrian) }
    }

    override fun getItemCount(): Int = antrianList.size

    fun updateData(newAntrianList: List<Antrian>) {
        this.antrianList = newAntrianList
        notifyDataSetChanged()
    }
}