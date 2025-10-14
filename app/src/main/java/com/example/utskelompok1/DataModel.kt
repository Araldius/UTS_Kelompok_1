package com.example.utskelompok1

data class Antrian(
    val id: String,
    val namaPasien: String,
    val nomorAntrian: Int,
    var status: String,
    val userId: String
)

data class Jadwal(
    val hari: String,
    var jamBuka: String,
    var jamTutup: String,
    var status: String
)

object DummyData {
    val antrianList = mutableListOf(
        Antrian("id1", "Budi Santoso", 1, "Selesai", "user1"),
        Antrian("id2", "Siti Aminah", 2, "Dipanggil", "user2"),
        Antrian("id3", "Ahmad Yusuf", 3, "Menunggu", "currentUser"),
        Antrian("id4", "Dewi Lestari", 4, "Menunggu", "user4"),
        Antrian("id5", "Eko Prasetyo", 5, "Menunggu", "user5")
    )

    val jadwalList = mutableListOf(
        Jadwal("Senin", "09:00", "17:00", "Buka"),
        Jadwal("Selasa", "09:00", "17:00", "Buka"),
        Jadwal("Rabu", "09:00", "17:00", "Buka"),
        Jadwal("Kamis", "09:00", "17:00", "Buka"),
        Jadwal("Jumat", "09:00", "17:00", "Buka"),
        Jadwal("Sabtu", "09:00", "12:00", "Buka"),
        Jadwal("Minggu", "", "", "Tutup")
    )
}