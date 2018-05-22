# BattleTech Weapon Editor

BWE is a tool to view and edit various values stored in json files meant to define weapon parameters used by BattleTech (2018). Common values shared between weapon types will be batch edited to maintain continuity within weapon groups, with bonus attributes from upgraded weapons automatically applied to save time and headache. Bonus values themselves are individually set for each weapon, and automatically apply to and correct for appropriate attributes on assignment.

It also includes functionality to manually generate a backup  file of the jsons as they currently exist, as well as restoring jsons from a chosen backup file.

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

- "Edit->Save Changes" to commit any changes made in the editor to your .json files. Closing the app without doing this will discard any unsaved changes.

- "Edit->Create Backup" to generate a single .json backup of your weapon jsons in their current state. It's recommended to place this outside of your "/weapon" folder. The app will also remember your last backup save location.

- "Edit->Restore Backup" to restore your weapon .jsons from a previously generated backup .json.

- "View->Columns" to select which columns are displayed. The program will remember these choices.

- "Help->About" to view the readme file with these instructions.

### Editor

- To repopulate the table, choose a filter category, select a subfilter, then hit the "Filter" button. If you wish to only display stock items, check the "Stock Only" box before filtering.

- To edit parameters, double-click the particular parameter for the item you wish to edit. Editable parameters are: damage*, instability*, accuracy modifier, crit chance, heat generated, tonnage, stat bonus A**, and stat bonus B**.

- All parameters but the stat bonuses are batch-edited by SubWeaponType; all weapons sharing the SubWeaponType will have their pase parameter set to your desired value to maintain continuity, then + items will have their bonus modifiers applied automatically.

- The stat bonus parameters are updated individually, and will autocorrect the appropriate parameters on assignment. Currently, the editor supports bonuses for: Dmg., Acc., Crit., Stb.Dmg., and Heat (I have not actually tested Heat as it doesn't exist as a vanilla bonus, but it should work exactly the same as the others).

*for weapons that fire multiple projectiles in a single attack (LRMs, SRMs, MachineGun), the value displayed is per volley. HOWEVER, when editing the damage and instability parameters for these items, make sure to enter the desired value PER PROJECTILE, NOT PER VOLLEY.

**when reassigning stat bonuses, stick to the vanilla game's syntax; that is, '+/-', then space, then the integer value of the bonus, then space, then the parameter abbreviation (with period). Failing to do so may result in the editor failing to recognize the appropriate parameter and not updating on batch fixes.

## Notes

- MOD AUTHORS: in order to edit batched values, your files MUST at least contain the 'WeaponSubType' key:value pair with a non-null value. This is how BWE selects what to batch edit. This goes for all batched values (not Bonus Strings).

- as this is a beta release, it's encouraged to create your own manual backup of "/weapon" until you feel confident in the backup and restore functionality. Just in case.

- works on mod weapons, so long as they follow the same json structure as the vanilla weapons. Just point the app to the folder containing the mod weapons.

- currently does not handle parameters for melee attacks.

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

## License

This project is licensed under the [GNU General Public License](https://www.gnu.org/licenses/gpl-3.0.en.html).