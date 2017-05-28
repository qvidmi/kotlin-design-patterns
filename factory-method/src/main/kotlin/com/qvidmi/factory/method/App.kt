package com.qvidmi.factory.method

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    manufactureWeapons(OrcBlacksmith())
    manufactureWeapons(ElfBlacksmith())
}

fun manufactureWeapons(blacksmith: Blacksmith) {
    val spear = blacksmith.manufactureWeapon(WeaponType.SPEAR)
    logger.info("$spear")

    val axe = blacksmith.manufactureWeapon(WeaponType.AXE)
    logger.info("$axe")

}
