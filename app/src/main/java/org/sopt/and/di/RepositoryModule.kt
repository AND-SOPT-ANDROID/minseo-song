package org.sopt.and.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.repositoryimpl.HobbyRepositoryImpl
import org.sopt.and.data.repositoryimpl.LoginRepositoryImpl
import org.sopt.and.data.repositoryimpl.SignUpRepositoryImpl
import org.sopt.and.domain.repository.HobbyRepository
import org.sopt.and.domain.repository.LoginRepository
import org.sopt.and.domain.repository.SignUpRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    abstract fun bindSignUpRepository(signUpRepositoryImpl: SignUpRepositoryImpl): SignUpRepository

    @Binds
    @Singleton
    abstract fun bindsHobbyRepository(hobbyRepositoryImpl: HobbyRepositoryImpl): HobbyRepository
}