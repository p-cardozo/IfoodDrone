package br.com.pcardozo.ifoodDrone.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import br.com.pcardozo.ifoodDrone.R


class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed({
            mostrarMainActivity()
        }, 3000)
    }

    private fun mostrarMainActivity() {
        val intent = Intent(this, CepActivity::class.java)
        startActivity(intent)
        finish()
    }

}