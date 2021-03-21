package com.d9tilov.android.websockettestapp.main.data

import com.d9tilov.android.websockettestapp.base.exception.RxWebSocketException
import com.d9tilov.android.websockettestapp.base.socket.RxWebSocket
import com.d9tilov.android.websockettestapp.base.socket.WebSocketEvent
import com.d9tilov.android.websockettestapp.main.data.remote.parser.WebSocketResponseParser
import com.d9tilov.android.websockettestapp.main.domain.ExchangeRepo
import com.d9tilov.android.websockettestapp.main.domain.entity.ExchangeRateItem
import com.d9tilov.android.websockettestapp.settings.domain.entity.ExchangePairDataModel
import io.reactivex.Completable
import io.reactivex.Observable

class ExchangeDataRepo(
    private val rxWebSocket: RxWebSocket,
    private val parser: WebSocketResponseParser
) : ExchangeRepo {

    private val ratesMap = mutableMapOf<String, ExchangeRateItem>()

    override fun startFetchData(pairs: List<ExchangePairDataModel>): Completable {
        var success: Boolean
        rxWebSocket.openConnection()
        for (pair in pairs) {
            success = rxWebSocket.subscribe("${pair.from.name}${pair.to.name}")
            if (!success) {
                return Completable.error(RxWebSocketException("Unable to make call with ${pair.from.name}${pair.to.name}"))
            }
        }
        return Completable.complete()
    }

    override fun stopFetchData(): Completable {
        if (rxWebSocket.closeConnection()) {
            return Completable.complete()
        }
        return Completable.error(RxWebSocketException("WebSocket hasn't been running yet"))
    }

    override fun getExchangeRate(): Observable<List<ExchangeRateItem>> {
        return rxWebSocket.publishSubjectStream().flatMap {
            when (it) {
                is WebSocketEvent.OnConnectionOpened,
                is WebSocketEvent.OnMessageReceivedBytes,
                is WebSocketEvent.OnConnectionClosing,
                is WebSocketEvent.OnConnectionClosed -> {
                    Observable.fromCallable { emptyList() }
                }
                is WebSocketEvent.OnMessageReceived -> {
                    if (parser.getSubscribedElementsCount(it.message) == 0) {
                        ratesMap.clear()
                    } else {
                        val subscribedElements: List<ExchangeRateItem>
                        if (parser.getSubscribedElements(it.message).isNotEmpty()) {
                            subscribedElements = parser.getSubscribedElements(it.message)
                            ratesMap.clear()
                        } else {
                            subscribedElements = parser.getTicks(it.message)
                        }
                        fillMap(subscribedElements)
                    }
                    Observable.fromCallable { mapToList() }
                }
                is WebSocketEvent.OnConnectionFailure -> {
                    Observable.error(RxWebSocketException("WebSocket was closed"))
                }
            }
        }
    }

    private fun fillMap(rates: List<ExchangeRateItem>) {
        for (rate in rates) {
            ratesMap[rate.from.name + rate.to.name] = rate
        }
    }

    private fun mapToList(): List<ExchangeRateItem> {
        return ratesMap.toList().map { it.second }.sortedWith(
            compareBy(
                { it.from.name },
                { it.to.name })
        )
    }
}
