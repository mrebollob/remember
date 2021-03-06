package com.mrb.remember.presentation.levels

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.mrb.remember.R
import com.mrb.remember.domain.exception.Failure
import com.mrb.remember.domain.extension.failure
import com.mrb.remember.domain.extension.gone
import com.mrb.remember.domain.extension.observe
import com.mrb.remember.domain.extension.toast
import com.mrb.remember.domain.extension.viewModel
import com.mrb.remember.domain.extension.visible
import com.mrb.remember.domain.model.Homework
import com.mrb.remember.domain.model.LeitnerDay
import com.mrb.remember.presentation.levels.adapter.LevelsAdapter
import com.mrb.remember.presentation.platform.BaseActivity
import kotlinx.android.synthetic.main.activity_levels.*
import java.util.Date

class LevelsActivity : BaseActivity() {

    private lateinit var levelsViewModel: LevelsViewModel
    private val levelsAdapter = LevelsAdapter()
    private var currentDay = -1

    override fun layoutId() = R.layout.activity_levels
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        levelsViewModel = viewModel(viewModelFactory) {
            observe(homework, ::handleHomework)
            observe(completedDay, ::handleCompletedDay)
            observe(loading, ::handleLoading)
            failure(failure, ::handleError)
        }

        levelsListView.adapter = levelsAdapter

        doneButton.setOnClickListener {
            levelsViewModel.setCompletedDay(LeitnerDay(currentDay, Date()))
        }
        levelsViewModel.init()

        levelsLoading.visible()
    }

    private fun handleHomework(homework: Homework?) {
        homework ?: return

        currentDay = homework.day
        levelsAdapter.levels = homework.levels

        val names = homework.levels.map { it.name }
            .reduce { acc, string -> "$acc, $string" }
        levelsTextView.text = getString(R.string.main_view_levels_to_review, names)
        dayTextView.text = getString(R.string.main_view_day_number, homework.day)
    }

    private fun handleCompletedDay(day: LeitnerDay?) {
        finish()
    }

    private fun handleLoading(showLoading: Boolean?) {
        if (showLoading == true) {
            levelsLoading.visible()
            levelsContent.gone()
            doneButton.gone()
        } else {
            levelsLoading.gone()
            levelsContent.visible()
            doneButton.visible()
        }
    }

    private fun handleError(failure: Failure?) {
        toast(getString(R.string.generic_error))
    }

    companion object Navigator {
        fun open(context: Context) {
            val intent = Intent(context, LevelsActivity::class.java)
            context.startActivity(intent)
        }
    }
}