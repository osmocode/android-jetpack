package fr.uge.plutus.ui.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.uge.plutus.data.model.Tag
import fr.uge.plutus.ui.ant.Ant

@Composable
fun AntTagInput(
    tags: List<Tag>,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = Ant.shapes.default)
            .background(color = Ant.colors.gray_1)
            .clickable(onClick = onClick)
            .padding(
                vertical = Ant.spacing.small,
                horizontal = Ant.spacing.default
            ),
        horizontalArrangement = Arrangement.spacedBy(Ant.spacing.default),
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Ant.colors.gray_4,
                    shape = CircleShape
                )
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = Icons.Outlined.Sell,
                contentDescription = "Tags Icon",
                tint = Ant.colors.gray_8
            )
        }
        Column {
            Text(
                text = "Select tag",
                color = Ant.colors.secondary_text
            )
            if (tags.isEmpty()) {
                Text(
                    text = "Empty",
                    fontSize = 10.sp,
                    color = Ant.colors.secondary_text
                )
            } else {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Ant.spacing.small),
                    content = {
                        items(tags) { tag ->
                            Text(
                                modifier = Modifier
                                    .clip(Ant.shapes.default)
                                    .background(color = Ant.colors.gray_4)
                                    .padding(horizontal = Ant.spacing.small),
                                text = tag.label,
                                fontSize = 10.sp,
                                color = Ant.colors.secondary_text
                            )
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun AntTagInputPreview(

) {
    AntTagInput(
        onClick = {},
        tags = listOf(
            Tag(
                tagId = null,
                label = "Demo",
                type = "CREDIT"
            ),
            Tag(
                tagId = null,
                label = "Other",
                type = "CREDIT"
            )
        )
    )
}