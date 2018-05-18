# BattleTech Weapon Editor

BWE is a tool to view and edit various values stored in json files meant to define weapon parameters used by BattleTech (2018). Common values shared between weapon types will be batch edited to maintain continuity within weapon groups, with bonus attributes from upgraded weapons automatically applied to save time and headache. Bonus values themselves are individually set for each weapon, and automatically apply to and correct for appropriate attributes on assignment.

It also includes functionality to manually generate a backup  file of the jsons as they currently exist, as well as restoring jsons from a chosen backup file.

Lastly, it displays and updates second-order statistics (like damage/ton ratios) to help users make more informed corrections. 

As this is a beta release, please consider also creating your own backup of said files in a safe location... just in case.

Before usage, please read the [readme.txt](https://github.com/iRhuel/BattleTechWeaponEditor/blob/master/src/readme.txt) contained in the package, or click "Help->About" in the app menu bar for more specific usage notes. 

## Download

Soon

### Prerequisites

You will need at least [Java 8 JRE](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) installed (Java 9 was used to make this app). A self-contained application package is in the works. 

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

## License

This project is licensed under the [GNU General Public License](https://www.gnu.org/licenses/gpl-3.0.en.html).