package fr.uge.plutus.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import fr.uge.plutus.ui.ant.Ant

open class AntWalletCardItem(
    val label: String,
    val desc: String
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AntWalletCardList(
    items: List<AntWalletCardItem>
) {
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            count = items.size,
            contentPadding = PaddingValues(Ant.spacing.default),
            modifier = Modifier.height(230.dp),
            content = {index ->
                AntWalletCard(
                    item = items[index],
                )
            }
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(Ant.spacing.default),
        ) {
            for (i in items.indices) {
                val color = animateColorAsState(
                    targetValue = if (pagerState.currentPage == i) Ant.colors.primary_color_5 else Ant.colors.gray_5
                )
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(
                            color = color.value,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}


@Composable
fun AntWalletCard(
    item: AntWalletCardItem
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Ant.spacing.small)
            .clip(Ant.shapes.default)
            .background(color = Color.LightGray)
            .padding(Ant.spacing.default)
    ) {
        Text(
            text = item.label
        )
    }
}

@Preview
@Composable
fun AntWalletCardPreview(

) {
    val wallets = listOf(
        AntWalletCardItem(
            label = "Personal",
            desc = "Current"
        ),
        AntWalletCardItem(
            label = "Pro",
            desc = "Not used"
        ),
    )
    AntWalletCardList(
        items = wallets
    )
}