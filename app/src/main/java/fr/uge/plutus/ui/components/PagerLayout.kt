package fr.uge.plutus.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.google.accompanist.pager.*

import fr.uge.plutus.ui.ant.Ant
import kotlinx.coroutines.launch

open class AntPagerLayoutItem(
    val label: String,
    val content: @Composable (page: Int) -> Unit
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AntPagerLayout(
    pages: List<AntPagerLayoutItem>,
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabRow(
            contentColor = Ant.colors.primary_color_2,
            backgroundColor = Ant.colors.gray_5,
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                AntPagerLayoutIndicator(
                    modifier = Modifier
                        .pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            },
            modifier = Modifier
                .height(35.dp)
                .padding(horizontal = Ant.spacing.default)
                .clip(shape = Ant.shapes.default),
        ) {
            pages.forEachIndexed { index, page ->
                val selected = pagerState.currentPage == index
                Tab(
                    modifier = Modifier
                        .zIndex(1f)
                        .clip(shape = Ant.shapes.default),
                    selected = selected,
                    onClick = {
                        scope.launch {
                            pagerState.scrollToPage(index)
                            // Animated option
                            // pagerState.animateScrollToPage(index)
                        }
                    },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Ant.colors.primary_text
                ){
                    Text(text = page.label,)
                }
            }
        }
        HorizontalPager(
            state = pagerState,
            count = pages.size,
            modifier = Modifier.fillMaxSize(),
            content = { page ->
                AntPagerLayoutPage(
                    page = page,
                    pages = pages
                )
            }
        )
    }
}


@Composable
fun AntPagerLayoutIndicator(
    modifier: Modifier = Modifier,
    color: Color = Ant.colors.primary_color_5,
) {
    Box(
        modifier
            .fillMaxSize()
            .padding(3.dp)
            .clip(shape = RoundedCornerShape(7.dp))
            .background(color = color)
    )
}

@Composable
fun AntPagerLayoutPage(
    page: Int,
    pages: List<AntPagerLayoutItem>
) {
    pages[page].content(page)
}
