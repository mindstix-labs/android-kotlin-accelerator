//
//  HomeViewModel.swift
//  iOSAccelerator
//
//  Created by Mindstix on 16/04/25.
//  Copyright © 2025 Mindstix Software Labs. All rights reserved.
//
// Home/View/HomeViewModel.swift
import Combine

class HomeViewModel: ObservableObject {
    @Published var welcomeMessage: String = ""

    private var interactor: HomeInteractorProtocol?

    init() {
        let interactor = HomeInteractor()
        let presenter = HomePresenter()
        _ = HomeRouter()

        presenter.viewModel = self
        interactor.presenter = presenter
        self.interactor = interactor
    }

    func onAppear() {
        interactor?.fetchWelcomeMessage()
    }
}

