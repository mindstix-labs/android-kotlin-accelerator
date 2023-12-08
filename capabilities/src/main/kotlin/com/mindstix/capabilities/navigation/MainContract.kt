package com.mindstix.capabilities.navigation

data class State(
    val displayToolBar: Boolean = false,
    val displayBottomNavigationBar: Boolean = false,
)

/*sealed class Event {
    data class SetMarket(val market: MarketModel?, val contentType: ContentType) : Event()
}*/
