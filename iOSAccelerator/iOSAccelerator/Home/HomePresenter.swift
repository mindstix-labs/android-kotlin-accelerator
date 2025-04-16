//
//  HomePresenter.swift
//  iOSAccelerator
//
//  Created by Mindstix on 16/04/25.
//  Copyright © 2025 Mindstix Software Labs. All rights reserved.
//

import Foundation
// Home/Presenter/HomePresenter.swift
protocol HomePresenterProtocol {
    func didFetchWelcomeMessage(_ message: HomeMessage)
}

class HomePresenter: HomePresenterProtocol {
    var viewModel: HomeViewModel?

    func didFetchWelcomeMessage(_ message: HomeMessage) {
        DispatchQueue.main.async {
            self.viewModel?.welcomeMessage = message.welcomeText
        }
    }
}

