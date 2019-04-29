package com.mrb.remember.domain.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mrb.remember.R
import com.mrb.remember.presentation.platform.BaseActivity
import com.mrb.remember.presentation.platform.BaseFragment

inline fun <reified T : ViewModel> BaseActivity.viewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit
): T {
    val vm = ViewModelProviders.of(this, factory)[T::class.java]
    vm.body()
    return vm
}

fun BaseActivity.navigate(baseFragment: BaseFragment) {
    supportFragmentManager.beginTransaction()
        .replace(R.id.container, baseFragment)
        .commit()
}