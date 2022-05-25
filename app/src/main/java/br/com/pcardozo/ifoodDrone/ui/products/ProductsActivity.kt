package br.com.pcardozo.ifoodDrone.ui.products

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.pcardozo.ifoodDrone.R
import br.com.pcardozo.ifoodDrone.model.CepModel
import br.com.pcardozo.ifoodDrone.model.ProductsModel
import br.com.pcardozo.ifoodDrone.ui.PaymentActivity
import org.koin.android.ext.android.inject

private const val EXTRA_CEP = "EXTRA_CEP"

class ProductsActivity : AppCompatActivity() {

    private lateinit var cep: CepModel

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, ProductsActivity::class.java)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private val adapter: ProductsAdapter by inject()

    private val btnBuy: Button by lazy { findViewById(R.id.products_btn_buy) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        intent.getParcelableExtra<CepModel>(EXTRA_CEP)?.let {
            cep = it
        }

        val list = mutableListOf(
            ProductsModel(
                R.drawable.coxinha,
                "Coxinha",
                "ragazzo",
                3.99,
                1
            ),

            ProductsModel(
                R.drawable.rosquinha,
                "rosquinha",
                "Donnuts",
                5.50,
                1
            ),

            ProductsModel(
                R.drawable.hamburguer,
                "Hamburger",
                "MCDonalds",
                20.0,
                1
            ),

            ProductsModel(
                R.drawable.bolo,
                "Bolo",
                "Sulani",
                63.20,
                1
            ),
        )

        adapter.listProducts = list

        findViewById<RecyclerView>(R.id.products_rcv).adapter = adapter


        btnBuy.setOnClickListener {
            var valueTotal = 0.0
            adapter.listProducts.forEach { item -> valueTotal += (item.price * item.amount) }
            if (valueTotal <= 0) {
                Toast.makeText(this, "Adicione algum produto", Toast.LENGTH_SHORT).show()
            } else {
                val intent = PaymentActivity.newInstance(this, valueTotal)
                startActivity(intent)
            }

        }
    }
}