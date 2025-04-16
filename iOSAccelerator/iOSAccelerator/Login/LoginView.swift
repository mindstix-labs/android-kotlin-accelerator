//
//  LoginView.swift
//  iOSAccelerator
//
//  Created by Mindstix on 16/04/25.
//  Copyright © 2025 Mindstix Software Labs. All rights reserved.
//

// Login/View/LoginView.swift
import SwiftUI
struct LoginView: View {
    @StateObject private var viewModel = LoginViewModel()

    var body: some View {
        NavigationStack(path: $viewModel.path) {
            VStack(spacing: 20) {
                Text("Login")
                    .font(.largeTitle)

                TextField("Username", text: $viewModel.username)
                    .textFieldStyle(.roundedBorder)

                SecureField("Password", text: $viewModel.password)
                    .textFieldStyle(.roundedBorder)

                if let errorMessage = viewModel.errorMessage {
                    Text(errorMessage)
                        .foregroundColor(.red)
                }

                Button("Login") {
                    viewModel.login()
                }
                .padding()
                .background(Color.blue)
                .foregroundColor(.white)
                .cornerRadius(8)
            }
            .padding()
            .navigationDestination(for: String.self) { route in
                switch route {
                case "home":
                    HomeView()
                default:
                    Text("Unknown destination")
                }
            }
        }
    }
}
