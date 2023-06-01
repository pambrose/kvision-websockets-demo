package com.github.pambrose.kvision_websockets

import com.github.pambrose.kvision_websockets.WsModel.connectToWebSocket
import com.github.pambrose.kvision_websockets.WsModel.disconnectFromWebSocket
import io.kvision.Application
import io.kvision.BootstrapCssModule
import io.kvision.BootstrapModule
import io.kvision.CoreModule
import io.kvision.FontAwesomeModule
import io.kvision.html.button
import io.kvision.html.h1
import io.kvision.module
import io.kvision.panel.VPanel
import io.kvision.panel.hPanel
import io.kvision.panel.root
import io.kvision.panel.vPanel
import io.kvision.startApplication
import io.kvision.state.ObservableValue
import io.kvision.state.bind
import io.kvision.utils.px
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher

val AppScope = CoroutineScope(window.asCoroutineDispatcher())

class App : Application() {

  private val connected = ObservableValue(false)

  override fun start(state: Map<String, Any>) {

    val msgPanel = VPanel {
      marginLeft = 10.px
    }

    root("kvapp") {

      vPanel {
        marginTop = 20.px
        marginLeft = 20.px
        spacing = 15

        h1 { +"KVision WebSocket Demo" }

        hPanel {
          spacing = 15

          button("Connect to WebSocket") {
            onClick {
              if (connected.value)
                disconnectFromWebSocket()
              else
                connectToWebSocket(msgPanel)

              connected.value = !connected.value
            }

            bind(connected) {
              text = if (it)
                "Disconnect from WebSocket"
              else
                "Connect to WebSocket"
            }
          }
        }

        add(msgPanel)
      }
    }
  }
}

fun main() {
  startApplication(
    ::App,
    module.hot,
    BootstrapModule,
    BootstrapCssModule,
    FontAwesomeModule,
    CoreModule
  )
}
