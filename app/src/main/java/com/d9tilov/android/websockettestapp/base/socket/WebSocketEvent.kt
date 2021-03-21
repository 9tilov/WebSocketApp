package com.d9tilov.android.websockettestapp.base.socket

import okhttp3.Response
import okio.ByteString

sealed class WebSocketEvent {

    class OnConnectionOpened(val response: Response) : WebSocketEvent()
    class OnMessageReceived(val message: String) : WebSocketEvent()
    class OnMessageReceivedBytes(val byteString: ByteString) : WebSocketEvent()
    class OnConnectionClosing(val code: Int, val reason: String) : WebSocketEvent()
    class OnConnectionClosed(val code: Int, val reason: String) : WebSocketEvent()
    class OnConnectionFailure(val t: Throwable, val response: Response?) : WebSocketEvent()
}

