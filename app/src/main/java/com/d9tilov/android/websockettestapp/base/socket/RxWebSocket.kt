package com.d9tilov.android.websockettestapp.base.socket

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import timber.log.Timber

class RxWebSocket(
    private val client: OkHttpClient,
    private val request: Request
) {

    private lateinit var webSocket: WebSocket
    private val subject = PublishSubject.create<WebSocketEvent>()
    private var isSocketConnected = false
    private val webSocketListener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            Timber.d("WebSocket: onOpen: ${response.message}")
            subject.onNext(WebSocketEvent.OnConnectionOpened(response))
            isSocketConnected = true
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            Timber.d("WebSocket: onMessage: $text")
            subject.onNext(WebSocketEvent.OnMessageReceived(text))
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            super.onMessage(webSocket, bytes)
            Timber.d("WebSocket: onMessage: $bytes")
            subject.onNext(WebSocketEvent.OnMessageReceivedBytes(bytes))
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
            Timber.d("WebSocket: onClosing with code $code and reason $reason")
            subject.onNext(WebSocketEvent.OnConnectionClosing(code, reason))
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            Timber.d("WebSocket: onClosed with code $code and reason $reason")
            subject.onNext(WebSocketEvent.OnConnectionClosed(code, reason))
            isSocketConnected = false
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            Timber.d("WebSocket: onFailure with error: ${t.message} and response ${response?.message}")
            subject.onNext(WebSocketEvent.OnConnectionFailure(t, response))
            isSocketConnected = false
        }
    }

    fun openConnection() {
        if (!isSocketConnected) {
            webSocket = client.newWebSocket(request, webSocketListener)
        }
    }

    fun closeConnection(): Boolean {
        if (isSocketConnected) {
            return webSocket.close(WEB_SOCKET_CLOSE_REASON, "Normal shutdown")
        }
        return false
    }

    fun subscribe(exchangePair: String): Boolean = webSocket.send("SUBSCRIBE: $exchangePair")
    fun unsubscribe(exchangePair: String): Boolean = webSocket.send("UNSUBSCRIBE: $exchangePair")

    fun publishSubjectStream(): Observable<WebSocketEvent> {
        return subject
    }

    companion object {
        private const val WEB_SOCKET_CLOSE_REASON = 1000
    }
}
