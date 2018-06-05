---------------
ABOUT

author: iRhuel
version: 0.2.2

---------------
MENU

- "File->Open" to change the working directory. Navigate to "[yourgamefolder]\BattleTech_Data\StreamingAssets\data\weapon" (or, if you're being extra careful, whichever directory it is that contains the weapon .jsons that you wish to edit). The table should then autopopulate with the data from your jsons. The app will remember this directory for next time.

- "Edit->Save Changes" to commit any changes made in the editor to your .json files. Closing the app without doing this will discard any unsaved changes.

- "Edit->Create Backup" to generate a single .json backup of your weapon jsons in their current state. It's recommended to place this outside of your "/weapon" folder. The app will also remember your last backup save location.

- "Edit->Restore Backup" to restore your weapon .jsons from a previously generated backup .json.

- "View->Columns" to select which columns are displayed. The program will remember these choices.

- "Help->About" I mean... you're reading this.

---------------
EDITOR

- To repopulate the table, choose a filter category and select a subfilter below. If you wish to only display stock items, check the "Stock Only" box.

- To edit parameters, double-click the particular parameter for the item you wish to edit. To batch edit, check the "Batch Edit" button before editing. All values other than second-order calculated values (dmg/ton, dmg/heat, stb/ton, stb/heat) are editable.

- Weapons are selected for batch editing by 'Name' field with all " +" substrings removed; all weapons sharing the base weapon's name will have their base parameter set to your desired value to maintain continuity, then + items will have their bonus modifiers applied automatically.

- Changes to either stat bonus will autocorrect the appropriate parameters on assignment. Currently, the editor supports bonuses for: " Dmg.", " Acc.", " Crit.", " Stb.Dmg.", " Heat", and "Dmg. (H)". (I have not actually tested Heat as it doesn't exist as a vanilla bonus, but it should work exactly the same as the others).

- For vanilla items with missing values (e.g melee weapons), do not try to assign values to fields that did not already have a pre-existing value. The game may freak out...

- when reassigning stat bonuses, stick to the vanilla game's syntax; that is, '+/-', then space, then the integer value of the bonus, then space, then the parameter abbreviation (with period). Failing to do so may result in the editor failing to recognize the appropriate parameter and not updating on batch fixes.

---------------
NOTES

- as this is a beta release, it's encouraged to create your own manual backup of "/weapon" until you feel confident in the backup and restore functionality. Just in case.

- works on mod weapons, so long as they follow the same json structure as the vanilla weapons. Just point the app to the folder containing the mod weapons.

- this app hasn't been tested on any platform but Windows.

---------------
KNOWN ISSUES

---------------
LICENSE

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program. If not, see <https://www.gnu.org/licenses/>.