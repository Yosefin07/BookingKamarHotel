package com.example.bookingkamarhotel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.gson.Gson

class RoomDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ambil data yang diteruskan dari MainActivity
        val roomJson = intent.getStringExtra("ROOM_DATA")
        val room = Gson().fromJson(roomJson, Room::class.java)

        setContent {
            RoomDetailScreen(room = room)
        }
    }
}

@Composable
fun RoomDetailScreen(room: Room) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Gambar kamar
        val painter: Painter = rememberImagePainter(room.imageRes)
        Image(
            painter = painter,
            contentDescription = room.title,
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Nama kamar
        Text(text = room.title, style = MaterialTheme.typography.headlineLarge)

        // Deskripsi panjang
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = room.longDescription, style = MaterialTheme.typography.bodyLarge)

        // Harga kamar
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Harga: Rp ${room.price}", style = MaterialTheme.typography.titleMedium)

        // Ketersediaan kamar
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Ketersediaan: ${room.availability} kamar", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Pemesanan
        Button(
            onClick = { /* Implementasikan logika pemesanan */ },
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Pesan Sekarang")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRoomDetail() {
    val room = Room(
        "Deluxe Room",
        "Nyaman dan elegan, cocok untuk pasangan.",
        "Kamar Deluxe dirancang untuk kenyamanan maksimal, dengan pemandangan indah dan fasilitas lengkap.",
        R.drawable.room_image_1, // Pastikan gambar ini ada
        120.0,
        5
    )
    RoomDetailScreen(room = room)
}
