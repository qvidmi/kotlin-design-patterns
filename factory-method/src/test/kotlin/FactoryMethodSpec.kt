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
import com.qvidmi.factory.method.*
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldNotBeInstanceOf
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

/**
 *
 * The Factory Method is a creational design pattern which uses factory methods to deal with the
 * problem of creating objects without specifying the exact class of object that will be created.
 * This is done by creating objects via calling a factory method either specified in an interface
 * and implemented by child classes, or implemented in a base class and optionally overridden by
 * derived classes—rather than by calling a constructor.
 *
 * Factory produces the object of its liking.
 * The weapon {@link Weapon} manufactured by the
 * blacksmith depends on the kind of factory implementation it is referring to.
 */
class FactoryMethodSpec: Spek({
    describe("The Factory Method") {

        it("should create Orcish Weapon when manufactured by Orc Blacksmith") {
            val blacksmith = OrcBlacksmith()
            val weapon = blacksmith.manufactureWeapon(WeaponType.SPEAR)
            weapon shouldBeInstanceOf OrcWeapon::class
            weapon shouldNotBeInstanceOf ElfWeapon::class
        }

        it("should create Elven Weapon when manufactured by Elf Blacksmith") {
            val blacksmith = ElfBlacksmith()
            val weapon = blacksmith.manufactureWeapon(WeaponType.SPEAR)
            weapon shouldBeInstanceOf ElfWeapon::class
            weapon shouldNotBeInstanceOf OrcWeapon::class
        }
    }
})