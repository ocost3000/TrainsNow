package com.quicktrain.core.model.reply

import com.quicktrain.core.model.data.Stop

data class ListStopsReply(
    val stops: List<Stop>,
    val nextId: String,
)
