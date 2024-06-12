package br.com.nicole.smarteragenda.di.Module

import android.content.Context
import androidx.room.Room
import br.com.nicole.smarteragenda.Database.ContactDao
import br.com.nicole.smarteragenda.Database.SmarterAgendaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideContactDao(db: SmarterAgendaDatabase): ContactDao{
        return db.contactDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): SmarterAgendaDatabase {
        return Room.databaseBuilder(
            context,
            SmarterAgendaDatabase::class.java,
            name = "smarterAgenda.db"
        ).build()
    }
}