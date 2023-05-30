package com.github.pambrose.kvision_websockets

import io.kvision.html.Span
import io.kvision.panel.VPanel
import io.kvision.remote.getService
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

object WsModel {

  private val service = getService<IWsService>()
  private var connected = false

  fun connectToWebSocket(msgPanel: VPanel) {
    if (!connected) {
      connected = true
      AppScope.launch {
        msgPanel.add(Span("Connecting to WebSocket"))
        service.sendIntReceiveString() { output /*: SendChannel<Int>*/, input /*: ReceiveChannel<String>*/ ->
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
                println(str)
              }
            }
          }
          msgPanel.add(Span("Disconnected from WebSocket"))
        }
      }
    }
  }

  fun disconnectFromWebSocket() {
    connected = false
  }
}