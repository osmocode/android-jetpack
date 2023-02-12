package fr.uge.plutus.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController

@Composable
fun AntTopBar(
    title: String,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp),
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 3.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon()
            trailingIcon()
        }
        Text(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 10.dp
                ),
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Box (
            modifier = Modifier.fillMaxSize(),
        ) {
            content()
        }
    }
}


@Preview
@Composable
fun AntTopBarPreview (

) {
    val navController = rememberNavController()
    AntTopBar(
        title = "Preview",
        leadingIcon = {
            IconButton(
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back"
                )
            }
        },
        trailingIcon = {
            AntActionButton(
                modifier = Modifier.padding(end = 16.dp),
                type = AntActionButtonType.PRIMARY,
                icon = Icons.Outlined.Add,
                title = "New Transaction",
                onClick = { /*TODO*/ }
            )
        }
    )
}