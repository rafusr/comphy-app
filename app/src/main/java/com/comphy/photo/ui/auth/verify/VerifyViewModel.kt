package com.comphy.photo.ui.auth.verify

import androidx.lifecycle.MutableLiveData
import com.comphy.photo.base.BaseAuthViewModel
import com.comphy.photo.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : BaseAuthViewModel() {

    val resendMessage = MutableLiveData<String>()

    suspend fun userRegisterVerify(otp: String, email: String) {
        authRepository.userRegisterVerify(
            otp,
            email,
            onError = { message.postValue(it.message) }
        )
            .onStart { isLoading.postValue(true) }
            .onCompletion { isLoading.postValue(false) }
            .collect { authResponse.postValue(it.message) }
    }

    suspend fun userRegisterResendCode(email: String, password: String) {
        authRepository.userRegister(
            email,
            password,
            onError = { resendMessage.postValue("Failed Resend Code") }
        )
            .onStart { isLoading.postValue(true) }
            .onCompletion { isLoading.postValue(false) }
            .collect { resendMessage.postValue("Resend Code Success") }
    }

    suspend fun userForgotVerify(otp: String, email: String) {
        authRepository.userForgotVerify(
            otp,
            email,
            onError = { message.postValue(it.message) }
        )
            .onStart { isLoading.postValue(true) }
            .onCompletion { isLoading.postValue(false) }
            .collect { authResponse.postValue(it.message) }
    }

    suspend fun userForgotResendCode(email: String) {
        authRepository.userForgot(
            email,
            onError = { resendMessage.postValue("Failed Resend Code") }
        )
            .onStart { isLoading.postValue(true) }
            .onCompletion { isLoading.postValue(false) }
            .collect { resendMessage.postValue("Resend Code Success") }
    }

}