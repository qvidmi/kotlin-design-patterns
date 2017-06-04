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
import com.qvidmi.state.Mammoth
import org.amshove.kluent.`should equal to`
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

/**
 *
 * In State pattern the container object has an internal state object that defines the current
 * behavior. The state object can be changed to alter the behavior.
 * <p>
 * This can be a cleaner way for an object to change its behavior at runtime without resorting to
 * large monolithic conditional statements and thus improves maintainability.
 * <p>
 * In this example the {@link Mammoth} changes its behavior as time passes by.
 *
 */
class StateSpec : Spek({
    var logger: InMemoryAppender = InMemoryAppender()
    var mammoth = Mammoth()
    beforeGroup {
        logger = InMemoryAppender()
        mammoth = Mammoth()
    }
    describe("the sunrise") {
        it("makes mammoth be calm and peaceful.") {
            mammoth.observe()
            verifyState(logger, "The mammoth is calm and peaceful.")
            logger.logSize `should equal to` 1
        }
    }
    describe("when time passes") {
        it("makes mammoth be angry!") {
            mammoth.timePasses()
            verifyState(logger, "The mammoth gets angry!")
            logger.logSize `should equal to` 2
        }
    }
    describe("when somebody sees the mammoth") {
        it("makes mammoth be furious!") {
            mammoth.observe()
            verifyState(logger, "The mammoth is furious!")
            logger.logSize `should equal to` 3
        }
    }
    describe("the sunset") {
        it("makes mammoth calm down.") {
            mammoth.timePasses()
            verifyState(logger, "The mammoth calms down.")
            logger.logSize `should equal to` 4
        }
    }

    describe("a good sleep") {
        it("makes mammoth be calm and peaceful.") {
            mammoth.observe()
            verifyState(logger, "The mammoth is calm and peaceful.")
            logger.logSize `should equal to` 5
        }
    }

    afterGroup {
        logger.stop()
    }
})

private fun verifyState(logger: InMemoryAppender, expectedAction: String) {
    logger.lastMessage `should equal to` expectedAction
}