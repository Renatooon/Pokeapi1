package com.cortez.renato.pokeapi1

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel(
    val context: Context
) : ViewModel() {
    private val sharedPreferencesRepository = SharedPreferencesRepository().also {
        it.setSharedPreference(context)
    }

    val emailError = MutableLiveData<Boolean>()
    val passwordError = MutableLiveData<Boolean>()
    val registerSuccess = MutableLiveData<Boolean>()

    fun registerInputs(email: String, password: String, password2: String) {
        var isValid = true

        if (!isValidEmail(email)) {
            emailError.postValue(true)
            isValid = false
        }

        if (isEmptyInputs(password, password2) || !isEqual(password, password2)) {
            passwordError.postValue(true)
            isValid = false
        }

        if (isValid) {
            sharedPreferencesRepository.saveUserEmail(email)
            sharedPreferencesRepository.saveUserPassword(password)
            registerSuccess.postValue(true)
        }
    }

    private fun isEqual(password: String, password2: String) = password == password2
    private fun isEmptyInputs(password: String, password2: String) = password.isEmpty() || password2.isEmpty()
    private fun isValidEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}