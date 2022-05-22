package br.com.pcardozo.ifoodDrone.ui.products

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.pcardozo.ifoodDrone.R
import br.com.pcardozo.ifoodDrone.model.ProductsModel
import br.com.pcardozo.ifoodDrone.ui.PaymentActivity
import org.koin.android.ext.android.inject

class ProductsActivity : AppCompatActivity() {

    private val adapter: ProductsAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val list = mutableListOf<ProductsModel>(
            ProductsModel(
                R.drawable.image_drone,
                "Coxinha",
                "ragazzo",
                13.20
            ),

            ProductsModel(
                R.drawable.image_drone,
                "Coxinha",
                "ragazzo",
                13.20
            ),

            ProductsModel(
                R.drawable.image_drone,
                "Coxinha",
                "ragazzo",
                13.20
            ),

            ProductsModel(
                R.drawable.image_drone,
                "Coxinha",
                "ragazzo",
                13.20
            ),
        )

        adapter.listProducts = list

        findViewById<RecyclerView>(R.id.products_rcv).adapter = adapter

        findViewById<Button>(R.id.products_btn_buy).setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }
    }
}