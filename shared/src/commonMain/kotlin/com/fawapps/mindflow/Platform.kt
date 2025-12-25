package com.fawapps.mindflow

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform