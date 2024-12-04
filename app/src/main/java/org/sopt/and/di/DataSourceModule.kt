package org.sopt.and.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.dataremote.datasource.HobbyRemoteDataSource
import org.sopt.and.data.dataremote.datasource.LoginRemoteDataSource
import org.sopt.and.data.dataremote.datasource.SignUpRemoteDataSource
import org.sopt.and.data.dataremote.datasourceimpl.HobbyRemoteDataSourceImpl
import org.sopt.and.data.dataremote.datasourceimpl.LoginRemoteDataSourceImpl
import org.sopt.and.data.dataremote.datasourceimpl.SignUpRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsLoginRemoteDataSource(loginDataSourceImpl: LoginRemoteDataSourceImpl): LoginRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsSignUpRemoteDataSource(signUpDataSourceImpl: SignUpRemoteDataSourceImpl): SignUpRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsHobbyRemoteDataSource(hobbyRemoteDataSourceImpl: HobbyRemoteDataSourceImpl): HobbyRemoteDataSource
}