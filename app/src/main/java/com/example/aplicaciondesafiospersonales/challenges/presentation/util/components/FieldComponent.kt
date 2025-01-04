import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FieldComponent(
    title: String,
    textState: MutableState<String>,
    isDropdown: Boolean = false,
    options: List<String>? = null,
    isDateField: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Lógica para DatePickerDialog
        if (isDateField) {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Establecer la fecha mínima como la fecha actual
            calendar.set(year, month, day)
            val currentDateInMillis = calendar.timeInMillis

            OutlinedTextField(
                value = textState.value,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clickable {
                        DatePickerDialog(
                            context,
                            { _, selectedYear, selectedMonth, selectedDay ->
                                textState.value = String.format(
                                    Locale.getDefault(),
                                    "%02d/%02d/%04d",
                                    selectedDay,
                                    selectedMonth + 1,
                                    selectedYear
                                )
                            },
                            year, month, day
                        )
                            .apply {
                                // Restringir la fecha mínima
                                datePicker.minDate = currentDateInMillis
                            }
                            .show()
                    },
                shape = RoundedCornerShape(11.dp),
                trailingIcon = {
                    IconButton(onClick = {
                        DatePickerDialog(
                            context,
                            { _, selectedYear, selectedMonth, selectedDay ->
                                textState.value = String.format(
                                    Locale.getDefault(),
                                    "%02d/%02d/%04d",
                                    selectedDay,
                                    selectedMonth + 1,
                                    selectedYear
                                )
                            },
                            year, month, day
                        ).apply {
                            // Restringir la fecha mínima
                            datePicker.minDate = currentDateInMillis
                        }
                            .show()
                    }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Calendar"
                        )
                    }
                }
            )
        }
        // Lógica para DropdownMenu
        else if (isDropdown && options != null) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                OutlinedTextField(
                    value = textState.value,
                    onValueChange = { textState.value = it },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .menuAnchor(),
                    shape = RoundedCornerShape(11.dp),
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Dropdown",
                            modifier = Modifier.clickable { expanded = !expanded }
                        )
                    }
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.sorted().forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                textState.value = option
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
        // Lógica para un campo de texto normal
        else {
            OutlinedTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(11.dp)
            )
        }
    }
}
