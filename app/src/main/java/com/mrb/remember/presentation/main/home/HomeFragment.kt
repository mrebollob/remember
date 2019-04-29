package com.mrb.remember.presentation.main.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.core.content.ContextCompat
import com.mrb.remember.R
import com.mrb.remember.domain.exception.Failure
import com.mrb.remember.domain.extension.failure
import com.mrb.remember.domain.extension.getStringHour
import com.mrb.remember.domain.extension.gone
import com.mrb.remember.domain.extension.observe
import com.mrb.remember.domain.extension.snack
import com.mrb.remember.domain.extension.toast
import com.mrb.remember.domain.extension.viewModel
import com.mrb.remember.domain.extension.visible
import com.mrb.remember.presentation.levels.LevelsActivity
import com.mrb.remember.presentation.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.Date

class HomeFragment : BaseFragment() {

    lateinit var homeViewModel: HomeViewModel
    override fun layoutId(): Int = R.layout.fragment_home
    private var timer: CountDownTimer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.setTitle(R.string.app_name)
        countdownView.gone()
        firstDayView.gone()

        homeViewModel = viewModel(viewModelFactory) {
            observe(studyTime, ::handleStudyTime)
            failure(failure, ::handleError)
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.init()
    }

    private fun handleStudyTime(studyTime: Date?) {
        studyTime ?: return

        countdownView.visible()
        firstDayView.gone()
        setLeitnerButtonEnabled()

        studyTimeTextView.text =
            getString(
                R.string.countdown_view_study_time,
                studyTime.getStringHour()
            )

        val remainingTime = studyTime.time - System.currentTimeMillis()
        timer = object : CountDownTimer(remainingTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                updateCountDownView(millisUntilFinished)
            }

            override fun onFinish() {
                updateCountDownView(0)
            }
        }.start()
    }

    private fun updateCountDownView(millisUntilFinished: Long) {

        if (millisUntilFinished > 0) {

            val seconds = millisUntilFinished / 1000
            val hours = seconds / 3600
            val minutes = ((seconds - hours * 3600) / 60) + 1
            updateCircleProgressView(seconds.toInt())

            if (hours < STUDY_HOUR_THRESHOLD) {
                setLeitnerButtonEnabled()
            } else {
                setLeitnerButtonDisabled()
            }

            val res = resources
            hoursTextView?.apply {
                text = res.getQuantityString(
                    R.plurals.hour_format,
                    hours.toInt(), hours
                )
            }

            minutesTextView?.apply {
                visible()
                text = res.getQuantityString(
                    R.plurals.minute_format,
                    minutes.toInt(), minutes
                )
            }
        } else {
            updateCircleProgressView(0)
            hoursTextView?.apply {
                text = getString(R.string.countdown_view_completed_time)
            }

            minutesTextView?.apply {
                gone()
            }
        }
    }

    private fun showFirstDay() {
        countdownView.gone()
        firstDayView.visible()
        setLeitnerButtonEnabled()
    }

    private fun setLeitnerButtonEnabled() {
        context?.let {
            showLeitnerButton.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    it,
                    R.color.show_leitner_button_color_enabled
                )
            )
        }

        showLeitnerButton.setOnClickListener {
            LevelsActivity.open(it.context)
        }
    }

    private fun setLeitnerButtonDisabled() {
        context?.let {
            showLeitnerButton.backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    it,
                    R.color.show_leitner_button_color_disabled
                )
            )
        }

        showLeitnerButton.setOnClickListener {
            it.snack(
                getString(
                    R.string.countdown_view_advance_time_error,
                    STUDY_HOUR_THRESHOLD
                )
            )
        }
    }

    private fun updateCircleProgressView(secondsUntilFinished: Int) {
        val value = (86400 - secondsUntilFinished) / 24
        circleProgressView?.apply {
            currentValue = value
        }
    }

    private fun handleError(failure: Failure?) {
        when (failure) {
            Failure.EmptyData -> showFirstDay()
            else -> context?.toast(getString(R.string.generic_error))
        }
    }

    override fun onPause() {
        super.onPause()
        timer?.cancel()
    }

    companion object {

        private const val STUDY_HOUR_THRESHOLD = 6

        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}
