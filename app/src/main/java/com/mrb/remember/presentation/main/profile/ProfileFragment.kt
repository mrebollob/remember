package com.mrb.remember.presentation.main.profile

import android.os.Bundle
import android.view.View
import com.mrb.remember.R
import com.mrb.remember.domain.exception.Failure
import com.mrb.remember.domain.extension.failure
import com.mrb.remember.domain.extension.loadImage
import com.mrb.remember.domain.extension.observe
import com.mrb.remember.domain.extension.toast
import com.mrb.remember.domain.extension.viewModel
import com.mrb.remember.domain.model.User
import com.mrb.remember.presentation.about.AboutActivity
import com.mrb.remember.presentation.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.aboutButton
import kotlinx.android.synthetic.main.fragment_profile.emailTextView
import kotlinx.android.synthetic.main.fragment_profile.nameTextView
import kotlinx.android.synthetic.main.fragment_profile.userImageView

class ProfileFragment : BaseFragment() {

  lateinit var profileViewModel: ProfileViewModel

  override fun layoutId(): Int = R.layout.fragment_profile
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity?.setTitle(R.string.title_profile)

    profileViewModel = viewModel(viewModelFactory) {
      observe(userData, ::handleUser)
      failure(failure, ::handleError)
    }

    profileViewModel.init()
    initViews()
  }

  private fun initViews() {
    aboutButton.setOnClickListener { AboutActivity.open(requireContext()) }
  }

  private fun handleUser(user: User?) {
    user ?: return

    nameTextView.text = user.getFullName()
    emailTextView.text = user.email
    userImageView.loadImage(user.picture)
  }

  private fun handleError(failure: Failure?) {
    context?.toast(getString(R.string.generic_error))
  }

  companion object {

    @JvmStatic
    fun newInstance() = ProfileFragment()
  }
}
