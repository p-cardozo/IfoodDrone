package br.com.pcardozo.ifoodDrone.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.pcardozo.ifoodDrone.R
import java.math.BigDecimal
import java.math.RoundingMode

private const val EXTRA_CEP = "EXTRA_CEP"
private const val EXTRA_VALUE = "EXTRA_VALUE"


class PaymentActivity : AppCompatActivity() {

    private val edtCardNumber: EditText by lazy { findViewById(R.id.payment_txt_card_number) }
    private val edtName: EditText by lazy { findViewById(R.id.payment_txt_name) }
    private val edtValidate: EditText by lazy { findViewById(R.id.payment_txt_validate) }
    private val edtCvv: EditText by lazy { findViewById(R.id.payment_txt_cvv) }


    companion object {
        fun newInstance(
            context: Context, value: Double
        ): Intent {
            val intent = Intent(context, PaymentActivity::class.java)
            intent.putExtra(EXTRA_VALUE, value)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)


        val value = intent.getDoubleExtra(EXTRA_VALUE, 0.0)

        findViewById<TextView>(R.id.payment_txt_value_total).text =
            BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).toString()

        if (edtCardNumber.length() > 0 &&
            edtName.length() > 0 &&
            edtValidate.length() > 0 &&
            edtCvv.length() > 0
        ) {
            Toast.makeText(this, "Os campos devem estar preenchido", Toast.LENGTH_LONG).show()
        } else {
            findViewById<Button>(R.id.payment_btn_buy).setOnClickListener {
                val intent = StatusActivity.newInstance(this)
                startActivity(intent)
            }
        }


    }
}