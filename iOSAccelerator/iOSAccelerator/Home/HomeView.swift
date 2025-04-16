//
//  HomeView.swift
//  iOSAccelerator
//
//  Created by Mindstix on 16/04/25.
//  Copyright © 2025 Mindstix Software Labs. All rights reserved.
//

// Home/View/HomeView.swift
import SwiftUI

struct HomeView: View {
    @StateObject private var viewModel = HomeViewModel()

    var body: some View {
        VStack(spacing: 20) {
            Text(viewModel.welcomeMessage)
                .font(.title)
                .padding()

            Image(systemName: "house.fill")
                .resizable()
                .frame(width: 100, height: 100)
                .foregroundColor(.blue)

            Spacer()
        }
        .padding()
        .onAppear {
            print("Home View")
            viewModel.onAppear()
        }
    }
}
