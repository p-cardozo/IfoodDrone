package br.com.pcardozo.ifoodDrone.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import br.com.pcardozo.ifoodDrone.R

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition {
            true
        }

        val intent = Intent(this, CepActivity::class.java)
        startActivity(intent)
        finish()


    }

}