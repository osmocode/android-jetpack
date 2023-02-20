package fr.uge.plutus.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AntTopBar(
    title: String = "",
    leadingIcons : List<@Composable () -> Unit>,
    trailingIcons: List<@Composable () -> Unit>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp,
                    horizontal = 11.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row {
                leadingIcons.forEach{
                    it.invoke()
                }
            }
            Row {
                trailingIcons.forEach{
                    it.invoke()
                }
            }
        }
        if (title.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}


@Preview
@Composable
fun MiddleAntTopBarPreview (

) {
    AntTopBar(
        title = "Preview",
        leadingIcons= listOf{
            AntActionButton(
                type = AntActionButtonType.LINK,
                icon = Icons.Default.ArrowBack,
                onClick = { /*TODO*/ }
            )
        },
        trailingIcons = listOf{
            AntActionButton(
                type = AntActionButtonType.PRIMARY,
                icon = Icons.Outlined.Add,
                title = "New Transaction",
                onClick = { /*TODO*/ }
            )
            AntActionButton(
                type = AntActionButtonType.LINK,
                icon = Icons.Default.ArrowForward,
                onClick = { /*TODO*/ }
            )
        }
    )
}

@Preview
@Composable
fun LightAntTopBarPreview(

) {
    AntTopBar(
        leadingIcons = listOf{
            AntActionButton(
                modifier = Modifier.padding(start = 16.dp),
                type = AntActionButtonType.LINK,
                icon = Icons.Default.ArrowBack,
                onClick = { /*TODO*/ }
            )
        },
        trailingIcons = listOf{}
    )
}