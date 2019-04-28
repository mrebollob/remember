package com.mrb.remember.domain.extension

import android.animation.Animator
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.snackbar.Snackbar
import com.mrb.remember.R

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
  return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun View.visible() {
  this.visibility = View.VISIBLE
}

fun View.invisible() {
  this.visibility = View.INVISIBLE
}

fun View.gone() {
  this.visibility = View.GONE
}

fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
  val snack = Snackbar.make(this, message, length)
  snack.show()
}

fun ImageView.loadImage(url: String) {
  Glide.with(context)
    .load(url)
    .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.black_20a)))
    .diskCacheStrategy(DiskCacheStrategy.ALL)
    .fitCenter()
    .into(this)
}

fun LottieAnimationView.listen(animationFinished: () -> Unit) {
  removeAllAnimatorListeners()
  addAnimatorListener(object : Animator.AnimatorListener {
    override fun onAnimationRepeat(animation: Animator?) {
    }

    override fun onAnimationEnd(animation: Animator?) {
      animationFinished()
    }

    override fun onAnimationCancel(animation: Animator?) {
    }

    override fun onAnimationStart(animation: Animator?) {
    }
  })
}