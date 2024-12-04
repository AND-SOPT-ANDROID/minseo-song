package org.sopt.and.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.presentation.model.HomeImage

class HomeViewModel : ViewModel() {

    private val _homeTopImages = MutableStateFlow<List<HomeImage>>(emptyList())
    val homeTopImages = _homeTopImages.asStateFlow()

    private val _homeRecommendImages = MutableStateFlow<List<HomeImage>>(emptyList())
    val homeRecommendImages = _homeRecommendImages.asStateFlow()

    private val _homeTop20Images = MutableStateFlow<List<HomeImage>>(emptyList())
    val homeTop20Images = _homeTop20Images.asStateFlow()

    private val _homeCategory = MutableStateFlow(
        listOf(
            R.string.new_classic,
            R.string.drama,
            R.string.entertainment,
            R.string.film,
            R.string.animation,
            R.string.abroad_series,
            R.string.current_affairs,
            R.string.kids,
            R.string.film_plus
        )
    )
    val homeCategory = _homeCategory.asStateFlow()

    init {
        loadHomeImages()
    }

    private fun loadHomeImages() {
        viewModelScope.launch {
            _homeTopImages.value = loadHomeTopBanner()
            _homeRecommendImages.value = loadHomeRecommend()
            _homeTop20Images.value = loadHomeTop20()
        }
    }

    private fun loadHomeTopBanner(): List<HomeImage> = listOf(
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231207/0454/P000940460.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231218/1141/P000821660.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20220520/P001602837.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240311/1030/P001516937.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231228/0220/P001751353.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1120/ko/20240720/0254/P001758020.jpg/dims/resize/F_webp,400"
        )
    )

    private fun loadHomeRecommend(): List<HomeImage> = listOf(
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1130/ko/20240826/0109/P001759473.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1130/ko/20240118/0754/P001749924.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1110/ko/20241021/0224/P001762002.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240906/0356/P001761238.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1120/ko/20241021/0222/P001761804.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240924/0141/P001762001.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://www.chosun.com/resizer/v2/HRGER65PGPIW36FJOBRNAP2PJM.jpg?auth=9da0c167de2cfb03a5d344ce4098faa669a22d7a2b90cb6a21fdc518b0af3558&width=530&height=757&smart=true"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1180/ko/20241021/0222/P001761804.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240823/0407/P001760705.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20241006/0201/P001758792.jpg/dims/resize/F_webp,400"
        )
    )

    private fun loadHomeTop20(): List<HomeImage> = listOf(
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1140/ko/20241021/0225/P001762001.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231025/1510/P001492081.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1160/ko/20241021/0222/P001761804.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240906/0356/P001761238.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1160/ko/20241021/0224/P001762002.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240814/1707/P001760343.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240415/1250/P001609758.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240920/0511/P001761839.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231026/1510/P001632868.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240812/0635/P001760278.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1140/ko/20241021/0225/P001762001.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231025/1510/P001492081.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1160/ko/20241021/0222/P001761804.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240906/0356/P001761238.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP1160/ko/20241021/0224/P001762002.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240814/1707/P001760343.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240415/1250/P001609758.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240920/0511/P001761839.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20231026/1510/P001632868.jpg/dims/resize/F_webp,400"
        ),
        HomeImage(
            title = "",
            image = "https://image.tving.com/ntgs/contents/CTC/caip/CAIP0900/ko/20240812/0635/P001760278.jpg/dims/resize/F_webp,400"
        )
    )
}