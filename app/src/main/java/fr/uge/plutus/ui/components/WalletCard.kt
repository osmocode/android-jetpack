package fr.uge.plutus.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import fr.uge.plutus.ui.ant.Ant

open class AntWalletCardItem(
    val label: String,
    val desc: String,
    val selected: Boolean
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AntWalletCardList(
    items: List<AntWalletCardItem>,
    selected: (index: Int) -> Unit
) {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState.currentPage) {
        selected.invoke(pagerState.currentPage)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            count = items.size,
            contentPadding = PaddingValues(Ant.spacing.small),
            modifier = Modifier.height(230.dp),
            content = { index ->
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
    item: AntWalletCardItem,
) {
    Box(
        modifier = Modifier
            .aspectRatio(ratio = 1.59f)
            .padding(Ant.spacing.small)
            .clip(Ant.shapes.default)
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Ant.colors.primary_color_3,
                        Ant.colors.primary_color_5
                    )
                )
            )
            .padding(Ant.spacing.default)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.label,
                    fontSize = 18.sp,
                    color = Color.White
                )
                if (item.selected) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.Black.copy(alpha = 0.3f),
                                shape = CircleShape
                            )
                            .padding(
                                vertical = Ant.spacing.small / 2,
                                horizontal = Ant.spacing.small
                            )
                    ) {
                        Text(
                            text = "Selected",
                            fontSize = 10.sp,
                            color = Color.White
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "$ 1000",
                textAlign = TextAlign.End,
                fontSize = 40.sp,
                fontWeight = FontWeight.Light,
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun AntWalletCardPreview(

) {
    val wallets = listOf(
        AntWalletCardItem(
            label = "Personal",
            desc = "Current",
            selected = true
        ),
        AntWalletCardItem(
            label = "Pro",
            desc = "Not used",
            selected = false
        ),
    )
    AntWalletCardList(
        items = wallets,
        selected = {}
    )
}