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
    @ObservedObject var presenter: LoginPresenter

    var body: some View {
        VStack {
            Image("loginBackground") // replace with the 3D image asset
                .resizable()
                .scaledToFill()
                .padding(.top, 100)
                .padding(.bottom, 20)
                .frame(width: UIScreen.main.bounds.width - 40, height: 200)
                .clipped()

            Text("Login to Your Account")
                .font(.title3)
                .fontWeight(.semibold)
                .foregroundColor(.black)
            
            Picker(selection: $presenter.input.isUsingPhone, label: Text("")) {
                Text("Email").tag(false)
                Text("Phone Number").tag(true)
            }
            .pickerStyle(SegmentedPickerStyle())
            .padding()
            
            if presenter.input.isUsingPhone {
                HStack {
                    Menu {
                        ForEach(presenter.countryCodes, id: \.self) { code in
                            Button(action: {
                                presenter.selectedCountryCode = code
                            }) {
                                Text(code)
                            }
                        }
                    } label: {
                        Text(presenter.selectedCountryCode)
                            .padding(.horizontal)
                    }
                    
                    TextField("Phone Number", text: $presenter.input.phoneNumber)
                        .keyboardType(.phonePad)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                }.padding(.horizontal)
            } else {
                TextField("Email", text: $presenter.input.email)
                    .keyboardType(.emailAddress)
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .padding(.horizontal)
            }
            
            Button("Send OTP") {
                presenter.sendOTP()
            }
            .disabled(!presenter.isFormValid)
            .frame(maxWidth: .infinity)
            .padding()
            .background(presenter.isFormValid ? Color.orange : Color.gray)
            .foregroundColor(.white)
            .cornerRadius(12)
            .padding()
            
            HStack {
                Text("Don’t have account?")
                    .foregroundColor(.black)
                Button("Create Account") {
                    presenter.goToSignUp()
                }
                .foregroundColor(.orange)
            }
            
            Spacer()
        }
    }
}

