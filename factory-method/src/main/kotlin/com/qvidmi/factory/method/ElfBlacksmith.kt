package com.qvidmi.factory.method

class ElfBlacksmith : Blacksmith {
    override fun manufactureWeapon(weaponType: WeaponType): Weapon {
        val elfWeapon = ElfWeapon(weaponType)
        return elfWeapon
    }

}