package com.github.pambrose.kvision_websockets

import com.google.inject.Inject
import io.ktor.server.websocket.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

@Suppress("ACTUAL_WITHOUT_EXPECT")
actual class WsService : IWsService {
  @Inject
  lateinit var wsSession: WebSocketServerSession

  override suspend fun sendIntReceiveString(input: ReceiveChannel<Int>, output: SendChannel<String>) {
    for (i in input) {
      output.send("Got back: $i")
    }
  }
}