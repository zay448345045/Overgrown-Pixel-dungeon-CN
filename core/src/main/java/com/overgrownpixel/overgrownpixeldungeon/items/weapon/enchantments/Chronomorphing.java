/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2019 Evan Debenham
 *
 * Overgrown Pixel Dungeon
 * Copyright (C) 2016-2019 Anon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This Program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without eben the implied warranty of
 * GNU General Public License for more details.
 *
 * You should have have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses>
 */

package com.overgrownpixel.overgrownpixeldungeon.items.weapon.enchantments;

import com.overgrownpixel.overgrownpixeldungeon.R;
import com.overgrownpixel.overgrownpixeldungeon.actors.Char;
import com.overgrownpixel.overgrownpixeldungeon.items.artifacts.TimekeepersHourglass;
import com.overgrownpixel.overgrownpixeldungeon.items.weapon.Weapon;
import com.overgrownpixel.overgrownpixeldungeon.sprites.items.ItemSprite;
import com.watabou.noosa.Game;
import com.watabou.utils.Random;

public class Chronomorphing extends Weapon.Enchantment {

    private static ItemSprite.Glowing COLOR = new ItemSprite.Glowing(Game.instance.getResources().getInteger(R.integer.chronomorphing));

    @Override
    public int proc(Weapon weapon, Char attacker, Char defender, int damage) {
        // lvl 0 - 33%
        // lvl 1 - 50%
        // lvl 2 - 60%
        int level = Math.max( 0, weapon.level() );

        if (Random.Int( level + 3 ) >= 2) {
            TimekeepersHourglass timekeepersHourglass = new TimekeepersHourglass();
            if(Random.Boolean()){
                timekeepersHourglass.getTimeStopEffectFreeze((weapon.level()+1)*2);
            } else {
                timekeepersHourglass.getTimeStopEffectStasis((weapon.level()+1)*2);
            }
        }

        return damage;
    }
	
	@Override
	public ItemSprite.Glowing glowing() {
		return COLOR;
	}
}