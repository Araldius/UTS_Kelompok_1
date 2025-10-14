package com.example.utskelompok1

// Model untuk data antrian
data class Antrian(
    val id: String,
    val namaPasien: String,
    val nomorAntrian: Int,
    var status: String,
    val userId: String,
    val tanggal: String // Tambahan field tanggal untuk riwayat
)

// Model untuk data jadwal
data class Jadwal(
    val hari: String,
    var jamBuka: String,
    var jamTutup: String,
    var status: String
)

// Object untuk menyimpan data dummy global
object DummyData {
    var currentLoggedInUserId: String? = null

    // --- DATA UNTUK ANTRIAN HARI INI (MISALNYA TANGGAL 15-10-2025) ---
    val antrianHariIni = mutableListOf(
        Antrian("h1", "Budi Santoso", 1, "Selesai", "user1", "15-10-2025"),
        Antrian("h2", "Siti Aminah", 2, "Selesai", "user2", "15-10-2025"),
        Antrian("h3", "Hendra Wijaya", 3, "Dipanggil", "user8", "15-10-2025"),
        Antrian("h4", "Given Morthen", 4, "Menunggu", "given_id", "15-10-2025"), // Antrian aktif Given
        Antrian("h5", "Devin Wongosari", 5, "Menunggu", "devin_id", "15-10-2025"), // Antrian aktif Devin
        Antrian("h6", "Ika Fitriani", 6, "Menunggu", "user9", "15-10-2025"),
        Antrian("h7", "Joko Susilo", 7, "Menunggu", "user10", "15-10-2025")
    )

    // --- DATA UNTUK RIWAYAT KUNJUNGAN (DARI HARI-HARI SEBELUMNYA) ---
    val riwayatList = mutableListOf(
        // Riwayat untuk Given Morthen
        Antrian("r1", "Given Morthen", 5, "Selesai", "given_id", "10-10-2025"),
        Antrian("r2", "Given Morthen", 2, "Selesai", "given_id", "02-10-2025"),
        Antrian("r3", "Given Morthen", 8, "Selesai", "given_id", "25-09-2025"),
        Antrian("r4", "Given Morthen", 1, "Selesai", "given_id", "18-09-2025"),

        // Riwayat untuk Devin Wongosari
        Antrian("r5", "Devin Wongosari", 1, "Selesai", "devin_id", "11-10-2025"),
        Antrian("r6", "Devin Wongosari", 7, "Selesai", "devin_id", "03-10-2025"),

        // Riwayat acak untuk user lain
        Antrian("r7", "Budi Santoso", 3, "Selesai", "user1", "08-10-2025")
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