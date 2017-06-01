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
import com.qvidmi.adapter.BattleFishingBoat
import com.qvidmi.adapter.BattleShip
import com.qvidmi.adapter.Captain
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

/**
 * An adapter helps two incompatible interfaces to work together. This is the real world definition
 * for an adapter. Interfaces may be incompatible but the inner functionality should suit the need.
 * The Adapter design pattern allows otherwise incompatible classes to work together by converting
 * the interface of one class into an interface expected by the clients.
 *
 * <p>
 * There are two variations of the Adapter pattern: The class adapter implements the adaptee's
 * interface whereas the object adapter uses composition to contain the adaptee in the adapter
 * object. This example uses the object adapter approach.
 *
 * <p>
 * The Adapter ({@link BattleFishingBoat}) converts the interface of the adaptee class (
 * {@link FishingBoat}) into a suitable one expected by the client ( {@link BattleShip} ).
 *
 * <p>
 * The story of this implementation is this. <br>
 * Pirates are coming! we need a {@link BattleShip} to fight! We have a {@link FishingBoat} and our
 * captain. We have no time to make up a new ship! we need to reuse this {@link FishingBoat}. The
 * captain needs a battleship which can fire and move. The spec is in {@link BattleShip}. We will
 * use the Adapter pattern to reuse {@link FishingBoat}.
 *
 */
class AdapterSpec : Spek({
    describe("The Adapter") {
        var map: Map<String, BattleShip> = mapOf()
        val BATTLESHIP_BEAN = "engineer"
        val CAPTAIN_BEAN = "captain"

        beforeEachTest {
            val battleShip: BattleFishingBoat = mock()
            map = mapOf(BATTLESHIP_BEAN to battleShip, CAPTAIN_BEAN to Captain(battleShip))
        }

        it("should move BattleShip when Captain moves") {
            map[CAPTAIN_BEAN]?.move()

            val battleShip: BattleShip? = map[BATTLESHIP_BEAN]
            verify(battleShip)?.move()
        }

        it("should make a fire from a BattleShip when Captain fires") {
            map[CAPTAIN_BEAN]?.fire()

            val battleShip: BattleShip? = map[BATTLESHIP_BEAN]
            verify(battleShip)?.fire()
        }
    }
})