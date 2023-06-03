package com.github.pambrose.websockets

import com.google.inject.Inject
import io.ktor.server.websocket.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

@Suppress("ACTUAL_WITHOUT_EXPECT")
actual class WsService : IWsService {
  @Inject
  lateinit var wsSession: WebSocketServerSession

  @OptIn(ExperimentalCoroutinesApi::class)
  override suspend fun wsService(input: ReceiveChannel<Int>, output: SendChannel<String>) {
    output.invokeOnClose {
      println("WebSocket connection closed")
    }

    for (i in input) {
      output.send("Got back: $i")
    }
  }
}