package de.floribe2000.warships_java.utilities

import de.floribe2000.warships_java.direct.api.ApiBuilder.Companion.createInstance
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Closeable
import java.util.concurrent.atomic.AtomicBoolean

abstract class AbstractRequestService {

    abstract fun initialize(apiKey: String)

    companion object {

        protected val initialized = AtomicBoolean(false)

        protected const val INSTANCE = "RequestService"

        protected val log: Logger = LoggerFactory.getLogger("RequestService")

        fun initialize(apiKey: String) {
            if (initialized.getAndSet(true)) {
                return
            }
            createInstance(apiKey, instanceName = INSTANCE)
        }

        fun reset() {
            initialized.set(false)
        }
    }
}