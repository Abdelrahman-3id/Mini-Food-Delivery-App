package com.example.minifooddeliveryapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.minifooddeliveryapp.domain.model.Order
import com.example.minifooddeliveryapp.domain.model.OrderStatus

@Composable
fun OrderStatusSection(order: Order) {
    val statusSteps = listOf(
        OrderStatus.PLACED to "Order Placed",
        OrderStatus.PREPARING to "Preparing",
        OrderStatus.OUT_FOR_DELIVERY to "On the Way",
        OrderStatus.DELIVERED to "Delivered"
    )

    val currentStepIndex = statusSteps.indexOfFirst { it.first == order.status }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.medium
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Order Status",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        statusSteps.forEachIndexed { index, (status, label) ->
            StatusStep(
                label = label,
                isCompleted = index < currentStepIndex,
                isCurrent = index == currentStepIndex,
                isLast = index == statusSteps.lastIndex
            )
        }
    }
}

@Composable
private fun StatusStep(
    label: String,
    isCompleted: Boolean,
    isCurrent: Boolean,
    isLast: Boolean
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val surfaceColor = MaterialTheme.colorScheme.surface
    val outlineColor = MaterialTheme.colorScheme.outline

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        // Step indicator
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(
                    color = when {
                        isCompleted -> primaryColor
                        isCurrent -> surfaceColor
                        else -> surfaceColor
                    },
                    shape = CircleShape
                )
                .border(
                    width = if (isCurrent) 2.dp else 1.dp,
                    color = when {
                        isCompleted -> primaryColor
                        isCurrent -> primaryColor
                        else -> outlineColor.copy(alpha = 0.5f)
                    },
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isCompleted) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completed",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(16.dp)
                )
            } else if (isCurrent) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Current",
                    tint = primaryColor,
                    modifier = Modifier.size(12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = when {
                isCompleted || isCurrent -> MaterialTheme.colorScheme.onSurface
                else -> MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            }
        )
    }

    if (!isLast) {
        Divider(
            modifier = Modifier
                .height(24.dp)
                .width(1.dp)
                .padding(start = 12.dp),
            color = if (isCompleted) primaryColor else outlineColor.copy(alpha = 0.2f)
        )
    }
}