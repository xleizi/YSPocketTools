package com.lei123.YSPocketTools.ui.plugin

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun LoadingView(modifier: Modifier = Modifier, onDismissRequest: () -> Unit = {}) {
    Dialog(onDismissRequest = onDismissRequest) {
        val infiniteTransition = rememberInfiniteTransition()
        val alpha by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = 1000
                    1f at 500
                },
                repeatMode = RepeatMode.Reverse
            )
        )
        val index by infiniteTransition.animateValue(
            initialValue = 0,
            targetValue = Element.all.size,
            typeConverter = Int.VectorConverter,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = Element.all.size * 1000
                }
            )
        )
        Log.i("amin", "$index")
        val image = index % Element.all.size

        Image(
            modifier = Modifier.size(40.dp).alpha(alpha),
            painter = painterResource(id = Element.all[image].icon),
            contentDescription = ""
        )
    }
}


