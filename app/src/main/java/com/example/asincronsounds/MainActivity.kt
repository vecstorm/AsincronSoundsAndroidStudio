package com.example.asincronsounds


import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.util.Log



class MainActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayer2: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mediaPlayer = MediaPlayer.create(this, R.raw.promare)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.maintheme)

        val txtPlay1 = findViewById<TextView>(R.id.textView)
        val txtPlay2 = findViewById<TextView>(R.id.textView2)
        val playButton = findViewById<Button>(R.id.Button1Play)
        val playButton2 = findViewById<Button>(R.id.Button2Play)
        val stopButton = findViewById<Button>(R.id.StopButton)
        var songName = ""

        playButton.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                mediaPlayer.start()

                Log.d("AUDIO", "Reproduciendo canción 1")

                launch(Dispatchers.Main) {
                    txtPlay1.text = "Reproduint..."
                }
            }
        }

        playButton2.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                mediaPlayer2.start()
                Log.d("AUDIO", "Reproduciendo canción 2")

                launch(Dispatchers.Main) {
                    txtPlay2.text = "Reproduint..."
                }
            }
        }

            stopButton.setOnClickListener {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.prepare()
                    txtPlay1.text = ""
                }
                if (mediaPlayer2.isPlaying) {
                    mediaPlayer2.stop()
                    mediaPlayer2.prepare()
                    txtPlay2.text = ""
                }
            }
        }

    }