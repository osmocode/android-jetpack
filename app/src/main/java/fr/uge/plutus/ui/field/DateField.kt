package fr.uge.plutus.ui.field

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DateFormat
import java.util.*


@Composable
fun AntDateField(
    date: MutableState<Calendar>
) {
    val formatter = DateFormat.getDateInstance()
    var fecha by rememberSaveable {
        mutableStateOf(formatter.format(Date(date.value.timeInMillis)))
    }

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year: Int, month: Int, day: Int ->
            val tmpCalendar = Calendar.getInstance()
            tmpCalendar.set(year, month, day)
            date.value = tmpCalendar
            fecha = formatter.format(Date(date.value.timeInMillis))
        }, date.value.get(Calendar.YEAR), date.value.get(Calendar.MONTH), date.value.get(Calendar.DAY_OF_MONTH)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = fecha,
            fontSize = 40.sp,
            modifier = Modifier.clickable {
                datePickerDialog.show()
            }
        )
    }
}

@Composable
fun AntTimeField(
    initHour: Int,
    initMinute: Int
){
    val time = remember { mutableStateOf("") }
    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, hour: Int, minute: Int ->
            time.value = "$hour : $minute"
        }, initHour, initMinute, false
    )
    Row {
        OutlinedTextField(
            value = time.value,
            onValueChange = { time.value = it },
            readOnly = true,
            enabled = false,
            label = { Text(text = "Selected Time") },
            modifier = Modifier.clickable {
                timePickerDialog.show()

            }

        )
        Icon(
            imageVector = Icons.Filled.AccessTime,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clickable {
                    timePickerDialog.show()
                }
                .align(Alignment.CenterVertically)
        )
    }

}