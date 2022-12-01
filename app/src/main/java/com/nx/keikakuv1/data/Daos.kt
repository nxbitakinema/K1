package com.nx.keikakuv1.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface Daos {

    @Query("SELECT * FROM NXRtable WHERE nxrtable.id=:id")  // **  อาจจเผิดตนง WHERE
    suspend fun getDAOsById(id: Int) : Nx?

    @Query("SELECT * FROM NXRtable ORDER BY dateUpdatedENTITYs DESC")
    fun getDAOs() : LiveData<List<Nx>>

    @Delete
    fun deleteDAOs(nxr: Nx) : Int

    @Update
    fun updateDAOs(nxr: Nx) : Int

    @Insert
    fun insertDAOs(note: Nx)

}