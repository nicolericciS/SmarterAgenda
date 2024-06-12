package br.com.nicole.smarteragenda.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.nicole.smarteragenda.Database.Converters.Converters
import br.com.nicole.smarteragenda.Model.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SmarterAgendaDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

}