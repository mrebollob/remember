package com.mrb.remember.domain.interactor

import com.mrb.remember.UnitTest
import com.mrb.remember.domain.functional.Either.Right
import com.mrb.remember.domain.model.Hour
import com.mrb.remember.domain.repository.ConfigRepository
import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetStudyHourTest : UnitTest() {

    private lateinit var getStudyHour: GetStudyHour

    @Mock
    private lateinit var repository: ConfigRepository

    @Before
    fun setUp() {
        getStudyHour = GetStudyHour(repository)
        given { repository.getStudyHour() }.willReturn(Right(Hour.empty()))
    }

    @Test
    fun `should get data from repository`() {
        runBlocking { getStudyHour.run(UseCase.None()) }

        verify(repository).getStudyHour()
        verifyNoMoreInteractions(repository)
    }
}
