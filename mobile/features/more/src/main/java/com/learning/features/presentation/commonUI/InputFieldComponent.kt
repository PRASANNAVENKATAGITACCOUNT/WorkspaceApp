package com.learning.features.presentation.commonUI

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.android.awaitFrame

@Composable
fun InputFieldComponent(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
    label: String = "Label",
    textData: String = "Hello World",
    onValueChange: (String) -> Unit,
    placeHolderText: String = "",
    isMultiline: Boolean = false,
    fontSize: Int = 20,
    showCursor: Boolean=false
) {
    val height = if (isMultiline) 205.dp else 75.dp
    val fontSize = fontSize.sp
    modifier.height(height)
    val focusRequester = remember { FocusRequester() }
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        awaitFrame()
        focusRequester.requestFocus()
    }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                focusRequester.requestFocus()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }



    TextField(
        value = textData,
        onValueChange = { onValueChange.invoke(it) },
        modifier = if(showCursor) modifier.focusRequester(focusRequester) else modifier,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = placeHolderText,
                style = TextStyle(
                    color = Color.Gray,
                    fontSize=fontSize
                )
            )
        },
        maxLines = if (isMultiline) 5 else 1,
        textStyle = TextStyle(
            fontSize = fontSize
        ),
    )

}

@Preview(showBackground = true)
@Composable
fun InputFieldComponentPreview() {
    InputFieldComponent(
        onValueChange = {},
        label = "",
        isMultiline = false,
        textData = "Hello Text"
    )
}
