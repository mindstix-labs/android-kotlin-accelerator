//
//  Environment.swift
//  iOSAccelerator
//
//  Created by Mindstix on 11/01/24.
//  Copyright © 2024 Mindstix Software Labs. All rights reserved.
//

import Foundation

public enum Environment {

    enum Keys {
        static let baseUrl = "BASE_URL"
    }
    
    // MARK: - Fetch Plist file
    private static let infoDictonary: [String: Any] = {
        guard let dict = Bundle.main.infoDictionary else {
            fatalError("plist file not found")
        }
        return dict
    }()

    // MARK: - Fetch Base_Url
    static let baseUrl: String = {

        guard let baseUrl = Environment.infoDictonary[Keys.baseUrl] as? String else {
             fatalError("Base Url not set in plist")
        }

        return baseUrl
    }()

}
