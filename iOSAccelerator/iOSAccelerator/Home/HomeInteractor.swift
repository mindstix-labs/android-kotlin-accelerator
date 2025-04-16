//
//  HomeInteractor.swift
//  iOSAccelerator
//
//  Created by Mindstix on 16/04/25.
//  Copyright © 2025 Mindstix Software Labs. All rights reserved.
//
import Foundation
// Home/Interactor/HomeInteractor.swift
protocol HomeInteractorProtocol {
    func fetchWelcomeMessage()
}

class HomeInteractor: HomeInteractorProtocol {
    var presenter: HomePresenterProtocol?

    func fetchWelcomeMessage() {
        let message = HomeMessage(welcomeText: "Welcome to the Home Screen 🎉")
        presenter?.didFetchWelcomeMessage(message)
    }
}

