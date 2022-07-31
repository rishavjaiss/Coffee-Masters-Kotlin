package com.android.coffeemasters.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.android.coffeemasters.DataManager
import com.android.coffeemasters.ItemInCart
import com.android.coffeemasters.Product
import com.android.coffeemasters.ui.theme.Primary
import com.android.coffeemasters.ui.theme.Secondary

@Composable
fun OrderPage(dataManager: DataManager) {
    val context = LocalContext.current
    LazyColumn {
        if (dataManager.cart.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "YOUR CART IS EMPTY",
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
        else{
            items(dataManager.cart) { product ->
                CartItem(product, onDelete = {
                    dataManager.cartRemove(it)
                })
            }
            item(){
                Column(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Secondary,
                            contentColor = Color.White
                        ),
                        onClick = {
                            Toast.makeText(context, " Yayyyy! Order Placed", Toast.LENGTH_LONG).show()
                            dataManager.cartClear()
                        },
                    ) {
                        Text("Place Order")
                    }
                }
            }
        }
    }
}

@Composable
fun CartItem(it: ItemInCart, onDelete: (Product)->Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()

    ) {
        Text("${it.quantity}x")
        Text(it.product.name,
            modifier = Modifier.width(150.dp)
        )
        Text("$${it.quantity*it.product.price}",
            modifier = Modifier.width(50.dp)
        )
        Image(
            imageVector = Icons.Filled.Delete,
            contentDescription = "Delete",
            colorFilter = ColorFilter.tint(Primary),
            modifier = Modifier.clickable {
                onDelete(it.product)
            }
        )
    }
}
