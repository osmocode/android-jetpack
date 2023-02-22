package fr.uge.plutus.ui.components

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FileCopy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.uge.plutus.ui.ant.Ant
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun AntCard(
    title : String,
    description : String,
    extras : String,
) {

    val swipe = rememberSwipeableState(initialValue = 0)
    val left = with(LocalDensity.current) { 60.dp.toPx() }
    val right = -with(LocalDensity.current) { 130.dp.toPx() }
    Log.println(Log.ASSERT, "SWIPE", swipe.progress.toString())
    var fraction:Float
    if (swipe.progress.from - swipe.progress.to < 0){
        fraction = swipe.progress.fraction
    }
    else if (swipe.progress.from - swipe.progress.to == 0){
        fraction = 1f
    }
    else{
        fraction = 1 - swipe.progress.fraction
    }

    Box(
        modifier = Modifier
            .padding(Ant.spacing.default)
            .height(50.dp)
            .fillMaxWidth()
            .swipeable(
                state = swipe,
                anchors = mapOf(
                    0f to 0,
                    left to 1,
                    right to 2
                ),
                thresholds = { _, _ ->
                    FractionalThreshold(0.3f)
                },
                orientation = Orientation.Horizontal
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .alpha(fraction),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            AntCardAction(
                icon = Icons.Outlined.Delete,
                onClick = {},
                size = fraction
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(Ant.spacing.default)
            ) {
                AntCardAction(
                    icon = Icons.Outlined.Edit,
                    onClick = {},
                    size = fraction
                )
                AntCardAction(
                    icon = Icons.Outlined.FileCopy,
                    onClick = {},
                    size = fraction
                )
            }
        }
        Box(modifier = Modifier
            .offset {
                IntOffset(swipe.offset.value.roundToInt(), 0)
            }
            .background(color = Ant.colors.background)
            .fillMaxSize()
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Ant.spacing.default),
                verticalAlignment = Alignment.Top
            ){
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(
                            color = Ant.colors.gray_5,
                            shape = CircleShape
                        )
                )
                Column(
                    modifier = Modifier.weight(1f)
                ){
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Ant.colors.primary_text
                    )

                    Text(
                        text = description,
                        fontSize = 16.sp,
                        color = Ant.colors.secondary_text
                    )
                }
                Text(
                    text = extras,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Right,
                    color = Ant.colors.primary_text
                )
            }
        }
    }
}

@Composable
fun AntCardAction(
    icon: ImageVector,
    color: Color = Ant.colors.primary_text,
    onClick: () -> Unit,
    size: Float
) {
    val sizeIcon = 12.dp + (size * 12).dp
    val sizeBox = 25.dp + (size * 25).dp
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Ant.colors.gray_5)
            .size(sizeBox)
            .clickable(onClick = onClick)
    ) {
        Icon(
            modifier = Modifier
                .align(Alignment.Center)
                .size(sizeIcon),
            imageVector = icon,
            contentDescription = null,
            tint = color
        )
    }
}

@Preview
@Composable
fun AntCardPreview(){
    AntCard("Nouveau","07/02/23", "3,25€")
}