package fr.uge.plutus.ui.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.DateFormat.getDateInstance
import java.util.*

@Preview(showBackground = true)
@Composable
fun DatepickerComponent() {

    var fecha by rememberSaveable {
        mutableStateOf("")
    }
    val formater = getDateInstance()
    val year: Int
    val month: Int
    val day: Int
    val mCalendar = Calendar.getInstance()
    year = mCalendar.get(Calendar.YEAR)
    month = mCalendar.get(Calendar.MONTH)
    day = mCalendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year: Int, month: Int, day: Int ->
            val tmpCalendar = Calendar.getInstance()
            tmpCalendar.set(year, month, day)
            fecha = formater.format(Date(tmpCalendar.timeInMillis))/*"$day/${month+1}/$year"*/
        }, year, month, day
    )

    Row {
        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            readOnly = true,
            label = { Text(text = "Select Date") },

            )
        Icon(
            imageVector = Icons.Filled.DateRange,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clickable {
                    datePickerDialog.show()
                }
                .align(CenterVertically)
        )
    }
}

@Composable
fun ShowTimePicker(context: Context, initHour: Int, initMinute: Int) {
    val time = remember { mutableStateOf("") }
    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            time.value = "$hour : $minute"
        }, initHour, initMinute, false
    )

    Row {
        OutlinedTextField(
            value = time.value,
            onValueChange = { time.value = it },
            readOnly = true,
            label = { Text(text = "Selected Time") },

            )
        Icon(
            imageVector = Icons.Filled.AccessTime,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clickable {
                    timePickerDialog.show()
                }
                .align(CenterVertically)
        )
    }


}