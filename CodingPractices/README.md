# Swift Coding Standard

iOS coding standards are a collection of guidelines and best practices that developers should adhere to when writing code for iOS applications. These standards are designed to promote consistency, maintainability, and readability in the codebase, facilitating collaboration on projects and reducing the likelihood of common errors.

## Comply with the outlined Points
#### 1. Naming:
- Follow Swift’s naming conventions: Use camelCase for variables and functions, PascalCase for classes/structs, and choose descriptive names that convey the object’s purpose. 
- Using prefixes for UI components in computed properties provides clarity, allowing readers to instantly identify an object’s purpose without relying on context.

<p align="center">
<img width="580px" src="https://miro.medium.com/v2/resize:fit:4800/format:webp/1*ws1jq4qz56xXJMdbZjN2rw.png" alt="aaosp"></img></p>

#### 2. Breaking Down Views with Computed Properties:
- Initially, the individual placed everything in the 'body,' but they recognized that adopting an approach where they wouldn't want to revisit the code was not ideal. Consequently, a solution was found: breaking down larger views into smaller computed properties or, when necessary, utilizing methods for handling data passing.

```swift
// Main Struct
struct  MainStructView: View {  
  // Main body
    var body: some  View {  
        VStack {  
            headerSection  // call for computed property
            userDetail  
        }  
    }  
      // declaring headerSection
    var headerSection: some  View {  
        HStack(spacing: 10) {  
            Text("Edit your profile")  
            Spacer()  
            btnEdit  
        }  
    }  
      // declaring user Details
    var userDetail: some  View {  
        Text("your detail goes here.")  
    }  
}
```
#### 3. Separate Button Actions for Clarity:
- Keep SwiftUI button actions clean by placing complex logic in separate methods, instead of writing all logic in an action block. Enhance code readability and maintainability.

```swift
// Main Struct
struct  MainStructView: View {  
  // Main body
    var body: some  View {  
        VStack {  
            Button("PrintHello", action:buttonAction()) // Button Call
        }  
    } 
    // button action function
    private func buttonAction() {
        print("Hello")
    } 
}
```
- Another approach is to call the button action from the viewModel to reduce the computation at the view

#### 4. Enhance Efficiency using @ViewBuilder Property Wrapper:
- In SwiftUI, when there's a need to conditionally display different views, the framework mandates that a view must return a singular, definite type. To address this and avoid compile-time errors, developers often resort to wrapping views in AnyView or Group. 
- Nevertheless, utilizing AnyView is not considered best practice as it recreates the entire hierarchy each time, potentially impacting performance and efficiency. Instead, it is recommended to employ the @ViewBuilder property wrapper.

<p align="center">
<img width="580px" src="https://miro.medium.com/v2/resize:fit:4800/format:webp/1*6TwXXPxYF__8fHUM_BvWbw.png" alt="aaosp"></img>
<img width="580px" src="https://miro.medium.com/v2/resize:fit:4800/format:webp/1*L1EtoduKE-9pnNVQVAWN8g.png" alt="aaosp"></img>
</p>

