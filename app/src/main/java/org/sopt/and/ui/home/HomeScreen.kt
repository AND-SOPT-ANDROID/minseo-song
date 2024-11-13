package org.sopt.and.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.delay
import org.sopt.and.R
import org.sopt.and.component.bar.TopBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val homeViewModel: HomeViewModel = viewModel()

    val hometopimages by homeViewModel.homeTopImages.collectAsState()
    val homerecommendimages by homeViewModel.homeRecommendImages.collectAsState()
    val hometop20images by homeViewModel.homeTop20Images.collectAsState()
    val homeCategory by homeViewModel.homeCategory.collectAsState()

    val pagerState = rememberPagerState(pageCount = { hometopimages.size })

    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % hometopimages.size
            pagerState.animateScrollToPage(nextPage)
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        item {
            TopBar()
        }

        stickyHeader {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(
                    count = homeCategory.size,
                    key = { item -> homeCategory[item] }
                ) { category ->
                    Text(
                        text = stringResource(homeCategory[category]),
                        color = Color.Gray
                    )
                }
            }
        }

        item {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 20.dp),
                modifier = Modifier.padding(vertical = 20.dp)
            ) { index ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .size(width = 360.dp, height = 480.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(hometopimages[index].image)
                            .crossfade(true)
                            .build(),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )

                    Text(
                        text = stringResource(
                            R.string.image_counter,
                            index + 1,
                            hometopimages.size
                        ),
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp)
                            .background(
                                color = Color.Black.copy(alpha = 0.7f),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }

        item {
            Column(
                modifier = Modifier.padding(top = 15.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.home_editor_recommend),
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 20.dp, top = 15.dp, bottom = 15.dp)
                    )

                    Image(
                        painter = painterResource(R.drawable.baseline_navigate_next_24),
                        contentDescription = "",
                        modifier = Modifier.size(35.dp)
                    )
                }

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    items(
                        count = homerecommendimages.size,
                        key = { index -> homerecommendimages[index].image }
                    ) { index ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(homerecommendimages[index].image)
                                .crossfade(true)
                                .build(),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .size(160.dp, 240.dp)
                        )
                    }
                }
            }
        }

        item {
            Column(
                modifier = Modifier.padding(top = 15.dp)
            ) {
                Text(
                    text = stringResource(R.string.home_today_top_20),
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp, top = 15.dp, bottom = 15.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    items(
                        count = hometop20images.size,
                        key = { index -> hometop20images[index].image }
                    ) { index ->
                        Box(
                            contentAlignment = Alignment.BottomStart
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(hometop20images[index].image)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(bottom = 35.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .size(160.dp, 240.dp)
                            )
                            Text(
                                text = (index + 1).toString(),
                                fontSize = 60.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
