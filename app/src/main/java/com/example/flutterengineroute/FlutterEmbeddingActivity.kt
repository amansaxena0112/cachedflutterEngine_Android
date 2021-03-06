package com.example.flutterengineroute

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.view.FlutterMain
import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.plugin.common.EventChannel

class FlutterEmbeddingActivity : FlutterActivity(){

    // You need to define an IntentBuilder subclass so that the
    // IntentBuilder uses FlutterEmbeddingActivity instead of a regular FlutterActivity.
    private class IntentBuilder// Override the constructor to specify your class.
    internal constructor() : FlutterActivity.NewEngineIntentBuilder(FlutterEmbeddingActivity::class.java)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
          init(this)
          val intentExtras = intent.extras?.keySet()?.associateBy({it}, { intent.extras!!.get(it)})
           eventChannelSink?.success(intentExtras)
        }

    // This is the method where you provide your existing FlutterEngine instance.
    override fun getFlutterEngine(): FlutterEngine? {
        return cachedflutterEngine
    }

    companion object {
                private const val EVENT_CHANNEL_NAME = "com.example.flutterengineroute/event"
                private var eventChannelSink: EventChannel.EventSink? = null
        private lateinit var cachedflutterEngine: FlutterEngine

        fun init(context: Context) {
            if (::cachedflutterEngine.isInitialized) {
                return
            }
            // Flutter must be initialized before FlutterEngines can be created.
            FlutterMain.startInitialization(context)
            FlutterMain.ensureInitializationComplete(context, arrayOf())
            // Instantiate a FlutterEngine.
            cachedflutterEngine = FlutterEngine(context)

                        val eventChannel = EventChannel(cachedflutterEngine.dartExecutor, EVENT_CHANNEL_NAME)
                        eventChannel.setStreamHandler(object : EventChannel.StreamHandler {
                                override fun onListen(o: Any?, eventSink: EventChannel.EventSink) {
                                       eventChannelSink = eventSink
                                    }
                               override fun onCancel(o: Any?) {
                                    }
                            })
//                        if (context !is FlutterEmbeddingActivity) {
//                                val flutterEmbeddingActivityIntent = FlutterEmbeddingActivity.createBuilder()
//                                    .initialRoute("init")
//                                    .build(context)
//                                ContextCompat.startActivity(context, flutterEmbeddingActivityIntent, null)
//                            }

        }

        // This is the method that others will use to create
        // an Intent that launches MyFlutterActivity.
        fun createBuilder(): FlutterActivity.NewEngineIntentBuilder {
            return IntentBuilder()
        }
    }

}