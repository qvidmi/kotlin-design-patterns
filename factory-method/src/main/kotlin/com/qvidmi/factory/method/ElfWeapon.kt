package com.qvidmi.factory.method

class ElfWeapon (val weaponType: WeaponType) : Weapon {
    override fun weaponType(): WeaponType {
        return weaponType
    }

    override fun toString(): String {
        return "Elven " + weaponType
    }
}