package com.nx.keikakuv1.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nx.keikakuv1.data.Nx
import com.nx.keikakuv1.ui.Color1001
import com.nx.keikakuv1.ui.Color1002
import com.nx.keikakuv1.utils.getDay
import com.nx.nxr.BoxListItem

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BoxList(
    nxrs: List<Nx>,
    openDialog: MutableState<Boolean>,
    query: MutableState<String>,
    deleteText: MutableState<String>,
    navController: NavController,
    notesToDelete: MutableState<List<Nx>>,
) {
    var previousHeader = ""

    LazyColumn {

        val queriedNotes = if (query.value.isEmpty() ) { nxrs } else {
            nxrs.filter { it.text.contains(query.value) || it.title.contains(query.value) }  }

        itemsIndexed(queriedNotes) {

                index, nxrs -> if ( nxrs.getDay() != previousHeader )

        {

            Column(modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)) {

                Text(text = nxrs.getDay(), color = Color.Gray, fontSize = 10.sp)

            }

            previousHeader =  nxrs.getDay()
            


        }

            BoxListItem(
                nxrs,
                openDialog,
                deleteText = deleteText ,
                navController,
                notesToDelete = notesToDelete,
                noteBackGround = if (index % 2 == 0) { Color1001 } else Color1002 ) // * day note 2 color


            Divider(
                modifier = Modifier.padding(top = 14.dp, bottom = 12.dp, end = 8.dp),
                color = MaterialTheme.colors.onError,
                thickness = 1.dp

            )


        }
    }
}
