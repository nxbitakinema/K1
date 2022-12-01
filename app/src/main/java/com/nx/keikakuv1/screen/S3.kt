package com.nx.keikakuv1.screen

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.nx.keikakuv1.Apps
import com.nx.keikakuv1.R
import com.nx.keikakuv1.component.Appbar
import com.nx.keikakuv1.component.ButtonFAB
import com.nx.keikakuv1.data.AppViewModel
import com.nx.keikakuv1.data.CONSTANTs.nxrDetailPlaceHolder
import com.nx.keikakuv1.data.Nx
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/// S C R E E N - 03 ////////////////////////////////////////////// e d i t ///

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun S3 ( nxrId: Int,  navController: NavController , viewModelx: AppViewModel) {

    val scope = rememberCoroutineScope()
    val screen3 = remember { mutableStateOf(nxrDetailPlaceHolder) }

    val screen3Text = remember { mutableStateOf(screen3.value.text) }
    val screen3Title = remember { mutableStateOf(screen3.value.title) }
    val screen3Photo = remember { mutableStateOf(screen3.value.imageUri) }
    val screen3saveButtonState = remember { mutableStateOf(false) }

    val getImageRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->

        if (uri != null) {
            Apps.getUriPermission(uri)
        }
        screen3Photo.value = uri.toString()
        if (screen3Photo.value != screen3.value.imageUri) {
            screen3saveButtonState.value = true
        }
    }

    LaunchedEffect(true) {
        scope.launch(Dispatchers.IO) {
            screen3.value = viewModelx.getAppViewModel(nxrId) ?: nxrDetailPlaceHolder
            screen3Text.value = screen3.value.text
            screen3Title.value = screen3.value.title
            screen3Photo.value = screen3.value.imageUri
        }
    }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.primary) {
        Scaffold(
            topBar = {
                Appbar(

                    title = "edit text",

                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.icon_save),
                            contentDescription = "save")   },

                    onIconClick = {
                        viewModelx.updateAppViewModel(
                            Nx(
                                id = screen3.value.id,
                                text = screen3Text.value,
                                title = screen3Title.value,
                                imageUri = screen3Photo.value
                            )
                        )
                        navController.popBackStack()
                    },

                    iconState = screen3saveButtonState
                )
            },

            floatingActionButton = {
                ButtonFAB(
                    contentDescription = "add photo",
                    icon = R.drawable.icon_camera,
                    action = { getImageRequest.launch(arrayOf("image/*")) },
                )
            },

            content = {

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(8.dp) ) {

                    if ( screen3Photo.value != null && screen3Photo.value!!.isNotEmpty()) {

                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest
                                    .Builder(LocalContext.current)
                                    .data(data = Uri.parse(screen3Photo.value))
                                    .build()
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.padding(4.dp))

                    OutlinedTextField(

                        modifier = Modifier.fillMaxWidth(),

                        value = screen3Title.value,

                        onValueChange = { value ->
                            screen3Title.value = value
                            if (screen3Title.value != screen3.value.title) {
                                screen3saveButtonState.value = true
                            } else if (screen3Text.value == screen3.value.text &&
                                screen3Title.value == screen3.value.title
                            ) {
                                screen3saveButtonState.value = false
                            }
                        },
                        label = { Text(text = "title") },

                        maxLines = 1,

                        singleLine = true,

                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = MaterialTheme.colors.onPrimary,
                            focusedLabelColor = MaterialTheme.colors.onPrimary,
                        ),
                    )
                    Spacer(modifier = Modifier.padding(4.dp))

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1.0f),
                        value = screen3Text.value,
                        onValueChange = { value ->
                            screen3Text.value = value
                            if (screen3Text.value != screen3.value.text) {
                                screen3saveButtonState.value = true
                            } else if (screen3Text.value == screen3.value.text &&
                                screen3Title.value == screen3.value.title
                            ) {
                                screen3saveButtonState.value = false
                            }
                        },
                        label = { Text(text = "story") },
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = MaterialTheme.colors.onPrimary,
                            focusedLabelColor = MaterialTheme.colors.onPrimary,
                        ),
                    )
                }
            }

        )
    }

}
