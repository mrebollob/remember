package com.mrb.remember.domain.exception

sealed class Failure {
    object NetworkConnection : Failure()
    object ServerError : Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : Failure()

    object EmptyData : FeatureFailure()
}
