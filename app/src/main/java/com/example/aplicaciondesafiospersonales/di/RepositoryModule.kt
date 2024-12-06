package com.example.aplicaciondesafiospersonales.di

import com.example.aplicaciondesafiospersonales.challenges.data.repository.ChallengesRepositoryImpl
import com.example.aplicaciondesafiospersonales.challenges.domain.repository.ChallengesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindChallengesRepository(impl: ChallengesRepositoryImpl): ChallengesRepository
}