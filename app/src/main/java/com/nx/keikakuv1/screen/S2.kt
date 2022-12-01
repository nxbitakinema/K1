package com.nx.keikakuv1.screen

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.nx.keikakuv1.R
import com.nx.keikakuv1.component.Appbar
import com.nx.keikakuv1.data.AppViewModel
import com.nx.keikakuv1.data.CONSTANTs.nxrDetailPlaceHolder
import com.nx.keikakuv1.data.CONSTANTs.nxrEditNavigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/// S C R E E N - 02 /////////////////////////////////////// d e t a i l ///

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun S2 ( nxrId: Int,  navController: NavController , viewModelx: AppViewModel) {

    val scope = rememberCoroutineScope()

    val screen2 = remember { mutableStateOf(nxrDetailPlaceHolder) }

    LaunchedEffect(true) {
        scope.launch(Dispatchers.IO) {
            screen2.value = viewModelx.getAppViewModel(nxrId) ?: nxrDetailPlaceHolder
        }
    }

    Surface( modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background ) {
        Scaffold(
            topBar = {
                Appbar(
                    title = screen2.value.title,
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.icon_edit_cut),
                            contentDescription = "edit" )   },
                    onIconClick = {
                        navController.navigate(nxrEditNavigation(screen2.value.id ?: 0) )  },
                    iconState = remember { mutableStateOf(true) }
                )
            },

            ) {
            Column(modifier = Modifier.fillMaxSize()) {
                if ( screen2.value.imageUri != null && screen2.value.imageUri!!.isNotEmpty() ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            ImageRequest
                                .Builder(LocalContext.current)
                                .data(data = Uri.parse(screen2.value.imageUri))
                                .build()  ),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(250.dp)
                            .fillMaxWidth()   )    }

                Text(
                    text = screen2.value.dateUpdated,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 6.dp, end = 8.dp)
                )

                Text(
                    text = screen2.value.text,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    lineHeight = 1.7.em,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .verticalScroll(rememberScrollState())
                )
            }
        }
    }
}




