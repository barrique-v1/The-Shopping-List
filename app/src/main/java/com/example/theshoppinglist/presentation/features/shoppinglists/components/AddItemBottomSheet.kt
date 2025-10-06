package com.example.theshoppinglist.presentation.features.shoppinglists.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.theshoppinglist.domain.model.Category
import com.example.theshoppinglist.domain.model.Unit as MeasurementUnit

/**
 * Bottom sheet for adding a new item to a shopping list.
 * Provides input fields for name, quantity, unit, and category.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemBottomSheet(
    onDismiss: () -> Unit,
    onConfirm: (name: String, quantity: Double, unit: MeasurementUnit, category: Category) -> Unit,
    sheetState: SheetState,
    modifier: Modifier = Modifier
) {
    var itemName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var selectedUnit by remember { mutableStateOf(MeasurementUnit.STUECK) }
    var selectedCategory by remember { mutableStateOf(Category.SONSTIGES) }
    var expandedUnit by remember { mutableStateOf(false) }
    var expandedCategory by remember { mutableStateOf(false) }
    var showNameError by remember { mutableStateOf(false) }
    var showQuantityError by remember { mutableStateOf(false) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Artikel hinzufügen",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Item name field
            OutlinedTextField(
                value = itemName,
                onValueChange = {
                    itemName = it
                    showNameError = false
                },
                label = { Text("Artikelname") },
                isError = showNameError,
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            if (showNameError) {
                Text(
                    text = "Bitte geben Sie einen Namen ein",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Quantity and Unit row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Quantity field
                OutlinedTextField(
                    value = quantity,
                    onValueChange = {
                        quantity = it
                        showQuantityError = false
                    },
                    label = { Text("Menge") },
                    isError = showQuantityError,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    singleLine = true,
                    modifier = Modifier.weight(1f)
                )

                // Unit dropdown
                ExposedDropdownMenuBox(
                    expanded = expandedUnit,
                    onExpandedChange = { expandedUnit = it },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = selectedUnit.getDisplayName(),
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Einheit") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedUnit) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expandedUnit,
                        onDismissRequest = { expandedUnit = false }
                    ) {
                        MeasurementUnit.entries.forEach { unit ->
                            DropdownMenuItem(
                                text = { Text(unit.getDisplayName()) },
                                onClick = {
                                    selectedUnit = unit
                                    expandedUnit = false
                                }
                            )
                        }
                    }
                }
            }
            if (showQuantityError) {
                Text(
                    text = "Bitte geben Sie eine gültige Menge ein",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Category dropdown
            ExposedDropdownMenuBox(
                expanded = expandedCategory,
                onExpandedChange = { expandedCategory = it },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedCategory.getDisplayName(),
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Kategorie") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategory) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expandedCategory,
                    onDismissRequest = { expandedCategory = false }
                ) {
                    Category.entries.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category.getDisplayName()) },
                            onClick = {
                                selectedCategory = category
                                expandedCategory = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDismiss) {
                    Text("Abbrechen")
                }
                Button(
                    onClick = {
                        // Validate inputs
                        val nameValid = itemName.isNotBlank()
                        val quantityValue = quantity.toDoubleOrNull()
                        val quantityValid = quantityValue != null && quantityValue > 0

                        showNameError = !nameValid
                        showQuantityError = !quantityValid

                        if (nameValid && quantityValid) {
                            onConfirm(
                                itemName.trim(),
                                quantityValue!!,
                                selectedUnit,
                                selectedCategory
                            )
                            onDismiss()
                        }
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text("Hinzufügen")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
