//
//  LoginPresenter.swift
//  iOSAccelerator
//
//  Created by Mindstix on 16/04/25.
//  Copyright © 2025 Mindstix Software Labs. All rights reserved.
//
import Foundation

// Login/Presenter/LoginPresenter.swift
class LoginPresenter: ObservableObject {
    private let interactor: LoginInteractor
    private let router: LoginRouter

    @Published var input = LoginInput()
    @Published var selectedCountryCode: String = "+91"
    
    let countryCodes = ["+91", "+1", "+44"] // Extend as needed

    init(interactor: LoginInteractor, router: LoginRouter) {
        self.interactor = interactor
        self.router = router
    }

    var isFormValid: Bool {
        input.isUsingPhone
        ? interactor.validatePhone(input.phoneNumber, selectedCountryCode: self.selectedCountryCode)
        : interactor.validateEmail(input.email)
    }

    func sendOTP() {
        guard isFormValid else { return }

        if input.isUsingPhone {
            interactor.sendOTP(to: "\(selectedCountryCode)\(input.phoneNumber)")
        } else {
            interactor.sendOTP(to: input.email)
        }
    }

    func goToSignUp() {
        router.navigateToSignUp()
    }
}

