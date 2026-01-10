package com.trasbd.ringbridge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class PermissionsRationaleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(modifier = Modifier.padding(32.dp)) {
                Text(
                    text = "Health Data Access",
                    style = MaterialTheme.typography.headlineMedium
                )

                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text =
                        "RingBridge connects to your smart ring and saves your health data " +
                                "to Health Connect so it can be viewed alongside other health apps.\n\n" +
                                "Data accessed includes:\n" +
                                "• Heart rate\n" +
                                "• Sleep sessions\n" +
                                "• Blood oxygen\n" +
                                "• Heart rate variability\n\n" +
                                "All data remains under your control and can be revoked at any time " +
                                "through Health Connect settings."
                )
            }
        }
    }
}
