package com.example.bookingkamarhotel

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookingkamarhotel.ui.theme.BookingKamarHotelTheme

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookingKamarHotelTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    WelcomeScreen()
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen() {
    val context = LocalContext.current
    val density = LocalDensity.current.density

    Box(modifier = Modifier.fillMaxSize()) {
        // Gambar latar belakang penuh dan dipotong
        Image(
            painter = painterResource(id = R.drawable.background_image), // ganti dengan gambar latar belakang kamu
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Overlay gelap untuk visibilitas teks
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        )

        // Konten utama
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues())
                .padding(horizontal = 32.dp, vertical = 24.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo aplikasi
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Teks Selamat Datang
            Text(
                text = "Selamat Datang di BookingKamarHotel",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 2,
                softWrap = true,
                modifier = Modifier.graphicsLayer(
                    shadowElevation = 4.dp.value * density,
                    shape = RoundedCornerShape(8.dp),
                    clip = false
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Teks Slogan
            Text(
                text = "Aplikasi pemesanan kamar hotel yang mudah dan cepat.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.8f),
                maxLines = 2,
                softWrap = true,
                modifier = Modifier.graphicsLayer(
                    shadowElevation = 2.dp.value * density,
                    shape = RoundedCornerShape(8.dp),
                    clip = false
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Baris untuk Tombol
            Row {
                // Tombol Masuk
                Button(
                    onClick = {
                        context.startActivity(Intent(context, LoginActivity::class.java))
                    },
                    modifier = Modifier
                        .weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE))
                ) {
                    Text("Masuk", color = Color.White)
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Tombol Daftar
                Button(
                    onClick = {
                        context.startActivity(Intent(context, RegisterActivity::class.java))
                    },
                    modifier = Modifier
                        .weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                ) {
                    Text("Daftar", color = Color.White)
                }
            }
        }
    }
}
