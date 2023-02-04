package fr.uge.plutus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.uge.plutus.ui.ant.Ant

@Composable
fun AntTextField(

) {
    var text = remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .background(
                color = Ant.colors.nav_background,
                shape = RoundedCornerShape(10.dp)
            ),
        value = text.value,
        onValueChange = {
            text.value = it
        },
        placeholder = {
            Text(
                text = "Search...",
            )
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(top = 0.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.padding(top = 0.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Clear Icon",
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Ant.colors.nav_item_focus,
            leadingIconColor = Ant.colors.nav_item_focus,
            trailingIconColor = Ant.colors.nav_item_focus,
            cursorColor = Ant.colors.nav_item_focus,
            placeholderColor = Ant.colors.nav_item_focus,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            backgroundColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun AntTextFieldPreview(

) {
    AntTextField()
}