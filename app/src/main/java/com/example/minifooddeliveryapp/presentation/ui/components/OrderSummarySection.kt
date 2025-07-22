package com.example.minifooddeliveryapp.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.minifooddeliveryapp.domain.model.Order

@Composable
fun OrderSummarySection(order: Order) {
    val deliveryFee = 2.50
    val total = order.totalPrice + deliveryFee

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Order Summary",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                SummaryRow("Subtotal", "$${"%.2f".format(order.totalPrice)}")
                Spacer(modifier = Modifier.height(8.dp))
                SummaryRow("Delivery Fee", "$${"%.2f".format(deliveryFee)}")
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Spacer(modifier = Modifier.height(8.dp))
                SummaryRow("Total", "$${"%.2f".format(total)}", isBold = true)
            }
        }
    }
}

@Composable
private fun SummaryRow(label: String, value: String, isBold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = if (isBold) MaterialTheme.typography.bodyLarge
            else MaterialTheme.typography.bodyMedium
        )
        Text(
            text = value,
            style = if (isBold) MaterialTheme.typography.bodyLarge
            else MaterialTheme.typography.bodyMedium
        )
    }
}