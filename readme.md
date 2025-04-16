<h1 align="center">Mindstix: iOS Accelerator</h1>
<p align="center">
Reusable Architectures, Effortless Integration, and Time-Efficient Development.
</p>
<p align="center">
<a href="#">
<img alt="iPhone OS" src="https://img.shields.io/badge/OS-iOS-blue?style=flat-square&logo=apple">
</a>
<a href="#">
<img alt="iPhone OS" src="https://img.shields.io/badge/Language-Swift-orange?style=flat-square&logo=swift">
</a>
</p>
<br>
<p align="center">
<img width="180px" src="https://media.licdn.com/dms/image/C4D0BAQGGkbotFEw4wg/company-logo_200_200/0/1652264978042/mindstix_software_labs_private_limited_logo?e=2147483647&v=beta&t=ACMmV88XBaSF-scaqXWBA0LkzHe1EtP1w5N0CJuc2as" alt="aaosp"></img></p>

### Why Accelerator ? ###

- Streamline the Development Process
- Improve Efficiency (Reusability)
- Save Time and Efforts
- Reduce Development Resources
- Consistent Architecture
- Rapid Prototyping
- Third Party Integrations
- Faster Time to Market

----
### Baseline ###
The iOS baseline, acting as the foundation for application development, establishes the fundamental structure and architecture that underlies the app's features and capabilities. This baseline is crucial for maintaining consistency, ensuring maintainability, and facilitating scalability throughout the entire development process.

----
### Contents ###
- SwiftUI
- MVVM (Model-View-ViewModel) Clean Architecture
- Package dependency manager (Dependency Injection)
- Reusable Modules
    - Network Module
        - REST: URLSession
        - GraphQl
- Data Storage Module
    - General Data: DataModel
    - Preference: Core Data , Realm
    - Secure Data: KeyChain, Userdefaults
- Navigation Components
    - Tab bar menu
    - Hamburger menu
- Common Utilities
- App Logger
- MBaaS Capabilities: Firebase
    - Remote Configuration
    - Crashlytics
    - Performance Monitoring
    - Analytics
    - Push Notifications
- Auth
    - Social Logins
        - Google
        - Facebook
    - OAuth 2.0
- CI/CD using Github Actions

----
### Baseline Architecture Overview ###

#### Clean Architecture ####
##### Core #####
The Core module serves as the bedrock of your application, comprising essential components and functionalities that are deemed universal and stable. These elements are designed to undergo minimal changes, offering a dependable and consistent foundation for the entire application. The primary objective of the Core module is to define a collection of fundamental building blocks that can be utilized throughout the application with minimal need for substantial modifications.

##### Capabilities #####
The Capabilities module serves as an amalgamation of essential shared functionalities vital for constructing the application. Within this module, one can find a spectrum of capabilities spanning areas such as networking, presentation, and utilities, all of which are indispensable across diverse features. The overarching aim is to forge a modular and reusable toolkit, fostering the seamless integration of these tools into various facets of the application. This approach not only enhances efficiency but also contributes to the creation of a more versatile and adaptable development framework.

##### Features #####
The Feature modules embody distinct functionalities or components within the application, with each feature meticulously crafted to encapsulate a self-contained and independent unit of user-facing functionality. These features are conceptualized as standalone entities, meticulously designed to enhance the application's modularity, facilitate ease-of-maintenance, and streamline the development process. By treating each feature as an isolated and cohesive unit, developers gain the flexibility to address specific user needs or business requirements with precision, fostering a modular architecture that not only enhances maintainability but also affords greater adaptability and scalability to the overall application structure.

##### App #####
The App module functions as the master conductor, harmonizing various elements within the application. Serving as the central integration point, it unifies Core components, shared Capabilities, and individual Features. Tasked with assembling these elements, the App module plays a crucial role in defining the comprehensive structure, dependencies, and interactions across different facets of the application. It is in this module that the overall blueprint for the application is articulated, ensuring that Core functionalities are readily available, shared Capabilities are easily accessible, and individual Features seamlessly come together to forge a unified and functional Android application. Through this orchestration, the App module stands as the linchpin in guaranteeing a cohesive and robust user experience.

#### Folder Structure ####

```
app
...... Modules
 └── networking
     └── rest
     
... features
 └── Login
 └── home
 └── Tabbar
 └── Hamburger

...... capabilities
    └── presentation
         └── reusable-ui-components
         └── theme
    └── util
    
......... core
     └── Managers
        └── Secure Data
        └── exception-handlers (API)
        └── Peference
        └── logger
        └── Analytics
            └── Manager
            └── Constant
        └── storage
...............
```

----
#### Application Architecture ####
##### MVVM  Architecture #####

<p align="center">
<img width="580px" src="https://miro.medium.com/v2/resize:fit:1400/format:webp/1*_DMvajfGcKQoIOWpLysa1Q.png" alt="aaosp"></img></p>

MVVM (Model-View-ViewModel) architecture is a software design pattern designed to segregate program logic from user interface controls.

Key benefits:
1. **Separation of Concerns**
2. **Maintainability**
3. **Reusability**
4. **Flexibility**
5. **Testability**
6. **Enhanced Collaboration**
7. **Scalability**
8. **Data Binding**


Reference Links:
- [Reference 1](https://medium.com/@abhilash.mathur1891/mvvm-in-ios-swift-aa1448a66fb4)
- [Reference 2](https://letcreateanapp.com/2022/02/26/mvvm-in-swift/)
- [Reference 3](https://www.youtube.com/watch?v=FwGMU_Grnf8&pp=ygUNbXZ2bSBpbiBzd2lmdA%3D%3D)

----
### Requirements ###
- Xcode 15
- Minimum deployment Target: iOS 15 
- Minimum swift version: Swift 4.2

----
### Guidelines ###
-  [Code Guidelines](https://github.com/mindstix-labs/ios-swift-accelerator/blob/Feature/HomePage/CodingPractices/README.md)

----
### Contributors ###
- Srinivas Reddy
- Anshul Rokde
- Abdul Khan

----
### Uses ###

<p align="center">
<a href="#">
<img alt="Ktlint" src="https://img.shields.io/badge/SwiftLint%20-%E2%9D%A4-FF4081">
</a>
<a href="#">
<img alt="Retrofit" src="https://flat.badgen.net/badge/Database/RealmDB">
</a>
<a href="#">
<img alt="Firebase" src="https://img.shields.io/badge/Google-Firebase-yellow">
</a>
</p>

----
### Licenses ###
MIT License

```
Copyright (c) 2023 Mindstix Software Labs, Inc.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
----
### Get in touch ###


[Mindstix](https://www.mindstix.com/)

