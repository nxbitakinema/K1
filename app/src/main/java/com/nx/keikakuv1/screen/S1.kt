@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.nx.keikakuv1.screen

import android.annotation.SuppressLint
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nx.keikakuv1.*
import com.nx.keikakuv1.R
import com.nx.keikakuv1.component.Appbar
import com.nx.keikakuv1.component.ButtonFAB
import com.nx.keikakuv1.component.SBar
import com.nx.keikakuv1.data.AppViewModel
import com.nx.keikakuv1.data.CONSTANTs.NAVIGATION_SCREEN_S4
import com.nx.keikakuv1.data.CONSTANTs.orPlaceHolderList
import com.nx.keikakuv1.data.Nx

/// S C R E E N - 01 ////////////////////////////////////////////// l i s t ///


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun S1 ( navController: NavController , viewModelx: AppViewModel) {

    val openDialog = remember { mutableStateOf(false) }
    val deleteText = remember { mutableStateOf("") }
    val notesQuery = remember { mutableStateOf("") }
    val notesToDelete = remember { mutableStateOf(listOf<Nx>()) }
    val nxrs = viewModelx.nxrs.observeAsState()
    val context = LocalContext.current

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                Appbar(
                    title = "KEIKAKU v.1",
                    
                    onIconClick = {
                        if (nxrs.value?.isNotEmpty() == true) {
                            openDialog.value = true
                            deleteText.value = "Are you sure want to delete all notes ?"
                            notesToDelete.value = nxrs.value ?: emptyList()
                        } else {
                            Toast.makeText( context,
                                "No Notes found.", Toast.LENGTH_SHORT ).show()
                        }
                    },

                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.icon_delete),
                            contentDescription = "delete",
                            tint = MaterialTheme.colors.background
                        )
                    },

                    iconState = remember { mutableStateOf(true) }


                )
            },

            floatingActionButton = {
                ButtonFAB(
                    icon = R.drawable.icon_add,
                    contentDescription = "add",
                    action = { navController.navigate(NAVIGATION_SCREEN_S4) },
                    )
            }
        ) {
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                SBar(query = notesQuery)
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                BoxList(
                    nxrs = nxrs.value.orPlaceHolderList(),
                    openDialog = openDialog,
                    query = notesQuery,
                    deleteText = deleteText,
                    navController = navController ,
                    notesToDelete = notesToDelete
                )
            }

            DeleteDialog(
                openDialog = openDialog,
                text = deleteText,
                notesToDelete = notesToDelete,
                action = {
                    notesToDelete.value.forEach {
                        viewModelx.deleteAppViewModel(it)
                    }
                }
            )
        }
    }
}


@Composable
fun DeleteDialog(
    openDialog: MutableState<Boolean>,
    text: MutableState<String>,
    action: () -> Unit,
    notesToDelete: MutableState<List<Nx>>
) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Delete Note")
            },
            text = {
                Column {
                    Text(text.value)
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column {
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Black,
                                contentColor = Color.White
                            ),
                            onClick = {
                                action.invoke()
                                openDialog.value = false
                                notesToDelete.value = mutableListOf()
                            }
                        ) {
                            Text("Yes")
                        }
                        Spacer(modifier = Modifier.padding(12.dp))
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Black,
                                contentColor = Color.White
                            ),
                            onClick = {
                                openDialog.value = false
                                notesToDelete.value = mutableListOf()
                            }
                        ) {
                            Text("No")
                        }
                    }
                }
            }
        )
    }
}



