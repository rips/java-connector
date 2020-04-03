# Changelog
## [3.13.0] - 2020-04-03
### Added
- Endpoint to fetch all profiles
- Extend ProfileHandler to fetch analysis profiles by language

## [3.12.2] - 2020-03-09
### Fixed
- Wrong entity wrapper for invite organization

## [3.12.1] - 2020-02-27
### Fixed
- Expose appId in ScanHandler

## [3.12.0] - 2020-02-27
### Added
- The ApplicationHandler creates or fetch an app by name
  - Provides the corresponding ScanHandler
### Misc
- Use internal common gradle plugin

## [3.11.1] - 2020-02-17
### Fixed
- Fix pom file generation

## [3.11.0] - 2020-02-17
### Added
- Invite an organization

## [3.10.0] - 2020-01-28
### Added
- Library Service
- Issue Manager provides a suspending method to get all issues
  - Added an optional chunk action
### Fixed
- Payload of user invitation and reset POST requests
- Process Service
- Check the state of the coroutine while querying issues
### Misc
- Update swagger file (without version in filename)