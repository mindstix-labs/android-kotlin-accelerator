/**
 * Copyright (c) 2023 Mindstix Software Labs
 * All rights reserved.
 */

package com.mindstix.onboarding.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.mindstix.onboarding.intents.LoginIntent
import com.mindstix.onboarding.intents.LoginViewStates

/**
 * Composable function representing the Login Screen.
 *
 * @param state The current state of the Login Screen loaded with data.
 * @param keyboardController The software keyboard controller.
 * @param userIntent A function to handle user intents related to the Login Screen.
 *
 * @author Abhijeet Kokane, Asim Shah
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    state: LoginViewStates.LoadedData,
    keyboardController: SoftwareKeyboardController?,
    userIntent: (LoginIntent) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var skinTone by remember { mutableStateOf("") }
    var favoriteColor by remember { mutableStateOf("") }
    var selectedClothingStyle by remember { mutableStateOf("Casual") }
    var openToNewStyles by remember { mutableStateOf(false) }
    var selectedFit by remember { mutableStateOf("Regular") }
    var selectedOccasion by remember { mutableStateOf("Work") }
    var localWeather by remember { mutableStateOf(false) }
    var selectedBudget by remember { mutableStateOf("Medium") }
    var sustainablePreference by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Welcome to Fashion Suggester!", style = MaterialTheme.typography.bodyMedium)

        // Personal Info Section
        Text("Personal Information", style = MaterialTheme.typography.bodyMedium)
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height (cm or ft/in)") })
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight (kg or lbs)") })
        OutlinedTextField(
            value = skinTone,
            onValueChange = { skinTone = it },
            label = { Text("Skin Tone") })
        OutlinedTextField(
            value = favoriteColor,
            onValueChange = { favoriteColor = it },
            label = { Text("Favorite Color") })

        Spacer(modifier = Modifier.height(16.dp))

        // Style Preferences Section
        Text("Style Preferences", style = MaterialTheme.typography.bodyMedium)
        Text("Preferred Clothing Style:")
        ClothingStyleOptions(
            selectedClothingStyle,
            onStyleSelected = { selectedClothingStyle = it })

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = openToNewStyles, onCheckedChange = { openToNewStyles = it })
            Text("Open to trying new styles")
        }

        // Fit Preferences Section
        Text("Fit Preference:")
        FitOptions(selectedFit, onFitSelected = { selectedFit = it })

        // Occasions Section
        Text("Typical Occasion:")
        OccasionOptions(selectedOccasion, onOccasionSelected = { selectedOccasion = it })

        // Weather & Location Section
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = localWeather, onCheckedChange = { localWeather = it })
            Text("Consider local weather")
        }

        // Budget Preferences Section
        Text("Budget:")
        BudgetOptions(selectedBudget, onBudgetSelected = { selectedBudget = it })

        // Sustainability Preference
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = sustainablePreference,
                onCheckedChange = { sustainablePreference = it })
            Text("Eco-friendly options")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Submit Button
        Button(onClick = {
            val data = UserData(
                name,
                height,
                weight,
                skinTone,
                favoriteColor,
                selectedClothingStyle,
                openToNewStyles,
                selectedFit,
                selectedOccasion,
                localWeather,
                selectedBudget,
                sustainablePreference
            )
//            userIntent.invoke(LoginIntent.NavigateToHomeScreen(data))
        }) {
            Text("Submit")
        }
    }
}

@Composable
fun ClothingStyleOptions(selectedStyle: String, onStyleSelected: (String) -> Unit) {
    val styles = listOf("Casual", "Formal", "Sporty", "Streetwear", "Bohemian")
    Column {
        styles.forEach { style ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedStyle == style,
                    onClick = { onStyleSelected(style) }
                )
                Text(style)
            }
        }
    }
}

@Composable
fun FitOptions(selectedFit: String, onFitSelected: (String) -> Unit) {
    val fits = listOf("Loose", "Regular", "Fitted")
    Column {
        fits.forEach { fit ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedFit == fit,
                    onClick = { onFitSelected(fit) }
                )
                Text(fit)
            }
        }
    }
}

@Composable
fun OccasionOptions(selectedOccasion: String, onOccasionSelected: (String) -> Unit) {
    val occasions = listOf("Work", "Casual Outings", "Parties", "Special Events")
    Column {
        occasions.forEach { occasion ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedOccasion == occasion,
                    onClick = { onOccasionSelected(occasion) }
                )
                Text(occasion)
            }
        }
    }
}

@Composable
fun BudgetOptions(selectedBudget: String, onBudgetSelected: (String) -> Unit) {
    val budgets = listOf("Low", "Medium", "High")
    Column {
        budgets.forEach { budget ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = selectedBudget == budget,
                    onClick = { onBudgetSelected(budget) }
                )
                Text(budget)
            }
        }
    }
}

// Data class to hold the user input
data class UserData(
    val name: String,
    val height: String,
    val weight: String,
    val skinTone: String,
    val favoriteColor: String,
    val clothingStyle: String,
    val openToNewStyles: Boolean,
    val fitPreference: String,
    val occasions: String,
    val localWeather: Boolean,
    val budget: String,
    val sustainablePreference: Boolean
)
