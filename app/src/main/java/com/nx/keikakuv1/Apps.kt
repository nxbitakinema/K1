package com.nx.keikakuv1

import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.nx.keikakuv1.data.CONSTANTs
import com.nx.keikakuv1.data.Daos
import com.nx.keikakuv1.data.Databases

@RequiresApi(Build.VERSION_CODES.O)
class Apps : Application() {

    private var db : Databases? = null

    init { instance = this }

    private fun getDb(): Databases {
        return if (db != null){
            db!!
        } else {
            db = Room.databaseBuilder(
                instance!!.applicationContext,
                Databases::class.java, CONSTANTs.DATABASE_NAME
            ).fallbackToDestructiveMigration()
                .build()
            db!!
        }
    }


    companion object {
        private var instance: Apps? = null

        fun getDao(): Daos {
            return instance!!.getDb().DAOs()
        }

        fun getUriPermission(uri: Uri){
            instance!!.applicationContext.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }

    }


}