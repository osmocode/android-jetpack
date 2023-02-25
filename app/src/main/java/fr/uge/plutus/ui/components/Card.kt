package fr.uge.plutus.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Wallpaper
import androidx.compose.runtime.Composable
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

open class AntCardActionItem(
    val icon: ImageVector,
    val color: Color,
    val onClick: () -> Unit,
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AntCard(
    title: String,
    description: String,
    extras: String,
    icon: ImageVector,
    leadingIcon: List<AntCardActionItem> = listOf(),
    trailingIcon: List<AntCardActionItem> = listOf(),
) {
    val size = 50.dp
    val swipe = rememberSwipeableState(initialValue = 0)
    val left = with(LocalDensity.current) {
        antCardActionPadding(
            size = size,
            length = leadingIcon.size
        ).toPx()
    }
    val right = -with(LocalDensity.current) {
        antCardActionPadding(
            size = size,
            length = trailingIcon.size
        ).toPx()
    }
    val fraction:Float = if (swipe.progress.from - swipe.progress.to < 0){
        swipe.progress.fraction
    }
    else if (swipe.progress.from - swipe.progress.to == 0){
        1f
    }
    else {
        1 - swipe.progress.fraction
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
            Row(
                horizontalArrangement = Arrangement.spacedBy(Ant.spacing.small)
            ) {
                leadingIcon.forEach { item ->
                    AntCardAction(
                        icon = item.icon,
                        color = item.color,
                        onClick = item.onClick,
                        size = fraction
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(Ant.spacing.small)
            ) {
                trailingIcon.forEach { item ->
                    AntCardAction(
                        icon = item.icon,
                        color = item.color,
                        onClick = item.onClick,
                        size = fraction
                    )
                }
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
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Ant.colors.primary_text
                    )

                    Text(
                        text = description,
                        fontSize = 12.sp,
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
fun antCardActionPadding(
    size: Dp,
    length: Int
): Dp {
    return (size + Ant.spacing.small)*length + (if (length <= 0) 0.dp else (Ant.spacing.small * (length-1)))
}

@Composable
fun AntCardAction(
    icon: ImageVector,
    color: Color,
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
    AntCard(
        title = "Nouveau",
        description = "07/02/23",
        extras = "3,25€",
        icon = Icons.Outlined.Wallpaper,
        leadingIcon = listOf(
            AntCardActionItem(
                icon = Icons.Outlined.Delete,
                color = Ant.colors.primary_text,
                onClick = {}
            )
        ),
        trailingIcon = listOf(
            AntCardActionItem(
                icon = Icons.Outlined.Edit,
                color = Ant.colors.primary_text,
                onClick = {}
            ),
            AntCardActionItem(
                icon = Icons.Outlined.Edit,
                color = Ant.colors.primary_text,
                onClick = {}
            ),
        )
    )
}