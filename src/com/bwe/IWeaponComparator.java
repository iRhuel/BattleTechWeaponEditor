package com.bwe;

import com.bwe.enums.Type;
import com.bwe.enums.WeaponSubType;
import com.bwe.pojo.weapon.Weapon;

import java.util.Comparator;

public class IWeaponComparator implements Comparator<Weapon> {

    @Override
    public int compare(Weapon wpn1, Weapon wpn2) {
        if (wpn1.getType().equals(wpn2.getType())) {
            if (wpn1.getWeaponSubType().equals(wpn2.getWeaponSubType())) {
                return wpn1.getDescription().getName().compareTo(wpn2.getDescription().getName());
            } else {
                return WeaponSubType.valueOf(wpn1.getWeaponSubType()).compareTo(WeaponSubType.valueOf(wpn2.getWeaponSubType()));
            }
        } else {
            return Type.valueOf(wpn1.getType()).compareTo(Type.valueOf(wpn2.getType()));
        }
    }
}
