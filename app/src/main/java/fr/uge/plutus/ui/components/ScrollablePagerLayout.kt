package fr.uge.plutus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import fr.uge.plutus.ui.ant.Ant
import kotlinx.coroutines.launch

open class ScrollablePagerLayoutItem(
    val label : String,
    val content : @Composable (page : Int) -> Unit
)


@OptIn(ExperimentalPagerApi::class)
@Composable
fun AntScrollablePagerLayout(
    pages: List<ScrollablePagerLayoutItem>
){
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column {
        ScrollableTabRow(
            backgroundColor = Color.Transparent,
            selectedTabIndex = pagerState.currentPage,
            contentColor = Color.Black,
            edgePadding = 0.dp,
            divider = {},
            indicator = { tabPositions ->
                AntScrollablePagerLayoutIndicator(
                    modifier = Modifier
                        .pagerTabIndicatorOffset(
                            pagerState= pagerState,
                            tabPositions = tabPositions
                        )
                )
            },
            modifier = Modifier
                .height(42.dp)
                .padding(horizontal = 10.dp)
        ) {
            pages.forEachIndexed{ index, page ->
                val selected = pagerState.currentPage == index
                Tab(
                    modifier = Modifier
                        .zIndex(2f)
                        .padding(3.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    selected = selected,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    selectedContentColor = Ant.colors.primary_text,
                    unselectedContentColor = Ant.colors.secondary_text
                ){
                    Box(modifier = Modifier.fillMaxSize()){
                        Text(
                            text = page.label,
                            modifier = Modifier.align(Alignment.Center),
                        )
                    }
                }
            }
        }
        HorizontalPager(
            modifier = Modifier.background(color = Color.Transparent),
            state = pagerState,
            count = pages.size,
            content = {page ->
                AntScrollablePagerLayoutPage(
                    page = page,
                    pages = pages)

            }
        )
    }
}

@Composable
fun AntScrollablePagerLayoutPage(
    page: Int,
    pages: List<ScrollablePagerLayoutItem>
){
    pages[page].content(page)
}

@Composable
fun AntScrollablePagerLayoutIndicator(
    modifier: Modifier = Modifier
){
    Box(modifier = modifier
        .fillMaxSize()
        .padding(3.dp)
        .clip(shape = RoundedCornerShape(10.dp))
        .background(color = Ant.colors.gray_1)
    )
}