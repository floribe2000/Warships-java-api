package players

import de.floribe2000.warships_java.direct.account.PlayerStatisticsByDateRequest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import utilities.ITestClass
import java.time.ZonedDateTime

class PlayerStatisticsByDateTest : ITestClass {
    override val instanceName: String = "TEST"

    @Test
    fun testDateCollectionSetting() {
        setupApi()

        val dates = listOf("20201231", "20210112")
        assertDoesNotThrow {
            PlayerStatisticsByDateRequest().dates(dates)
        }
    }

    @Test
    fun testDateCollectionSetting_throwException() {
        setupApi()

        val dates = listOf("20201231", "2021-01-12")
        assertThrows<IllegalArgumentException> {
            PlayerStatisticsByDateRequest().dates(dates)
        }
    }

    @Test
    fun testAddDate() {
        setupApi()

        val date = ZonedDateTime.now()
        assertDoesNotThrow {
            PlayerStatisticsByDateRequest().addDate(date)
        }
    }

    @Test
    fun testAddDate_throwException() {
        setupApi()

        val date = ZonedDateTime.now()
        val request = PlayerStatisticsByDateRequest()
        repeat(10) {
            request.addDate(date)
        }

        assertThrows<IllegalStateException> {
            request.addDate(date)
        }
    }
}