#### 5. DRY (Don’t Repeat Yourself):
- Say no to repetitive code. If you’re copying and pasting code, explore the option of developing a shared function or component.
-  Some Common Components like Separator lines can be created in CommonUI files
-  Create a Shared Function for Handling common Business Logic and API Requests
-  [Create custom modifiers for creating common UI components at once](https://www.hackingwithswift.com/books/ios-swiftui/custom-modifiers)

#### 6. Optional:
- Swift optional handle nil values. Use them when values are absent. Avoid forced unwrapping; instead, use optional binding, guard, or nil coalescing for safe handling.

```swift
    var selectedId: Int? // Declaring an optional variable  
  
    // Optional binding  
    if  let id = selectedId {  
        // Use optionalValue safely  
    } else {  
        // Handle the absence of a value  
    }  
    // Using guard to safely unwrap the optional  
    guard selectedId else {  
        return  
    }  
    // Nil coalescing - When required to provide default value  
    let value = selectedId ??  1
```
#### 7. Avoid Static Strings: Utilize Constants or Enums for Clarity:
 - Adhering to best practices involves substituting static string literals with constants or enums. This approach enhances code clarity, minimizes errors, and facilitates maintainability by centralizing string values for seamless updates and consistency.

<p align="center">
<img width="580px" src="https://miro.medium.com/v2/resize:fit:4800/format:webp/1*ilibcS2rtUNUi4pIHbgQuw.png" alt="aaosp"></img>
<img width="580px" src="https://miro.medium.com/v2/resize:fit:4800/format:webp/1*SU1ntxFwR-Blo0NVs51h5A.png" alt="aaosp"></img>
</p>

#### 8. Keep methods Small:
- Remember the ‘Keep It Simple’ rule: Each method should tackle just one problem, not a bunch of them. If you’ve got lots of conditions in a method, try breaking them into smaller, easy-to-read methods. It makes your code less confusing and helps you spot bugs faster.

#### 9. Use Final:
- The 'final' keyword imposes limitations on subsequent inheritance or overriding, contributing to enhanced runtime performance. When developing a class or function that either logically cannot be overridden or is intended to remain unaltered by subclassing, it is advisable to designate it as 'final.'
-  The Notification ViewModel mentioned earlier is labeled as 'final' not only to prevent inheritance but also to optimize performance.

#### 10. The improved best practices for using MVVM:
- Use ViewModel classes to manage the state and logic of your views and use the model for data and business logic.

**Decoupling from View:**  Keep your View clean by avoiding the following tasks in the View file:

-   **_Calling API:_** _Move API calls to the ViewModel._
-   **_Business Logic:_** _Business logic should be kept separate from the view._
-   **_Static Data:_** _Use model classes to set up and manage static data if required. ViewModel can hold and expose this data as needed._
-   **_State Management:_** _Manage every aspect of UI state (e.g., loading, API success, API failure) within the ViewModel._

<p align="center">
<img width="580px" src="https://miro.medium.com/v2/resize:fit:4800/format:webp/1*FAYj2-A2l7ukVY-yqwUUFg.png" alt="aaosp"></img>
<img width="580px" src="https://miro.medium.com/v2/resize:fit:1106/format:webp/1*iWyDOevg7-VuoRW9RK7Rbw.png" alt="aaosp"></img>
</p>

#### 11. Use delegation and protocols:
1.  **Delegation for Effective Communication:**
    
    -   Utilize delegation as a mechanism where one object can request another to perform tasks, fostering efficient communication between components.
2.  **Protocols as Contracts:**
    
    -   In Swift, a protocol functions as a contract, outlining a set of methods or properties.
    -   Any Swift type adhering to the protocol must implement the specified methods and properties.
3.  **Protocol Extensions for Default Implementations:**
    
    -   Enhance modularity by creating protocols and extending them using Protocol Extensions.
    -   Leverage Protocol Extensions to provide default method implementations, streamlining the adherence process for conforming types.
4.  **Cleaner and More Modular Code:**
    
    -   Employ delegation and protocols to achieve cleaner and more modular code.
    -   Facilitate seamless communication between various components by adhering to the principles of delegation and protocol usage.

#### 12. Preventing Retain Cycles in Swift: Utilizing weak, unowned, and Capture Lists:
- These practices are essential for maintaining memory efficiency and preventing strong reference cycles. 
- [Dive into the details by checking out this blog by](https://medium.com/mackmobile/avoiding-retain-cycles-in-swift-7b08d50fe3ef)

#### 13. Commenting:
Commenting code in SwiftUI, like in any programming language, involves providing clear and concise explanations for various parts of your code. Here are some best practices for commenting in SwiftUI:

1.  **Use Inline Comments Sparingly:**
    
    -   Insert short, to-the-point comments inline with the code to explain complex logic, edge cases, or any non-obvious decisions. However, avoid over-commenting, as excessively verbose comments can clutter the code.

```swift
    // Increment the counter variable
    counter += 1
``` 
    
2.  **Header Comments for Structured Sections:**
    
    -   Use header comments to delineate sections of your SwiftUI code. This is especially helpful in larger files to provide a quick overview.
  
```swift
    // MARK: - View Lifecycle
``` 
    
3.  **Documenting SwiftUI Views:**
    
    -   Comment the purpose and functionality of SwiftUI views, particularly if they are complex or involve specific UI patterns.
   
   ```swift
    struct CustomView: View {
        // MARK: - Properties
    
        // MARK: - Body
        var body: some View {
            // Main content goes here
        }
    }
``` 

#### 14. Swiftlint:
- [SwiftLint](https://github.com/realm/SwiftLint) is a tool to enforce Swift style guide rules and conventions.

#### 15. Avoid code smell:
   Some of the known code smells:

-   Code should not nest more than 2 closure expressions.

**Noncompliant Code Example**

```swift 
var notificationsList: some View {  
  List {  
    ForEach(notifications) { notification in  
        VStack { // Noncompliant  
          Text(notification.title)  
          Text(notification.subTitle)  
        }  
    }  
  }  
}
```

**Compliant Code Example**


```swift
var notificationsList: some View {  
  List {  
    ForEach(notifications) { notification in  
         notificationCell(for: notification)  
    }  
  }  
}  
  
func notificationCell(for notification: NotificationsData): some View {  
    VStack {  
          Text(notification.title)  
          Text(notification.subTitle)  
    }  
}
```

-   **_Remove commented-out code_**: Programmers should not comment out code as it bloats programs and reduces readability.
-   Refactor function to reduce its  **_Cognitive Complexity_**  from 17 to the 15 allowed. To refactor a function and reduce its Cognitive Complexity, you can consider the following strategies:  
    - Break down complex logic into smaller functions.  
    - Apply the Single Responsibility Principle.  
    - Reduce Nesting  
    - Simplify Conditions  
    - Use Guard Clauses  
    - Remove Redundant Code
-   Add a nested comment explaining why this function is empty, or complete the implementation.
-   Remove the unnecessary Boolean literal.


```swift
//Noncompliant Code Example  
  
if locationEdited == true { // Remove the unnecessary Boolean literal.  
 }  
  
//Compliant Code Example  
  
// You don't need to compare it explicitly to true. You can directly use it in the if statement to check if it's true.  
if locationEdited{  
}
```

-   Merge the if statement with the nested one.


```swift
//Noncompliant Code Example  
  
if locationEdited {  
    if locationName == "XYZ" { //Merge this if statement with the nested one.  
      
     }  
}  
  
//Compliant Code Example  
  
if locationEdited && locationName == "XYZ"{  
}
```
## Some other Key Considerations

-   Avoid Nested if.
-   Avoid long parameter lists.
-   Prefer  **switch**  over if-else.
-   Use Guard for early exits.
-   Always  **delete unused code**  also code that is generated by IDE.
-   Handle all the possible errors for tasks eg. no internet connection, API failure…etc
-   Use Generics when you want to create flexible and reusable code that can work with different types
-   Follow SOLID Principle

<p align="center">
<img width="580px" src="https://cdn-media-1.freecodecamp.org/images/0*q3-4kypImPD0VDPg.jpg" alt="aaosp"></img>
</p>

<p align="Right">
<h>Contributor</h> 
<br>
Anshul Rokde
</p>

