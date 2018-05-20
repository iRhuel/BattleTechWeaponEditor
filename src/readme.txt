---------------
ABOUT

author: iRhuel
version: 0.1.0

---------------
MENU

- "File->Open" to change the working directory. Navigate to "[yourgamefolder]\BattleTech_Data\StreamingAssets\data\weapon" (or, if you're being extra careful, whichever directory it is that contains the weapon .jsons that you wish to edit). The table should then autopopulate with the data from your jsons. The app will remember this directory for next time.

- "Edit->Save Changes" to commit any changes made in the editor to your .json files. Closing the app without doing this will discard any unsaved changes.

- "Edit->Create Backup" to generate a single .json backup of your weapon jsons in their current state. It's recommended to place this outside of your "/weapon" folder. The app will also remember your last backup save location.

- "Edit->Restore Backup" to restore your weapon .jsons from a previously generated backup .json.

- "Help->About" I mean... you're reading this.

---------------
EDITOR

- To repopulate the table, choose a filter category, select a subfilter, then hit the "Filter" button. If you wish to only display stock items, check the "Stock Only" box before filtering.

- To edit parameters, double-click the particular parameter for the item you wish to edit. Editable parameters are: damage*, instability*, accuracy modifier, crit chance, heat generated, tonnage, stat bonus A**, and stat bonus B**.

- All parameters but the stat bonuses are batch-edited by SubWeaponType; all weapons sharing the SubWeaponType will have their pase parameter set to your desired value to maintain continuity, then + items will have their bonus modifiers applied automatically.

- The stat bonus parameters are updated individually, and will autocorrect the appropriate parameters on assignment. Currently, the editor supports bonuses for: Dmg., Acc., Crit., Stb.Dmg., and Heat (I have not actually tested Heat as it doesn't exist as a vanilla bonus, but it should work exactly the same as the others).

*for weapons that fire multiple projectiles in a single attack (LRMs, SRMs, MachineGun), the value displayed is per volley. HOWEVER, when editing the damage and instability parameters for these items, make sure to enter the desired value PER PROJECTILE, NOT PER VOLLEY.

**when reassigning stat bonuses, stick to the vanilla game's syntax; that is, '+/-', then space, then the integer value of the bonus, then space, then the parameter abbreviation (with period). Failing to do so may result in the editor failing to recognize the appropriate parameter and not updating on batch fixes.

---------------
NOTES

- MOD AUTHORS: in order to edit batched values, your files MUST at least contain the 'WeaponSubType' key:value pair with a non-null value. This is how BWE selects what to batch edit. This goes for all batched values (not Bonus Strings).

- as this is a beta release, it's encouraged to create your own manual backup of "/weapon" until you feel confident in the backup and restore functionality. Just in case.

- works on mod weapons, so long as they follow the same json structure as the vanilla weapons. Just point the app to the folder containing the mod weapons.

- currently does not handle parameters for melee attacks.

- this app hasn't been tested on any platform but Windows.

---------------
KNOWN ISSUES

---------------
LICENSE

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.