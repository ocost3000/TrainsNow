package com.quicktrain.core.model.request

class ListStopsRequest {
    enum class SearchMode(val value: Int) {
        ID(0),
        DISTANCE(1)
    }
}
