package com.mrb.remember.presentation.main.home

import androidx.lifecycle.MutableLiveData
import com.mrb.remember.domain.interactor.GetNextStudyTime
import com.mrb.remember.domain.interactor.UseCase
import com.mrb.remember.presentation.platform.BaseViewModel
import com.mrb.remember.testing.OpenForTesting
import java.util.Date
import javax.inject.Inject

@OpenForTesting
class HomeViewModel @Inject constructor(
    private val getNextStudyTime: GetNextStudyTime
) : BaseViewModel() {

    var studyTime: MutableLiveData<Date> = MutableLiveData()

    fun init() {
        getNextStudyTime(UseCase.None()) {
            it.either(::handleFailure, ::handleStudyTime)
        }
    }

    private fun handleStudyTime(studyTime: Date) {
        this.studyTime.value = studyTime
    }
}