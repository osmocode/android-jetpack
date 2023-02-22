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
import fr.uge.plutus.ui.ant.Ant

@Composable
fun AntTopBar(
    title: String = "",
    leadingIcons : List<@Composable () -> Unit> = listOf(),
    trailingIcons: List<@Composable () -> Unit> = listOf()
) {
    Column (
        modifier = Modifier
            .padding(
                top = 20.dp,
                bottom = 10.dp
            ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Ant.spacing.default - 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row (
                horizontalArrangement = Arrangement.spacedBy(Ant.spacing.small)
            ) {
                leadingIcons.forEach{
                    it.invoke()
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(Ant.spacing.small)
            ){
                trailingIcons.forEach{
                    it.invoke()
                }
            }
        }
        if (title.isNotEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Ant.spacing.default),
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Ant.colors.primary_text
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
                type = AntActionButtonType.LINK,
                icon = Icons.Default.ArrowBack,
                onClick = { /*TODO*/ }
            )
        },
        trailingIcons = listOf{}
    )
}