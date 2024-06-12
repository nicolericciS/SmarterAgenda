package br.com.nicole.smarteragenda.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.nicole.smarteragenda.Model.Contact

@Dao
interface ContactDao {

    @Insert
    suspend fun insert(contact: Contact)

    @Query("SELECT * FROM Contact")
    suspend fun searchAll(): List<Contact>



}