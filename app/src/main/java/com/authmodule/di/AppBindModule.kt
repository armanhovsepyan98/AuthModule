package com.authmodule.di

import com.authmodule.data.AuthRepositoryImpl
import com.authmodule.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppBindModule {
    @Binds
    fun bindPostAuthRepo(postAuthRepositoryImpl: AuthRepositoryImpl): AuthRepository
}