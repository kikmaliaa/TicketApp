package com.example.ticketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.de,t[0 ijlay

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            // =========================
            // STATE DIKELOLA PARENT
            // =========================

            var nama by rememberSaveable {
                mutableStateOf("")
            }

            var jumlahTiket by rememberSaveable {
                mutableStateOf(1)
            }

            var status by rememberSaveable {
                mutableStateOf("Nama masih kosong")
            }

            val hargaTiket = 50000

            // =========================
            // LAUNCHED EFFECT
            // =========================

            LaunchedEffect(status) {

                if (status == "Memproses pesanan........") {

                    delay(5000)

                    status = "Tiket telah dipesan"
                }
            }

            // =========================
            // UI / CHILD
            // =========================

            TicketScreen(
                nama = nama,
                jumlahTiket = jumlahTiket,
                hargaTiket = hargaTiket,
                status = status,

                onNamaChange = {
                    nama = it

                    status = if (it.isBlank()) {
                        "Nama masih kosong"
                    } else {
                        "Siap memesan tiket"
                    }
                },

                onTambahTiket = {
                    jumlahTiket++
                },

                onKurangTiket = {
                    if (jumlahTiket > 1) {
                        jumlahTiket--
                    }
                },

                onPesanClick = {

                    if (nama.isBlank()) {
                        status = "Nama harus diisi"
                    } else {
                        status = "Memproses pesanan........"
                    }
                }
            )
        }
    }
}


// ======================================================
// CHILD COMPOSABLE
// STATE SUDAH DI-HOIST KE PARENT
// ======================================================

@Composable
fun TicketScreen(
    nama: String,
    jumlahTiket: Int,
    hargaTiket: Int,
    status: String,
    onNamaChange: (String) -> Unit,
    onTambahTiket: () -> Unit,
    onKurangTiket: () -> Unit,
    onPesanClick: () -> Unit
) {

    // =========================
    // WARNA
    // =========================

    val backgroundPink = Color(0xFFFFF5F8)
    val cardPink = Color(0xFFFFE4EC)
    val primaryPink = Color(0xFFE75480)
    val darkPink = Color(0xFF9E3156)
    val softPink = Color(0xFFFFD4E1)
    val textDark = Color(0xFF442A33)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundPink
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            // =========================
            // IDENTITAS
            // =========================

            Text(
                text = "Rizki Amalia",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = darkPink
            )

            Text(
                text = "245150407111014",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))


            // =========================
            // JUDUL
            // =========================

            Text(
                text = "🎟 Pemesanan Tiket",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = darkPink
            )

            Text(
                text = "Pesan tiketmu dengan mudah ♡",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))


            // =========================
            // CARD
            // =========================

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    // =========================
                    // NAMA
                    // =========================

                    Text(
                        text = "Nama Pembeli",
                        fontWeight = FontWeight.Bold,
                        color = textDark
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = nama,
                        onValueChange = onNamaChange,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("Masukkan nama kamu")
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = primaryPink,
                            unfocusedBorderColor = softPink
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))


                    // =========================
                    // JUMLAH TIKET
                    // =========================

                    Text(
                        text = "Jumlah Tiket",
                        fontWeight = FontWeight.Bold,
                        color = textDark
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Button(
                            onClick = onKurangTiket,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = softPink
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "−",
                                color = darkPink,
                                fontSize = 20.sp
                            )
                        }

                        Text(
                            text = "$jumlahTiket",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = textDark
                        )

                        Button(
                            onClick = onTambahTiket,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = softPink
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = "+",
                                color = darkPink,
                                fontSize = 20.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))


                    // =========================
                    // HARGA TIKET
                    // =========================

                    Text(
                        text = "Harga Tiket",
                        fontWeight = FontWeight.Bold,
                        color = textDark
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                cardPink,
                                RoundedCornerShape(16.dp)
                            )
                            .padding(16.dp)
                    ) {

                        Text(
                            text = "Rp ${hargaTiket.formatRupiah()} / tiket",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = darkPink
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))


                    // =========================
                    // TOTAL HARGA
                    // =========================

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Total",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )

                        Text(
                            text = "Rp ${(hargaTiket * jumlahTiket).formatRupiah()}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = darkPink
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))


                    // =========================
                    // BUTTON PESAN
                    // =========================

                    Button(
                        onClick = onPesanClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryPink
                        ),
                        enabled = status != "Memproses pesanan........"
                    ) {

                        if (status == "Memproses pesanan........") {

                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )

                            Spacer(modifier = Modifier.width(10.dp))
                        }

                        Text(
                            text = if (
                                status == "Memproses pesanan........"
                            ) {
                                "Memproses..."
                            } else {
                                "Pesan Tiket"
                            },
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))


                    // =========================
                    // STATUS
                    // =========================

                    StatusCard(status)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "♡ Thank you for choosing us ♡",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}


// ======================================================
// STATUS
// ======================================================

@Composable
fun StatusCard(status: String) {

    val backgroundColor = when (status) {

        "Tiket telah dipesan" ->
            Color(0xFFE3F6E8)

        "Nama harus diisi" ->
            Color(0xFFFFE4E8)

        "Memproses pesanan........" ->
            Color(0xFFE6F0FF)

        else ->
            Color(0xFFF7EEF2)
    }

    val textColor = when (status) {

        "Tiket telah dipesan" ->
            Color(0xFF267A3E)

        "Nama harus diisi" ->
            Color(0xFFB3261E)

        "Memproses pesanan........" ->
            Color(0xFF315A9E)

        else ->
            Color(0xFF6B5960)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                backgroundColor,
                RoundedCornerShape(14.dp)
            )
            .padding(14.dp)
    ) {

        Text(
            text = "Status : $status",
            color = textColor,
            fontWeight = FontWeight.Medium
        )
    }
}


// ======================================================
// FORMAT RUPIAH
// ======================================================

fun Int.formatRupiah(): String {
    return String.format("%,d", this)
        .replace(',', '.')
}