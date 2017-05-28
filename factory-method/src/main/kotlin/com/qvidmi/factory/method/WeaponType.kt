package com.qvidmi.factory.method

enum class WeaponType constructor(private val title: String) {

    SHORT_SWORD("short sword"),
    SPEAR("spear"),
    AXE("axe"),
    UNDEFINED("");

    override fun toString(): String {
        return title
    }
}
