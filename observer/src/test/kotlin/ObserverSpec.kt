/**
 * The MIT License
 * Copyright (c) 2017 Viktar Dzmitryienka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.qvidmi.observer.*
import org.amshove.kluent.`should equal to`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

/**
 *
 * The Observer pattern is a software design pattern in which an object, called the subject,
 * maintains a list of its dependents, called observers, and notifies them automatically of any
 * state changes, usually by calling one of their methods. It is mainly used to implement
 * distributed event handling systems. The Observer pattern is also a key part in the familiar
 * model–view–controller (MVC) architectural pattern. The Observer pattern is implemented in
 * numerous programming libraries and systems, including almost all GUI toolkits.
 * <p>
 * In this example {@link Weather} has a state that can be observed. The {@link Orcs} and
 * {@link Hobbits} register as observers and receive notifications when the {@link Weather} changes.
 *
 */
class ObserverSpec : Spek({
    var logger: InMemoryAppender = InMemoryAppender()

    beforeEachTest {
        logger = InMemoryAppender()
    }
    describe("When it's raining") {
        it("makes Orcs wet") {
            verifyAction(logger, Orcs(), WeatherType.RAINY, Orcs.RAINY_ACTION)
        }

        it("makes Hobbits look for cover") {
            verifyAction(logger, Hobbits(), WeatherType.WINDY, Hobbits.WINDY_ACTION)
        }
    }
    describe("When it's sunny") {
        it("makes Orcs' eyes hurt") {
            verifyAction(logger, Orcs(), WeatherType.SUNNY, Orcs.SUNNY_ACTION)
        }

        it("makes Hobbits happy") {
            verifyAction(logger, Hobbits(), WeatherType.WINDY, Hobbits.WINDY_ACTION)
        }
    }
    describe("When it's cold") {
        it("makes Orcs freeze") {
            verifyAction(logger, Orcs(), WeatherType.COLD, Orcs.COLD_ACTION)
        }

        it("makes Hobbits shiver") {
            verifyAction(logger, Hobbits(), WeatherType.WINDY, Hobbits.WINDY_ACTION)
        }
    }
    describe("When it's windy") {
        it("makes Orcs' smell almost vanish") {
            verifyAction(logger, Orcs(), WeatherType.WINDY, Orcs.WINDY_ACTION)
        }

        it("makes Hobbits hold their hats tightly") {
            verifyAction(logger, Hobbits(), WeatherType.WINDY, Hobbits.WINDY_ACTION)
        }
    }

    describe("The Observer (Weather)") {
        it("makes Observable (Orcs and Hobbits) react on changes when they do care") {
            val orcs: Orcs = mock()
            val hobbits: Hobbits = mock()

            val weather = Weather(WeatherType.SUNNY)
            weather.addObserver(orcs)
            weather.addObserver(hobbits)
            verifyZeroInteractions(orcs)
            verifyZeroInteractions(hobbits)

            weather.changes(WeatherType.RAINY)
            verify(orcs).update(weather, WeatherType.RAINY)
            verify(hobbits).update(weather, WeatherType.RAINY)

            logger.logSize `should equal to` 1
        }

        it("makes Observable (Orcs and Hobbits) ignore changes when they don't care") {
            val orcs: Orcs = mock()
            val hobbits: Hobbits = mock()

            val weather = Weather(WeatherType.SUNNY)
            weather.addObserver(orcs)
            weather.addObserver(hobbits)

            verifyZeroInteractions(orcs)
            verifyZeroInteractions(hobbits)

            weather.removeObserver(orcs)
            weather.removeObserver(hobbits)

            weather.timePasses()
            weather.timePasses()
            weather.timePasses()

            verifyZeroInteractions(orcs)
            verifyZeroInteractions(hobbits)

            logger.logSize `should equal to` 3
        }
    }

    afterEachTest {
        logger.stop()
    }
})

private fun verifyAction(logger: InMemoryAppender, race: Race, weatherChange: WeatherType, expectedAction: String) {
    val weather = Weather(WeatherType.SUNNY)
    weather.addObserver(race)
    weather.changes(weatherChange)
    logger.lastMessage `should equal to` expectedAction
}