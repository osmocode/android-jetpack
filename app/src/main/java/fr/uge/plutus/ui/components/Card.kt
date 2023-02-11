package fr.uge.plutus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.uge.plutus.ui.ant.Ant


@Composable
fun AntCard(
    title : String,
    description : String,
    extras : String,
) {

    Box(
        modifier = Modifier
            .background(color = Color.White)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        color = Ant.colors.nav_background,
                        shape = CircleShape
                    )
            ) {

            }
            Column(modifier = Modifier
                .weight(1f)) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Ant.colors.nav_item_focus
                )

                Text(
                    text = description,
                    fontSize = 16.sp,
                    color = Ant.colors.nav_item_focus
                )
            }

            Text(
                text = extras,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Right,
                color = Ant.colors.nav_item_focus
            )
        }
    }
}


@Preview
@Composable
fun AntCardPreview(){
    AntCard("Nouveau","07/02/23", "3,25€")
}