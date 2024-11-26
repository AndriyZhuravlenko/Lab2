package com.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lab2.ui.theme.Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Theme {
                MainActivityScreen()
            }
        }
    }
}

@Composable
fun MainActivityScreen() {
    val itemList = remember { mutableStateListOf<Item>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .weight(7.0f)
                .padding(8.dp)
        ) {
            items(itemList) { item ->
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Item Icon",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Blue
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(text = "Id: ${item.id}", style = MaterialTheme.typography.bodySmall)
                                Text(text = item.name, style = MaterialTheme.typography.bodyLarge)
                                Text(
                                    text = "Description: ${item.description}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )
                            }
                        }
                        IconButton(onClick = { itemList.remove(item) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Item",
                                tint = Color.Red
                            )
                        }
                    }
                }
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .weight(3.0f)
                .padding(8.dp)
                .background(Color(0xFFE0F7FA))
        ) {
            val textFieldId = remember { mutableStateOf("") }
            val textFieldName = remember { mutableStateOf("") }
            val textFieldDescription = remember { mutableStateOf("") }

            TextField(
                value = textFieldId.value,
                onValueChange = { textFieldId.value = it },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                label = { Text("ID") }
            )
            TextField(
                value = textFieldName.value,
                onValueChange = { textFieldName.value = it },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                label = { Text("Name") }
            )
            TextField(
                value = textFieldDescription.value,
                onValueChange = { textFieldDescription.value = it },
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                label = { Text("Description") }
            )
            Button(
                onClick = {
                    if (textFieldId.value.isNotEmpty() && textFieldName.value.isNotEmpty()) {
                        itemList.add(
                            Item(
                                id = textFieldId.value,
                                name = textFieldName.value,
                                description = textFieldDescription.value
                            )
                        )
                        textFieldId.value = ""
                        textFieldName.value = ""
                        textFieldDescription.value = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Add Item")
            }
        }
    }
}

data class Item(
    val id: String,
    val name: String,
    val description: String
)

@Preview
@Composable
fun MainActivityPreview() {
    Lab2Theme {
        MainActivityScreen()
    }
}
