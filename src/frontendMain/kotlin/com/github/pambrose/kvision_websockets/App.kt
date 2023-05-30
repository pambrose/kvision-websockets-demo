package com.github.pambrose.kvision_websockets

import com.github.pambrose.kvision_websockets.WebSocketModel.connectToWebSocket
import com.github.pambrose.kvision_websockets.WebSocketModel.disconnectFromWebSocket
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
import io.kvision.startApplication
import io.kvision.utils.px
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher

val AppScope = CoroutineScope(window.asCoroutineDispatcher())

class App : Application() {

  override fun start(state: Map<String, Any>) {

    val msgPanel = VPanel {
      marginLeft = 5.px
    }

    root("kvapp") {

      h1 { +"WebSocket Demo" }

      hPanel {
        spacing = 15

        button("Connect to WebSocket") {
          onClick {
            connectToWebSocket(msgPanel)
          }
        }

        button("Disconnect from WebSocket") {
          onClick {
            disconnectFromWebSocket()
          }
        }
      }

      add(msgPanel)
    }

//    AppScope.launch {
//      val pingResult = PingModel.ping("Hello world from client!")
//      root.add(Div(pingResult))
//    }
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
