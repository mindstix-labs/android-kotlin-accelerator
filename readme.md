** PRA mindstix-baseline **
==============

## Building the App
---
#### Requirements
	• Android Studio Flamingo
    • Target API: 33 (Android 13 Tiramisu)
**Requirement**: Install JetBrains Toolbox, then install Android Studio via Toolbox.  Updates are done through Toolbox.
#### Language & Tools
	• Kotlin 1.9.21 (soon Kotlin 1.9.21)
	• Gradle: v8.0
    • Android Gradle plugin: v8.0
[More information you can find here](https://example.com)

### Getting the source
---
First, check out the sources and clone all repositories:
| Creators Module  | Module Type | New Home | Github Repo | Owner |
| ------------- | ------------- | ----|-----|-----|
| Core  | Core  |  |  |  |
| UI  | Capability  |  |  | Platform  |
| Interactions  | Capability  |  |  | Platform  |
| Analytics  | Capability  |  | | Platform  |
| Storage  | Capability  |  |  | Platform  |
| Network  | Capability  |  |  | Platform  |
| Navigation  | Capability  | component-navigation |  | Platform  |
| Database  | Capability  | component-database |  | Platform  |

----

## Version control workflow
- We loosely use the "Git flow" approach: 'development' is primary branch - it should always be releasable, and only merged into when we have tested and verified that everything works, including unit test, and is good to go.
- Keep commits atomic and self-explanatory, use rebase to clean up messy branches before merging them back into the development branch.
- The Kotlin code should comply ktlint.  Invoke this through Gradle under verification task 'ktlintCheck'.  Auto-format through formatting task 'ktlintFormat'.
- Daily development is done by creating a new branch with the ticket number and following the pattern, then merge it back into the development branch via a pull request.

Example: ` `

----
## Quality control tools
- [Crashlytics](https://firebase.google.com/products/crashlytics) - helps collecting, analyzing and organizing app crash reports
- [SonarQube](https://sonarqube) - continuous inspection of code quality to perform automatic reviews with static analysis of code to detect bugs and code smells

[![ktlint](https://img.shields.io/badge/ktlint%20code--style-%E2%9D%A4-FF4081)](https://pinterest.github.io/ktlint/)  This module is ktlint-compliant.
 