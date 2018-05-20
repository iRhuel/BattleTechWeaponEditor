# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/)
and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [v0.1.1] - 2018-05-18
### Changed
- changed tableview to format doubles to two decimal places
- amended README.md to list Java 10 as requirement, not Java 8
    - java 8 JRE was producing versioning errors
- changed some helper methods to return wrapper classes instead of primitives
    - this will help parse jsons missing value:keys, such as BTML mod files
    
### Fixed
- table now correctly refreshes after restoring from a backup file