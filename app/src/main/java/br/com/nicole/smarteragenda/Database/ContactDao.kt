package br.com.nicole.smarteragenda.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import br.com.nicole.smarteragenda.Model.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(contact: Contact)

    @Query("SELECT * FROM Contact")
    fun searchAll(): Flow<List<Contact>>

    @Query("SELECT * FROM Contact WHERE id= :id")
    fun searchForId(id: Long): Flow<Contact>?
    @Query("DELETE FROM Contact WHERE id = :id")
    suspend fun delete(id: Long)



}