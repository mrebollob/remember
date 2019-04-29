package com.mrb.remember.presentation.main.profile

import android.os.Bundle
import android.view.View
import com.mrb.remember.R
import com.mrb.remember.domain.exception.Failure
import com.mrb.remember.domain.extension.failure
import com.mrb.remember.domain.extension.gone
import com.mrb.remember.domain.extension.loadImage
import com.mrb.remember.domain.extension.observe
import com.mrb.remember.domain.extension.toast
import com.mrb.remember.domain.extension.viewModel
import com.mrb.remember.domain.extension.visible
import com.mrb.remember.domain.model.User
import com.mrb.remember.presentation.about.AboutActivity
import com.mrb.remember.presentation.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {

    lateinit var profileViewModel: ProfileViewModel

    override fun layoutId(): Int = R.layout.fragment_profile
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.title_profile)

        profileViewModel = viewModel(viewModelFactory) {
            observe(userData, ::handleUser)
            observe(loading, ::handleLoading)
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

    private fun handleLoading(showLoading: Boolean?) {
        if (showLoading == true) {
            shimmerView.startShimmerAnimation()
            shimmerView.visible()
            profileView.gone()
        } else {
            shimmerView.stopShimmerAnimation()
            shimmerView.gone()
            profileView.visible()
        }
    }

    override fun onPause() {
        super.onPause()
        shimmerView.stopShimmerAnimation()
    }

    companion object {

        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
