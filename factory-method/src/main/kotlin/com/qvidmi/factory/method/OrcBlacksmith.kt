package com.qvidmi.factory.method

class OrcBlacksmith : Blacksmith {
    override fun manufactureWeapon(weaponType: WeaponType): Weapon {
        return OrcWeapon(weaponType)
    }
}