package com.nx.keikakuv1.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.RoomDatabase

@RequiresApi(Build.VERSION_CODES.O)
@Database(entities = [ Nx::class], version = 1)
abstract class Databases : RoomDatabase() {  abstract fun DAOs(): Daos }