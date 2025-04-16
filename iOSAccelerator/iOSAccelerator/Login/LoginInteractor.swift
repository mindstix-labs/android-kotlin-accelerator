//
//  LoginInteractor.swift
//  iOSAccelerator
//
//  Created by Mindstix on 16/04/25.
//  Copyright © 2025 Mindstix Software Labs. All rights reserved.
//

import Foundation

// Login/Interactor/LoginInteractor.swift
protocol LoginInteractorProtocol {
    func login(with credentials: LoginCredentials)
}

class LoginInteractor: LoginInteractorProtocol {
    var presenter: LoginPresenterProtocol?

    func login(with credentials: LoginCredentials) {
        // Simulate login logic
        if credentials.username == "admin" && credentials.password == "admin" {
            presenter?.loginSucceeded()
        } else {
            presenter?.loginFailed(error: "Invalid username or password.")
        }
    }
}

