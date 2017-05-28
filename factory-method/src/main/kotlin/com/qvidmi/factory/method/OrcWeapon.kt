package com.qvidmi.factory.method

class OrcWeapon(val weaponType: WeaponType) : Weapon {
    override fun weaponType(): WeaponType {
        return weaponType
    }

    override fun toString(): String {
        return "Orcish " + weaponType
    }
}