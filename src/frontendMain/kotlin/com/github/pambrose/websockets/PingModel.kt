package com.github.pambrose.websockets

import io.kvision.remote.getService

object PingModel {

    private val pingService = getService<IPingService>()

    suspend fun ping(message: String): String {
        return pingService.ping(message)
    }
}
