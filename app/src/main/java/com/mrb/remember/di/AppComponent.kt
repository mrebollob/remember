package com.mrb.remember.di

import android.app.Application
import com.mrb.remember.RememberApp
import com.mrb.remember.di.builder.ActivityBuilder
import com.mrb.remember.di.module.ApiModule
import com.mrb.remember.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ApiModule::class,
        ActivityBuilder::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: RememberApp)
}
