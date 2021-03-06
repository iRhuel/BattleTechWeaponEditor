# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [v0.2.4] - 2018-07-01

### Added
- file path column to track files across multiple directories
- file name column

### Changed
- program will now recursively search subfolders within working directory for valid weapon .json files and add them to its working file list
    - it's recommended to NOT edit the game's weapon.json files directly, but instead use override files with ModTek (more info on ModTek and be found [here](https://github.com/Mpstark/ModTek))
    - because of this, it's recommended that you point BWE to your "/BATTLETECH/Mods" folder, and edit overrides this way
- backup function will now also store relative filepath of back up files (relative to working directory at time of creation) 
    - restoring from a backup file created before this update will dump the old files DIRECTLY into the working directory 
    - restoring a backup file to a new location will replicate its directory structure in that new location

## [v0.2.3] - 2018-06-28

### Changed
- bonus values will now respect operator specified at the beginning of each bonus substring (either '+' or '-'). Note that certain bonus values like Acc. will retain their original behavior (will subtract from value rather than add, even if '+' is specified). This is to maintain parity with vanilla bonus values. For these values, the opposite operator will also have the opposite effect, as expected.

## [v0.2.2] - 2018-06-04

### Added
- right-click context menu to editor tableview
- "Open File" option to table context menu
- ComponentTag selector to table context menu

### Fixed
- corrected faulty edit application logic that caused bonus values to reapply with any change to bonus-eligible fields. Should now correctly strip bonus before reapplying.
- CriticalChanceMultiplier now correctly editable (how did I even break this...?!)

## [v0.2.1] - 2018-05-30

### Added
- stopgap solution for RangeSplit, until a more elegant solution can be implemented

### Changed
- minor preferences saving optimization

### Fixed
- bonus string parser now correctly detects " Dmg. (H)" bonus
- faulty restore method logic, causing only files already detected in working directory to be restored. Should now properly restore all files (and only those files) present in backup file, and leave unbacked files untouched.
- inline column visibility toggle button now correctly saves column visibility state to preferences

## [v0.2.0] - 2018-05-29

### Added
- batch edit toggle
- tabs for future feature expansion
- support for melee weapons
- working directory display at bottom
- support for multiple bonus values in a single field

### Changed
- all fields are now single and batch editable
- batch edit now uses weapon name with '' +"s removed as search criteria for eligible batched items
    - this will better support modded items, which might otherwise be missing WeaponSubType field or match with a vanilla weapon
- re-enabled "smarter" sorting
- "Toggle Stock" now filters on selection
- columns visibility toggler is now an inline menu under "View"
- re-enabled TableViewContextMenu with custom solution (from Dan Newton)

### Removed
- removed "Filter" button, table now filters on subfilter selection

## [v0.1.2] - 2018-05-21

### Added
- column display toggles

## [v0.1.1] - 2018-05-18

### Added
- batch editable values: HeatDamage, MinRange, MaxRange, RefireModifier, ShotsWhenFired, AttackRecoil, InventorySize
- single editable values: Cost, Rarity, Purchasable

### Changed
- name and manufacturer now single editable
- changed tableview to format doubles to two decimal places
- amended README.md to list Java 10 as requirement, not Java 8
    - java 8 JRE was producing versioning errors
- changed some helper methods to return wrapper classes instead of primitives
    - this will help parse jsons missing value:keys, such as BTML mod files

### Fixed
- table now correctly refreshes after restoring from a backup file
- if working directory doesn't exist, file/dir chooser now defaults to "C://" instead of failing