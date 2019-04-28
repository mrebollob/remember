package com.mrb.remember.presentation.main.journal

import android.os.Bundle
import android.view.View
import com.mrb.remember.R
import com.mrb.remember.domain.exception.Failure
import com.mrb.remember.domain.extension.toast
import com.mrb.remember.presentation.platform.BaseFragment

class JournalFragment : BaseFragment() {

  override fun layoutId(): Int = R.layout.fragment_journal
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity?.setTitle(R.string.title_journal)
  }

  private fun handleError(failure: Failure?) {
    context?.toast(getString(R.string.generic_error))
  }

  companion object {

    @JvmStatic
    fun newInstance() = JournalFragment()
  }
}
