package com.comphy.photo.data.repository

import com.comphy.photo.data.source.remote.client.ApiService
import com.comphy.photo.data.source.remote.response.BaseMessageResponse
import com.comphy.photo.data.source.remote.response.auth.AuthBody
import com.comphy.photo.utils.JsonParser.parseTo
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun userLogin(
        authBody: AuthBody,
        onError: (errorResponse: BaseMessageResponse?) -> Unit,
        onException: (exceptionResponse: String?) -> Unit
    ) = flow {
        val response = apiService.userLogin(authBody)
        response.suspendOnSuccess { emit(data) }
            .onError {
                val responseResult: BaseMessageResponse? =
                    errorBody?.string()?.parseTo(BaseMessageResponse::class.java)
                onError(responseResult)
                Timber.tag("On Error").e(message())
            }
            .onException {
                onException(message)
                Timber.tag("On Exception").e(message())
            }
    }.flowOn(ioDispatcher)

    suspend fun userLoginGoogle(
        email: String,
        token: String,
        onError: (errorResponse: BaseMessageResponse?) -> Unit,
        onException: (exceptionResponse: String?) -> Unit
    ) = flow {
        val authBody = AuthBody(username = email, token = token)
        val response = apiService.userLoginGoogle(authBody)
        response.suspendOnSuccess { emit(data) }
            .onError {
                val responseResult: BaseMessageResponse? =
                    errorBody?.string()?.parseTo(BaseMessageResponse::class.java)
                onError(responseResult)
                Timber.tag("On Error").e(message())
            }
            .onException {
                onException(message)
                Timber.tag("On Exception").e(message())
            }
    }.flowOn(ioDispatcher)

    suspend fun userRegister(
        name: String,
        email: String,
        password: String,
        onError: (errorResponse: BaseMessageResponse?) -> Unit,
        onException: (exceptionResponse: String?) -> Unit
    ) = flow {
        val authBody = AuthBody(name = name, username = email, password = password)
        val response = apiService.userRegister(authBody)
        response.suspendOnSuccess { emit(data) }
            .onError {
                val responseResult: BaseMessageResponse? =
                    errorBody?.string()?.parseTo(BaseMessageResponse::class.java)
                onError(responseResult)
                Timber.tag("On Error").e(message())
            }
            .onException {
                onException(message)
                Timber.tag("On Exception").e(message())
            }
    }.flowOn(ioDispatcher)

    suspend fun userRegisterGoogle(
        name: String,
        email: String,
        token: String,
        onError: (errorResponse: BaseMessageResponse?) -> Unit,
        onException: (exceptionResponse: String?) -> Unit
    ) = flow {
        val authBody = AuthBody(name = name, username = email, token = token)
        val response = apiService.userRegisterGoogle(authBody)
        response.suspendOnSuccess { emit(data) }
            .onError {
                val responseResult: BaseMessageResponse? =
                    errorBody?.string()?.parseTo(BaseMessageResponse::class.java)
                onError(responseResult)
                Timber.tag("On Error").e(message())
            }
            .onException {
                onException(message)
                Timber.tag("On Exception").e(message())
            }
    }.flowOn(ioDispatcher)

    suspend fun userRegisterVerify(
        otp: String,
        email: String,
        onError: (errorResponse: BaseMessageResponse?) -> Unit,
        onException: (exceptionResponse: String?) -> Unit
    ) = flow {
        val response = apiService.userRegisterVerify(otp, email)
        response.suspendOnSuccess { emit(data) }
            .onError {
                val responseResult: BaseMessageResponse? =
                    errorBody?.string()?.parseTo(BaseMessageResponse::class.java)
                onError(responseResult)
                Timber.tag("On Error").e(message())
            }
            .onException {
                onException(message)
                Timber.tag("On Exception").e(message())
            }
    }.flowOn(ioDispatcher)

    suspend fun userForgot(
        email: String,
        onError: (errorResponse: BaseMessageResponse?) -> Unit,
        onException: (exceptionResponse: String?) -> Unit
    ) = flow {
        val response = apiService.userForgot(email)
        response.suspendOnSuccess { emit(data) }
            .onError {
                val responseResult: BaseMessageResponse? =
                    errorBody?.string()?.parseTo(BaseMessageResponse::class.java)
                onError(responseResult)
                Timber.tag("On Error").e(message())
            }
            .onException {
                onException(message)
                Timber.tag("On Exception").e(message())
            }
    }.flowOn(ioDispatcher)

    suspend fun userForgotVerify(
        otp: String,
        email: String,
        onError: (errorResponse: BaseMessageResponse?) -> Unit,
        onException: (exceptionResponse: String?) -> Unit
    ) = flow {
        val response = apiService.userForgotVerify(otp, email)
        response.suspendOnSuccess { emit(data) }
            .onError {
                val responseResult: BaseMessageResponse? =
                    errorBody?.string()?.parseTo(BaseMessageResponse::class.java)
                onError(responseResult)
                Timber.tag("On Error").e(message())
            }
            .onException {
                onException(message)
                Timber.tag("On Exception").e(message())
            }
    }.flowOn(ioDispatcher)

    suspend fun userForgotReset(
        otp: String,
        newPassword: String,
        email: String,
        onError: (errorResponse: BaseMessageResponse?) -> Unit,
        onException: (exceptionResponse: String?) -> Unit
    ) = flow {
        val response = apiService.userForgotReset(otp, newPassword, email)
        response.suspendOnSuccess { emit(data) }
            .onError {
                val responseResult: BaseMessageResponse? =
                    errorBody?.string()?.parseTo(BaseMessageResponse::class.java)
                onError(responseResult)
                Timber.tag("On Error").e(message())
            }
            .onException {
                onException(message)
                Timber.tag("On Exception").e(message())
            }
    }.flowOn(ioDispatcher)
}