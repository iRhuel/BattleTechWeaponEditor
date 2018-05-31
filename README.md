# BattleTech Weapon Editor

BWE is a tool to view and edit various values stored in json files meant to define weapon parameters used by BattleTech (2018). Common values shared between weapon types can be batch edited to maintain continuity within weapon groups, with bonus attributes from upgraded weapons automatically applied to save time and headache. 

It also includes functionality to manually generate a backup file of the jsons as they currently exist, as well as restoring jsons from a chosen backup file.

Lastly, it displays and updates second-order statistics (like damage/ton ratios) to help users make more informed corrections.  

Before usage, please read the Usage and Notes section here, the [readme.txt](https://github.com/iRhuel/BattleTechWeaponEditor/blob/master/src/readme.txt) contained in the package, or click "Help->About" in the app menu bar for more specific usage notes. 

## Download

[Latest release](https://github.com/iRhuel/BattleTechWeaponEditor/releases)

Unzip to a location of your choice, and run the .jar file with JRE (must have JRE installed). If it doesn't automatically do so, right click on .jar, click 'open with...', and select 'Java(TM) Platform SE Binary'.

### Prerequisites

You will need at least [Java 10 JRE](http://www.oracle.com/technetwork/java/javase/downloads/jre10-downloads-4417026.html) installed (support for Java 9, which was used to develop this app, has ended). A self-contained application package is in the works.

## Usage

### Menu

- "File->Open" to change the working directory. Navigate to "[yourgamefolder]\BattleTech_Data\StreamingAssets\data\weapon" (or, if you're being extra careful, whichever directory it is that contains the weapon .jsons that you wish to edit). The table should then autopopulate with the data from your jsons. The app will remember this directory for next time.

- "Edit->Save Changes" to commit any changes made in the editor to the .json files in the current working directory. Closing the app or changing working directory without doing this will discard any unsaved changes.

- "Edit->Create Backup" to generate a single .json backup of your weapon jsons in their current state. It's recommended to place this outside of your "/weapon" folder. The app will also remember your last backup save location.

- "Edit->Restore Backup" to restore all files saved from a previously generated backup .json, to the current working directory.

- "View->Columns" to select which columns are displayed. The program will remember these choices.

- "Help->About" to view the readme file with these instructions.

### Editor

- To repopulate the table, choose a filter category and select a subfilter below. If you wish to only display stock items, check the "Stock Only" box.

- To edit parameters, double-click the particular parameter for the item you wish to edit. To batch edit, check the "Batch Edit" button before editing. All values other than second-order calculated values (dmg/ton, dmg/heat, stb/ton, stb/heat) are editable.

- Weapons are selected for batch editing by 'Name' field with all " +" substrings removed; all weapons sharing the base weapon's name will have their base parameter set to your desired value to maintain continuity, then + items will have their bonus modifiers applied automatically.

- Changes to either stat bonus will autocorrect the appropriate parameters on assignment. Currently, the editor supports bonuses for: " Dmg.", " Acc.", " Crit.", " Stb.Dmg.", " Heat", and "Dmg. (H)". (I have not actually tested Heat as it doesn't exist as a vanilla bonus, but it should work exactly the same as the others).

- When including multiple bonus tags in a single field, please separate them with a '|' (without the quotes).

- For vanilla items with missing values (e.g melee weapons), do not try to assign values to fields that did not already have a pre-existing value. The game may freak out...

- when reassigning stat bonuses, stick to the vanilla game's syntax; that is, '+/-', then space, then the integer value of the bonus, then space, then the parameter abbreviation (with period). Failing to do so may result in the editor failing to recognize the appropriate parameter and not updating on batch fixes.

## Notes

- MOD AUTHORS: BWE selects items for batch editing based on the Name field value, with all " +" suffixes removed. Therefore, all items sharing a base name with the edited item will be batch fixed

- as this is a beta release, it's encouraged to create your own manual backup of "/weapon" until you feel confident in the backup and restore functionality. Just in case.

- works on mod weapons, so long as they follow the same json structure as the vanilla weapons. Just point the app to the folder containing the mod weapons.

- this app hasn't been tested on any platform but Windows. 

## TODO

Suggestions, critiques, feature requests are more than welcome.

- view and edit more values
- more robust table column display button
- natively packaged version independent of JRE on local machine
- graphing functionality
- generify json parsing in preparation for...
    - support for more json types like pilots, mechs, chassis, etc. (currently only supports weapon jsons like those found in ..\StreamingAssets\data\weapon)
- de-uglification

## Author(s)

* **Phil Truong** aka **iRhuel**
    * Discord: iRhuel#5923
    * Reddit: reddit.com/u/iRhuel
    
## Thanks


FasterXML, for the [Jackson Json Library](https://github.com/FasterXML/jackson)

Dan Newton, for his ingenious [TableViewContextMenu](https://dzone.com/articles/stopping-javafx-context-menus-from-auto-hiding) solution

## License

This project is licensed under the [GNU General Public License](https://www.gnu.org/licenses/gpl-3.0.en.html).