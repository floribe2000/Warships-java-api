package de.floribe2000.warships_java.utilities

import de.floribe2000.warships_java.direct.api.ApiBuilder
import de.floribe2000.warships_java.direct.api.ApiBuilder.Companion.createInstance
import org.slf4j.LoggerFactory
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.concurrent.thread

abstract class AbstractRequestService {

    abstract fun initialize(apiKey: String)

    companion object {

        protected val initialized = AtomicBoolean(false)

        protected const val INSTANCE = "RequestService"

        protected val LOG = LoggerFactory.getLogger("RequestService")

        fun initialize(apiKey: String) {
            if (initialized.getAndSet(true)) {
                return
            }
            createInstance(apiKey, INSTANCE)
//            try {
//                Runtime.getRuntime().addShutdownHook(thread {
//                    ApiBuilder.shutdown()
//                })
//            } catch (ie: IllegalArgumentException) {
//                LOG.error("Encountered an exception while adding a shutdown hook.")
//            }
        }

        fun reset() {
            initialized.set(false)
        }
    }
}