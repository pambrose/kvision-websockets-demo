package com.github.pambrose.kvision_websockets

import io.kvision.annotations.KVService
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

@KVService
interface IWsService {
  // Server side
  suspend fun wsService(input: ReceiveChannel<Int>, output: SendChannel<String>) {
  }

  // Client side
  suspend fun wsService(handler: suspend (SendChannel<Int>, ReceiveChannel<String>) -> Unit) {
  }
}