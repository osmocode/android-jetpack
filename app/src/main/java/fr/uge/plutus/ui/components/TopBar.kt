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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AntTopBar(
    title: String? = null,
    backIcon: ImageVector? = null,
    backOnClick: () -> Unit,
    trailingIcon: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp),
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 3.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (backIcon != null) {
                IconButton(
                    onClick = backOnClick
                ) {
                    Icon(
                        imageVector = backIcon,
                        contentDescription = null
                    )
                }
            }
            trailingIcon()
        }
        if (title != null) {
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
        }
        Box (
            modifier = Modifier.fillMaxSize(),
        ) {
            content()
        }
    }
}


@Preview
@Composable
fun MiddleAntTopBarPreview (

) {
    AntTopBar(
        title = "Preview",
        backIcon = Icons.Default.ArrowBack,
        backOnClick = {},
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

@Preview
@Composable
fun LightAntTopBarPreview(

) {
    AntTopBar(
        backIcon = Icons.Default.ArrowBack,
        backOnClick = {

        }
    )
}