package com.mrb.remember.domain.repository

import com.mrb.remember.UnitTest
import com.mrb.remember.data.UserRepositoryImp
import com.mrb.remember.data.api.UserApiService
import com.mrb.remember.data.utils.NetworkHandler
import com.mrb.remember.domain.exception.Failure.NetworkConnection
import com.mrb.remember.domain.exception.Failure.ServerError
import com.mrb.remember.domain.functional.Either
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqual
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class UserRepositoryTest : UnitTest() {

    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var networkHandler: NetworkHandler
    @Mock
    private lateinit var service: UserApiService

    @Before
    fun setUp() {
        userRepository = UserRepositoryImp(service, networkHandler)
    }

    @Test
    fun `user service should return network failure when no connection`() {
        given { networkHandler.isConnected }.willReturn(false)

        val user = userRepository.user()

        user shouldBeInstanceOf Either::class.java
        user.isLeft shouldEqual true
        user.either({ failure -> failure shouldBeInstanceOf NetworkConnection::class.java }, {})
        verifyZeroInteractions(service)
    }

    @Test
    fun `user request should catch exceptions`() {
        given { networkHandler.isConnected }.willReturn(true)

        val user = userRepository.user()

        user shouldBeInstanceOf Either::class.java
        user.isLeft shouldEqual true
        user.either({ failure -> failure shouldBeInstanceOf ServerError::class.java }, {})
    }
}