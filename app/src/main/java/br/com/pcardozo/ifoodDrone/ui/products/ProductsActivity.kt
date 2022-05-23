package br.com.pcardozo.ifoodDrone.ui.products

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.com.pcardozo.ifoodDrone.R
import br.com.pcardozo.ifoodDrone.model.ProductsModel
import org.koin.android.ext.android.inject

class ProductsActivity : AppCompatActivity() {

    private val adapter: ProductsAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val list = mutableListOf<ProductsModel>(
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
                R.drawable.image_drone,
                "Bolo",
                "Sulani",
                63.20,
                1
            ),
        )

        adapter.listProducts = list

        findViewById<RecyclerView>(R.id.products_rcv).adapter = adapter

        findViewById<Button>(R.id.products_btn_buy).setOnClickListener {
            var valueTotal = 0.0
            adapter.listProducts.forEachIndexed { position, item ->
                valueTotal += (item.price * item.amount)
            }
            Toast.makeText(this, valueTotal.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}