package br.com.pcardozo.ifoodDrone.ui.products

import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.pcardozo.ifoodDrone.R
import br.com.pcardozo.ifoodDrone.extensions.inflate
import br.com.pcardozo.ifoodDrone.model.ProductsModel

private const val VIEW_ID = R.layout.item_product

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    var listProducts: MutableList<ProductsModel> = mutableListOf()
        set(value) {
            val oldSize = field.size
            val newSize = value.size
            field.addAll(value)

            notifyItemRangeInserted(oldSize, newSize)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductsViewHolder(parent)

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) =
        holder.bind(listProducts[position], position)

    override fun getItemCount(): Int = listProducts.size

    inner class ProductsViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(VIEW_ID)) {

        fun bind(productsModel: ProductsModel, position: Int) = with(itemView) {
            findViewById<ImageView>(R.id.item_products_img).setImageResource(productsModel.image)

            findViewById<TextView>(R.id.item_products_txt_name).text = productsModel.name
            findViewById<TextView>(R.id.item_products_txt_store).text = productsModel.store
            findViewById<TextView>(R.id.item_products_txt_price).text =
                productsModel.price.toString()

            val txtAmount = findViewById<TextView>(R.id.item_products_txt_amount)

            var amount = txtAmount.text.toString().toInt()


            findViewById<Button>(R.id.item_products_btn_add).setOnClickListener {
                amount++
                txtAmount.text = amount.toString()
                productsModel.amount = amount
            }

            findViewById<Button>(R.id.item_products_btn_remove).setOnClickListener {
                if (amount > 0){
                    amount--
                    txtAmount.text = amount.toString()
                    productsModel.amount = amount
                }

            }
        }


    }
}