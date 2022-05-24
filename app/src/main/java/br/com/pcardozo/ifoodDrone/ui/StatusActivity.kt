package br.com.pcardozo.ifoodDrone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.pcardozo.ifoodDrone.R

private const val STREET = "STREET_KEY"


class StatusActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, StatusActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        val pref = getSharedPreferences("PREF", MODE_PRIVATE)
        val street = pref.getString(STREET, null)

        findViewById<TextView>(R.id.status_txt_street).text =
            "Seu pedido está a caminho de: $street"

    }
}