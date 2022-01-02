package com.esq.foodsearch.common.data.di

import com.esq.foodsearch.common.data.cache.preferences.PetSavePreferences
import com.esq.foodsearch.common.data.cache.preferences.Preferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

  @Binds
  abstract fun providePreferences(preferences: PetSavePreferences): Preferences
}