package com.mrb.remember.presentation.levels

import androidx.lifecycle.MutableLiveData
import com.mrb.remember.domain.interactor.GetCompletedDay
import com.mrb.remember.domain.interactor.GetHomework
import com.mrb.remember.domain.interactor.SaveCompletedDay
import com.mrb.remember.domain.interactor.UseCase
import com.mrb.remember.domain.model.Homework
import com.mrb.remember.domain.model.LeitnerDay
import com.mrb.remember.presentation.platform.BaseViewModel
import com.mrb.remember.testing.OpenForTesting
import javax.inject.Inject

@OpenForTesting
class LevelsViewModel @Inject constructor(
    private val getCompletedDay: GetCompletedDay,
    private val saveCompletedDay: SaveCompletedDay,
    private val getHomework: GetHomework
) : BaseViewModel() {

    var homework: MutableLiveData<Homework> = MutableLiveData()
    var completedDay: MutableLiveData<LeitnerDay> = MutableLiveData()

    fun init() {
        showLoading()
        getCompletedDay(UseCase.None()) {
            it.either(::handleFailure, ::handleLeitnerDay)
        }
    }

    fun setCompletedDay(day: LeitnerDay) {
        saveCompletedDay(SaveCompletedDay.Params(day)) {
            it.either(::handleFailure, ::handleCompletedDay)
        }
    }

    private fun handleCompletedDay(day: LeitnerDay) {
        this.completedDay.value = day
    }

    private fun handleLeitnerDay(leitnerDay: LeitnerDay) {
        getHomework(GetHomework.Params(leitnerDay.dayNumber + 1)) {
            it.either(::handleFailure, ::handleHomework)
        }
    }

    private fun handleHomework(homework: Homework) {
        hideLoading()
        this.homework.value = homework
    }
}