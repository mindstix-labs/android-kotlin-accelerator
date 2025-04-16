//
//  iOSAcceleratorApp.swift
//  iOSAccelerator
//
//  Created by Mindstix on 09/01/24.
//  Copyright © 2024 Mindstix Software Labs. All rights reserved.
//

import SwiftUI

@main
struct iOSAcceleratorApp: App {
    var body: some Scene {
        WindowGroup {
            let interactor = LoginInteractor()
            let router = LoginRouter()
            let presenter = LoginPresenter(interactor: interactor, router: router)
            LoginView(presenter: presenter)
        }
    }
}
