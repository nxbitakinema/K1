package com.nx.keikakuv1.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nx.keikakuv1.data.CONSTANTs.TABLE_NAME
import com.nx.keikakuv1.utils.getDateCreated

@RequiresApi(Build.VERSION_CODES.O)
@Entity(
    tableName = TABLE_NAME ,
    indices = [ Index ( value = ["id"] , unique = true ) ] )
data class Nx constructor(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    @ColumnInfo(name = "textENTITYs")
    val text: String,

    @ColumnInfo(name = "titleENTITYs")
    val title: String,

    @ColumnInfo(name = "dateUpdatedENTITYs")
    val dateUpdated: String = getDateCreated(),

    @ColumnInfo(name = "imageUriENTITYs")
    val imageUri: String? = null

)
