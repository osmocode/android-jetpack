package fr.uge.plutus.ui.field

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.uge.plutus.ui.ant.Ant
import java.text.DateFormat
import java.util.*


@Composable
fun AntDateField(
    date: MutableState<Calendar>
) {
    val dayformat = DateFormat.getDateInstance()
    val timeformat = DateFormat.getTimeInstance(DateFormat.SHORT)

    var day by rememberSaveable {
        mutableStateOf(dayformat.format(Date(date.value.timeInMillis)))
    }

    var time by rememberSaveable {
        mutableStateOf(timeformat.format(Date(date.value.timeInMillis)))
    }

    val dayPickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, y: Int, m: Int, d: Int ->
            date.value.set(Calendar.YEAR, y)
            date.value.set(Calendar.MONTH, m)
            date.value.set(Calendar.DATE, d)
            day  = dayformat.format(Date(date.value.timeInMillis))
        }, date.value.get(Calendar.YEAR), date.value.get(Calendar.MONTH), date.value.get(Calendar.DAY_OF_MONTH)
    )

    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, h: Int, m: Int ->
            date.value.set(Calendar.HOUR, h)
            date.value.set(Calendar.MINUTE, m)
            time = timeformat.format(Date(date.value.timeInMillis))
        }, date.value.get(Calendar.HOUR), date.value.get(Calendar.MINUTE), false
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Choice day",
            color = Ant.colors.secondary_text
        )
        Text(
            text = day,
            fontSize = 40.sp,
            modifier = Modifier.clickable {
                dayPickerDialog.show()
            }
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "Choice hour",
            color = Ant.colors.secondary_text
        )
        Text(
            text = time,
            fontSize = 40.sp,
            modifier = Modifier.clickable {
                timePickerDialog.show()
            }
        )
    }
}
