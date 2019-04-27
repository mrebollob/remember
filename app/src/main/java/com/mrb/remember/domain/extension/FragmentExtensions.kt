package com.mrb.remember.domain.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrb.remember.presentation.platform.BaseFragment

/*
    From https://medium.com/thoughts-overflow/how-to-add-a-fragment-in-kotlin-way-73203c5a450b
 */

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
  beginTransaction().func().commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
  supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
  supportFragmentManager.inTransaction { replace(frameId, fragment) }
}

inline fun <reified T : ViewModel> BaseFragment.viewModel(
  factory: ViewModelProvider.Factory,
  body: T.() -> Unit
): T {
  val vm = androidx.lifecycle.ViewModelProviders.of(this, factory)[T::class.java]
  vm.body()
  return vm
}