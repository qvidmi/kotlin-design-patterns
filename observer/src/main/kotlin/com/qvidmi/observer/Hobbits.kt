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
package com.qvidmi.observer

import mu.KotlinLogging

open class Hobbits : Race {
    private val logger = KotlinLogging.logger {}

    companion object {
        val COLD_ACTION: String = "The hobbits are shivering in the cold weather."
        val RAINY_ACTION: String = "The hobbits look for cover from the rain."
        val SUNNY_ACTION: String = "The happy hobbits bade in the warm sun."
        val WINDY_ACTION: String = "The hobbits hold their hats tightly in the windy weather."
    }

    override fun update(subject: Weather, argument: WeatherType) {
        when (argument) {
            WeatherType.COLD -> logger.info(COLD_ACTION)
            WeatherType.RAINY -> logger.info(RAINY_ACTION)
            WeatherType.SUNNY -> logger.info(SUNNY_ACTION)
            WeatherType.WINDY -> logger.info(WINDY_ACTION)
            else -> {
            }
        }
    }

}