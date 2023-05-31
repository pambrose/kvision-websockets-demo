package com.github.pambrose.kvision_websockets

import io.kvision.html.Span
import io.kvision.panel.VPanel
import io.kvision.remote.getService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

object WsModel {

  private val service = getService<IWsService>()
  private var connected = false

  @OptIn(ExperimentalCoroutinesApi::class)
  fun connectToWebSocket(msgPanel: VPanel) {
    if (!connected) {
      connected = true
      AppScope.launch {
        msgPanel.add(Span("Connecting to WebSocket"))
        service.sendIntReceiveString() { output: SendChannel<Int>, input: ReceiveChannel<String> ->
          output.invokeOnClose {
            msgPanel.add(Span("WebSocket connection closed"))
          }
          coroutineScope {
            launch {
              while (connected) {
                val i = Random.nextInt()
                output.send(i)
                delay(1.seconds)
              }
              input.cancel()
            }

            launch {
              for (str in input) {
                msgPanel.add(Span(str))
              }
            }
          }
        }
      }
    }
  }

  fun disconnectFromWebSocket() {
    connected = false
  }
}