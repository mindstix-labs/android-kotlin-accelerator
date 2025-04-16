//
//  LoginViewModel.swift
//  iOSAccelerator
//
//  Created by Mindstix on 16/04/25.
//  Copyright © 2025 Mindstix Software Labs. All rights reserved.
//

// Login/View/LoginViewModel.swift
import Combine

// Login/View/LoginViewModel.swift
//class LoginViewModel: ObservableObject {
//    @Published var username: String = ""
//    @Published var password: String = ""
//    @Published var errorMessage: String?
//    @Published var path: [String] = [] // used to trigger navigation
//
//    private var interactor: LoginInteractor?
//
//    init() {
//        let interactor = LoginInteractor()
//        let presenter = LoginPresenter()
//        let router = LoginRouter(viewModel: self)
//
//        presenter.viewModel = self
//        presenter.router = router
//        interactor.presenter = presenter
//        self.interactor = interactor
//    }
//
//    func login() {
//        let credentials = LoginCredentials(username: username, password: password)
//        interactor?.login(with: credentials)
//    }
//}
