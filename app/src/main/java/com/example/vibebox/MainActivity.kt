package com.example.vibebox

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibebox.ui.theme.VibeBoxTheme
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VibeBoxTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background)
                {
                    AudioLogin()
                }
            }
        }
    }
}

@Composable
fun AudioLogin(){

    var roomId by remember {
        mutableStateOf("")
    }
    var username by remember {
        mutableStateOf("")
    }
    var context = LocalContext.current

    Column (Modifier.fillMaxSize(),Arrangement.Center, Alignment.CenterHorizontally){
        Text(text = "Vibe Box", fontSize = 32.sp, fontFamily = FontFamily.Monospace)
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(value = roomId, onValueChange ={
            roomId=it }, label = { Text(text = "Room ID")})

        OutlinedTextField(value = username, onValueChange ={
            username=it }, label = { Text(text = "Username")})

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            roomId= generateRoomId()
            val intent =Intent(context,LiveAudioRoomActivity::class.java)
            intent.putExtra("userId",username)
            intent.putExtra("roomId",roomId)
            intent.putExtra("isHost",true)
            context.startActivity(intent)
        }) {
            Text(text = "Start Audiobox")
        }
        Button(onClick = {
            val intent =Intent(context,LiveAudioRoomActivity::class.java)
            intent.putExtra("userId",username)
            intent.putExtra("roomId",roomId)
            intent.putExtra("isHost",false)
            context.startActivity(intent) }) {
            Text(text = "Join Audiobox")
        }
    }
}


fun generateRoomId(): String {
    var id = StringBuilder();
    while (id.length<5){
        var random = java.util.Random().nextInt(10);
        id.append(random)
    }
        return  id.toString()
    }
