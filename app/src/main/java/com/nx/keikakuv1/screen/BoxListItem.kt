@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.nx.nxr

import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.nx.keikakuv1.data.CONSTANTs.nxrDetailNavigation
import com.nx.keikakuv1.data.Nx

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BoxListItem(
    note: Nx,
    openDialog: MutableState<Boolean>,
    deleteText: MutableState<String>,
    navController: NavController,
    noteBackGround: Color,
    notesToDelete: MutableState<List<Nx>>
) {

    return Box(modifier = Modifier.height(80.dp)) {

        Column(
            modifier = Modifier
                .background(noteBackGround)
                .fillMaxWidth()
                .combinedClickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),    // CLICK EFFECT
                    onClick = {
                        if (note.id != 0) {
                            navController.navigate(nxrDetailNavigation(note.id ?: 0))
                        }
                    },
                    onLongClick = {
                        if (note.id != 0) {
                            openDialog.value = true
                            deleteText.value = "Are you sure you want to delete this note ?"
                            notesToDelete.value = mutableListOf(note)
                        }
                    })
                .fillMaxSize()


        ) {

            Row {

                if (note.imageUri != null && note.imageUri.isNotEmpty()){

                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest
                                .Builder(LocalContext.current)
                                .data(data = Uri.parse(note.imageUri))
                                .build()  ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(top = 2.dp, end = 8.dp)
                            .clip(CutCornerShape(2.dp))
                            .height(80.dp)
                            .width(80.dp)
                    )
                }


                Column {

                    // STORY TITLE
                    Text(
                        text = note.title,
                        color = Color.LightGray,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 2.dp, end = 8.dp, bottom = 6.dp),
                        fontSize = 12.sp
                    )


                    // STORY
                    Text(
                        text = note.text,
                        color = Color.Gray,
                        maxLines = 3,
                        modifier = Modifier.padding(end = 8.dp),
                        fontSize = 12.sp,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.subtitle2,
                        lineHeight = 1.5.em


                    )

                }
            }

        }
    }
}