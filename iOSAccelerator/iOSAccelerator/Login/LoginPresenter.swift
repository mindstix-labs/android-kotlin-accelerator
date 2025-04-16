//
//  LoginPresenter.swift
//  iOSAccelerator
//
//  Created by Mindstix on 16/04/25.
//  Copyright © 2025 Mindstix Software Labs. All rights reserved.
//
import Foundation

// Login/Presenter/LoginPresenter.swift
protocol LoginPresenterProtocol {
    func loginSucceeded()
    func loginFailed(error: String)
}

class LoginPresenter: LoginPresenterProtocol {
    var viewModel: LoginViewModel?
    var router: LoginRouterProtocol?

    func loginSucceeded() {
        DispatchQueue.main.async {
            self.viewModel?.errorMessage = nil
            self.router?.navigateToHome()
        }
    }

    func loginFailed(error: String) {
        DispatchQueue.main.async {
            self.viewModel?.errorMessage = error
        }
    }
}
