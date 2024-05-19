package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeButtonAndImage()
}

@Composable
fun LemonadeButtonAndImage(modifier: Modifier = Modifier.fillMaxSize()) {
    var result by remember { mutableStateOf(1) }
    var randomNumb = (1..3).random() // random 1
    var randomMax = randomNumb + 4 // max 7  1, 2/3/4/5 6/7
    val imageResource = when (result) {
        1 -> R.drawable.lemon_tree
//        2 -> R.drawable.lemon_squeeze
//        3 -> R.drawable.lemon_squeeze
        in 2..2 + randomNumb -> R.drawable.lemon_squeeze
        in 3 + randomNumb..randomMax -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    var imageResult by remember { mutableStateOf(1) }
    val textResource = when (imageResult) {
        1 -> R.string.Lemon_tree
//        2 -> R.string.Lemon
//        3 + randomNumb -> R.string.Lemon
        in 2..2 + randomNumb -> R.string.Lemon
        in 3 + randomNumb..randomMax -> R.string.Glass_of_lemonade
        else -> R.string.Empty_glass
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                result = if (result < randomMax + 1) result + 1 else 1
                imageResult = if (imageResult < randomMax + 1) imageResult + 1 else 1
            },
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan
            )
        ) {
            val image: Painter = painterResource(imageResource)
            Image(
                painter = image,
                contentDescription = result.toString()
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(textResource)
        )
        Text("Tap Count: ${(result-1).toString()}")
    }

}




