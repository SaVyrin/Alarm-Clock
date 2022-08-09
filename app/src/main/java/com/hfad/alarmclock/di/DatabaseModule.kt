package com.hfad.alarmclock.di

import android.content.Context
import androidx.room.Room
import com.hfad.alarmclock.data.database.AlarmDao
import com.hfad.alarmclock.data.database.AlarmsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AlarmsDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            AlarmsDatabase::class.java,
            "post_database"
        ).build()
    }

    @Provides
    fun provideUserDao(database: AlarmsDatabase): AlarmDao {
        return database.alarmDao
    }
}