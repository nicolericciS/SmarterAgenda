package br.com.nicole.smarteragenda.di.Module

import android.content.Context
import br.com.nicole.smarteragenda.Database.ContactDao
import br.com.nicole.smarteragenda.Database.SmarterAgendaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideContactDao(@ApplicationContext context: Context): ContactDao{
        return SmarterAgendaDatabase.getDatabase(context).contactDao()
    }
}