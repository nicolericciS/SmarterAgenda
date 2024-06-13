package br.com.nicole.smarteragenda.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.nicole.smarteragenda.database.converters.Converters
import br.com.nicole.smarteragenda.model.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SmarterAgendaDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

}