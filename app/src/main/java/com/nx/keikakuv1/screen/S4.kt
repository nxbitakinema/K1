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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

////// S C R E E N - 04 //////////////////////////////// c r e a t e ///


@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun S4 ( navController: NavController , viewModelx: AppViewModel) {

    val screen4Text = remember { mutableStateOf("") }
    val screen4Title = remember { mutableStateOf("") }
    val screen4Photo = remember { mutableStateOf("") }

    val screen4saveButtonState = remember { mutableStateOf(false) }

    val getImageRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) {
        if (it != null) { Apps.getUriPermission(it) }
        screen4Photo.value = it.toString()
    }

    Surface( modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.primary ) {
        Scaffold(
            topBar = {
                Appbar(
                    title = "create note",
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.icon_save),
                            contentDescription = "save",
                        )
                    },
                    onIconClick = {
                        viewModelx.createAppViewModel(
                            screen4Title.value,
                            screen4Text.value,
                            screen4Photo.value
                        )
                        navController.popBackStack()
                    },
                    iconState = screen4saveButtonState
                )
            },

            floatingActionButton = {
                ButtonFAB(
                    icon = R.drawable.icon_camera,
                    contentDescription = "add photo",
                    action = { getImageRequest.launch(arrayOf("image/*")) },
                )
            },


            content = {

                Column( Modifier.fillMaxSize().padding(8.dp) ) {

                    if ( screen4Photo.value.isNotEmpty() ) {

                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest
                                    .Builder(LocalContext.current)
                                    .data(data = Uri.parse(screen4Photo.value))
                                    .build()  ),
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

                        value = screen4Title.value,

                        onValueChange = { value ->
                            screen4Title.value = value
                            screen4saveButtonState.value =
                                screen4Title.value != "" && screen4Text.value != ""
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
                        modifier = Modifier.fillMaxWidth().fillMaxHeight(1f),
                        value = screen4Text.value,
                        onValueChange = { value ->
                            screen4Text.value = value
                            screen4saveButtonState.value =
                                screen4Title.value != "" && screen4Text.value != ""
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