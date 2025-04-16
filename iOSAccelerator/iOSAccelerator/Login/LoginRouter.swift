//
//  LoginRouter.swift
//  iOSAccelerator
//
//  Created by Mindstix on 16/04/25.
//  Copyright © 2025 Mindstix Software Labs. All rights reserved.
//
import Foundation
// Login/Router/LoginRouter.swift
protocol LoginRouterProtocol {
    func navigateToHome()
}

class LoginRouter: LoginRouterProtocol {
    weak var viewModel: LoginViewModel?

    init(viewModel: LoginViewModel) {
        self.viewModel = viewModel
    }

    func navigateToHome() {
        DispatchQueue.main.async {
            self.viewModel?.path.append("home")
        }
    }
}
