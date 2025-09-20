package com.ocost.trainsnow

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform