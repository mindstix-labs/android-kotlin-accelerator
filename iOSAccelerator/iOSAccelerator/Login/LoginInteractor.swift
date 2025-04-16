//
//  LoginInteractor.swift
//  iOSAccelerator
//
//  Created by Mindstix on 16/04/25.
//  Copyright © 2025 Mindstix Software Labs. All rights reserved.
//

import Foundation

// Login/Interactor/LoginInteractor.swift
class LoginInteractor {
    func validatePhone(_ number: String) -> Bool {
        let phoneRegex = "^[0-9]{10}$"
        return NSPredicate(format: "SELF MATCHES %@", phoneRegex).evaluate(with: number)
    }

    func validateEmail(_ email: String) -> Bool {
        let emailRegex = "^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        return NSPredicate(format: "SELF MATCHES %@", emailRegex).evaluate(with: email)
    }

    func sendOTP(to destination: String) {
        print("Sending OTP to: \(destination)")
        // Trigger backend API here
    }
}


