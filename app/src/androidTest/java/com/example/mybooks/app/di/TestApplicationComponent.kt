package com.example.mybooks.app.di

import com.example.mybooks.app.di.TestApplicationModule
import com.example.mybooks.app.di.component.ApplicationComponent
import com.example.mybooks.app.di.module.DomainModule
import com.example.mybooks.app.di.module.RepositoryModule
import dagger.Component
import com.example.mybooks.app.di.module.TestDataModule
import com.example.mybooks.app.di.module.TestUtilsModule
import com.example.mybooks.app.di.viewmodel.ViewModelFactoryModule
import com.example.mybooks.app.di.viewmodel.ViewModelModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
    TestApplicationModule::class,
        TestUtilsModule::class,
        DomainModule::class,
        TestDataModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class)
)
interface TestApplicationComponent: ApplicationComponent {
}