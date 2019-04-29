package com.mrb.remember.presentation

import com.mrb.remember.AndroidTest
import com.mrb.remember.domain.functional.Either.Right
import com.mrb.remember.domain.interactor.GetUser
import com.mrb.remember.domain.model.User
import com.mrb.remember.presentation.main.profile.ProfileViewModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.given
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class ProfileViewModelTest : AndroidTest() {

    private lateinit var profileViewModel: ProfileViewModel

    @Mock
    private lateinit var getUser: GetUser

    @Before
    fun setUp() {
        profileViewModel = ProfileViewModel(getUser)
    }

    @Test
    fun `init view model should update live data`() {
        val testUser = User(
            "firstName", "lastName", "email", "picture"
        )
        given { runBlocking { getUser.run(eq(any())) } }.willReturn(Right(testUser))

        profileViewModel.userData.observeForever {
            it.firstName shouldEqualTo testUser.firstName
            it.lastName shouldEqualTo testUser.lastName
            it.email shouldEqualTo testUser.email
            it.picture shouldEqualTo testUser.picture
        }

        runBlocking { profileViewModel.init() }
    }
}