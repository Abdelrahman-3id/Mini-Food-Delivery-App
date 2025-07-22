//package com.example.minifooddeliveryapp.data.websocket
//
//import com.google.gson.Gson
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.flow.MutableSharedFlow
//import kotlinx.coroutines.flow.SharedFlow
//import kotlinx.coroutines.launch
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import okhttp3.Response
//import okhttp3.WebSocket
//import okhttp3.WebSocketListener
//import javax.inject.Inject
//
//class WebSocketService @Inject constructor(
//    private val okHttpClient: OkHttpClient
//) {
//    private var webSocket: WebSocket? = null
//    private val _updates = MutableSharedFlow<OrderStatusUpdateDto>()
//    val updates: SharedFlow<OrderStatusUpdateDto> = _updates
//
//    fun connect() {
//        val request = Request.Builder()
//            .url("ws://10.0.2.2:8080/orders/updates")
//            .build()
//
//        webSocket = okHttpClient.newWebSocket(request, object : WebSocketListener() {
//            override fun onMessage(webSocket: WebSocket, text: String) {
//                try {
//                    val update = Gson().fromJson(text, OrderStatusUpdateDto::class.java)
//                    CoroutineScope(Dispatchers.IO).launch {
//                        _updates.emit(update)
//                    }
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//
//            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
//                // Handle failure and reconnect
//            }
//        })
//    }
//
//    fun disconnect() {
//        webSocket?.close(1000, "Closing connection")
//        webSocket = null
//    }
//
//    data class OrderStatusUpdateDto(
//        val orderId: String,
//        val newStatus: String
//    )
//}