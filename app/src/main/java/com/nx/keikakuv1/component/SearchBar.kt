package com.nx.keikakuv1.component
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.nx.keikakuv1.R

@Composable
fun SBar(query: MutableState<String>) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp)
        ,
        value = query.value,
        onValueChange = { query.value = it },
        placeholder = { Text("search") },
        maxLines = 1,
        singleLine = true,
        trailingIcon = {
            AnimatedVisibility(
                visible = query.value.isNotEmpty(),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                IconButton( onClick = { query.value = "" } ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.icon_close),
                        contentDescription = "clear search" )
                }
            }
        }
    )
}