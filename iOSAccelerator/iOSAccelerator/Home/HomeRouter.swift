//
//  HomeRouter.swift
//  iOSAccelerator
//
//  Created by Mindstix on 16/04/25.
//  Copyright © 2025 Mindstix Software Labs. All rights reserved.
//

// Home/Router/HomeRouter.swift
protocol HomeRouterProtocol {
    func navigateToSomewhere()
}

class HomeRouter: HomeRouterProtocol {
    func navigateToSomewhere() {
        // Navigation logic (placeholder)
        print("Navigating to another screen from Home")
    }
}
