package com.example.bookingkamarhotel

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bookingkamarhotel.ui.theme.BookingKamarHotelTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userEmail = intent.getStringExtra("USER_EMAIL") ?: "User"

        setContent {
            BookingKamarHotelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val rooms = listOf(
                        Room("Deluxe Room",
                            "Nyaman dan elegan, cocok untuk pasangan.",
                            "Deluxe Room merupakan pilihan kamar yang sempurna untuk tamu yang menginginkan kenyamanan dan kemewahan. Didesain dengan konsep modern dan elegan, kamar ini dilengkapi dengan tempat tidur queen size yang nyaman, serta perabotan mewah yang memberikan suasana yang menyenangkan. Fasilitas di dalamnya mencakup TV LED 43 inci dengan berbagai saluran hiburan, " +
                                    "AC yang dapat disesuaikan, serta meja kerja untuk keperluan bisnis. " +
                                    "Kamar mandi pribadi dengan shower air panas dan perlengkapan mandi premium memastikan kenyamanan ekstra bagi penghuninya. " +
                                    "Dengan pemandangan kota yang indah, Deluxe Room adalah pilihan terbaik untuk tamu yang mencari pengalaman menginap yang mewah namun tetap terjangkau.",
                            R.drawable.room_image_1,
                            120.0,
                            5
                    ),
                        Room(
                            "Standar Room",
                            "Kamar Hemat, bersih dan nyaman.",
                            "Standard Room adalah pilihan yang ideal bagi tamu yang membutuhkan akomodasi dengan kenyamanan dasar namun tetap terjangkau. " +
                                    "Kamar ini memiliki tempat tidur single atau double yang nyaman, AC untuk memastikan kenyamanan sepanjang malam, serta meja kecil untuk kebutuhan pribadi atau pekerjaan. " +
                                    "Fasilitas kamar mandi di dalamnya cukup lengkap, dengan shower dan perlengkapan mandi standar. " +
                                    "Meskipun sederhana, kamar ini dirancang dengan baik untuk memenuhi kebutuhan tamu yang ingin menginap dengan harga yang lebih ekonomis tanpa mengorbankan kualitas tidur dan kenyamanan.",
                            R.drawable.room_image_2,
                            120.0,
                            5

                    ),

                        Room(
                            "Suite Room",
                            "Luas dan mewah, cocok untuk keluarga.",
                            "Suite Room menawarkan lebih banyak ruang dan kemewahan bagi tamu yang membutuhkan akomodasi premium. Kamar ini terdiri dari ruang tidur terpisah, ruang tamu, serta fasilitas yang lebih lengkap seperti sofa yang nyaman, meja makan kecil, dan TV besar untuk kenyamanan ekstra. " +
                                    "Selain itu, Suite Room dilengkapi dengan kamar mandi besar yang memiliki bathtub dan shower terpisah, memberikan pengalaman mandi yang lebih mewah. Pemandangan kota yang luar biasa dapat dinikmati dari jendela besar, sementara desain interiornya yang elegan dan modern memastikan kenyamanan maksimal selama menginap. " +
                                    "Suite Room adalah pilihan tepat bagi tamu yang menginginkan lebih dari sekedar tempat tidur, namun juga pengalaman menginap yang.",
                            R.drawable.room_image_3,
                            120.0,
                            5
                    ),


                        Room(
                            "Executive Room",
                            "Didesain untuk eksekutif dan pebisnis.",
                            "Executive Room adalah pilihan kamar yang didesain khusus untuk para tamu VIP atau pebisnis yang membutuhkan kenyamanan ekstra dan fasilitas lengkap. " +
                                    "Kamar ini dilengkapi dengan tempat tidur king size, meja kerja besar dengan kursi ergonomis, serta lampu yang dapat diatur sesuai kebutuhan untuk menciptakan atmosfer yang nyaman saat bekerja atau bersantai." +
                                    "Fasilitas kamar mandi yang elegan termasuk shower dengan tekanan air yang sangat baik, serta perlengkapan mandi premium. Kamar ini juga menawarkan pemandangan spektakuler dari lantai tinggi, memberikan suasana tenang dan privat yang ideal untuk tamu yang menginginkan ketenangan setelah hari yang sibuk. " +
                                    "Executive Room dirancang untuk memberikan kenyamanan dan efisiensi bagi para tamu yang menghargai kualitas layanan.",
                            R.drawable.room_image_4,
                            120.0,
                            5

                    ),
                        Room(
                            "Family Room",
                            "Cocok Untuk Keluarga Besar.",
                            "Family Room dirancang khusus untuk keluarga yang menginginkan kenyamanan dan ruang yang lebih luas selama menginap. " +
                                    "Kamar ini dilengkapi dengan dua tempat tidur queen size yang dapat menampung hingga empat orang, serta ruang tamu kecil untuk bersantai. Fasilitas di dalam kamar mencakup TV layar datar, AC yang dapat diatur untuk setiap bagian kamar, dan meja makan untuk makan bersama. Kamar mandi memiliki fasilitas shower dan bathtub, cocok untuk keluarga dengan anak-anak. Desain kamar yang terang dan luas memberi suasana yang nyaman bagi keluarga yang ingin menginap bersama, sementara pemandangan kota yang indah menambah kesan menyenangkan selama masa menginap. " +
                                    "Family Room adalah pilihan tepat untuk keluarga yang menginginkan kenyamanan rumah dengan fasilitas hotel premium.",
                            R.drawable.room_image_1,
                            120.0,
                            5),
                    )

                    RoomList(
                        rooms = rooms,
                        modifier = Modifier.padding(innerPadding),
                        userEmail = userEmail
                    )
                }
            }
        }
    }
}

@Composable
fun RoomList(rooms: List<Room>, modifier: Modifier = Modifier, userEmail: String) {
    val context = LocalContext.current
    val gson = Gson()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp)
        ) {
            items(rooms) { room ->
                RoomItem(room = room) {
                    val intent = Intent(context, RoomDetailActivity::class.java).apply {
                        putExtra("ROOM_DATA", gson.toJson(room))
                    }
                    context.startActivity(intent)
                }
            }
        }

        Text(
            text = "Welcome @$userEmail",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun RoomItem(room: Room, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = room.imageRes,
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = room.title, style = MaterialTheme.typography.titleMedium)
                Text(text = room.shortDescription, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

