package com.mrb.remember.domain.model

data class User(
  val firstName: String,
  val lastName: String,
  val email: String,
  val picture: String
) {
  fun getFullName() = "$firstName $lastName"
}