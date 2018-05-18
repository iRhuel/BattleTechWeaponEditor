# BattleTech Weapon Editor

BWE is a tool to view and edit various values stored in json files meant to define weapon parameters used by BattleTech (2018). Common values shared between weapon types will be batch edited to maintain continuity within weapon groups, with bonus attributes from upgraded weapons automatically applied to save time and headache. Bonus values themselves are individually set for each weapon, and automatically apply to and correct for appropriate attributes on assignment.

It also includes functionality to manually generate a backup  file of the jsons as they currently exist, as well as restoring jsons from a chosen backup file.

Lastly, it displays and updates second-order statistics (like damage/ton ratios) to help users make more informed corrections. 

As this is a beta release, please consider also creating your own backup of said files in a safe location... just in case. Please message me if/when you run into any bugs or unexpected behavior. 

Before usage, please read the [readme.txt](https://github.com/iRhuel/BattleTechWeaponEditor/blob/master/src/readme.txt) contained in the package, or click "Help->About" in the app menu bar for more specific usage notes. 

## Download

[Latest release](https://github.com/iRhuel/BattleTechWeaponEditor/releases)

Unzip to a location of your choice, and run the .jar file with JRE (must have JRE installed). If it doesn't automatically do so, right click on .jar, click 'open with...', and select 'Java(TM) Platform SE Binary'.

### Prerequisites

You will need at least [Java 10 JRE](http://www.oracle.com/technetwork/java/javase/downloads/jre10-downloads-4417026.html) installed (support for Java 9, which was used to develop this app, has ended). A self-contained application package is in the works. 

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