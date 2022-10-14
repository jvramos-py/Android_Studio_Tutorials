package com.example.android_studio_tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_studio_tutorials.ui.theme.Android_Studio_TutorialsTheme
import com.example.android_studio_tutorials.ui.theme.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android_Studio_TutorialsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                }
            }
        }
    }
}

data class Message(val author: String, val body: String)

//we keep track if the message is expanded or not in this variable
var isExpanded by remember {
    mutableStateOf(false)
}

val test: Int = if (isExpanded) Int.MAX_VALUE else 1

@Composable
fun MessageCard(msg: Message) {
    //SurfaceColor will be updated gradually from one color to other
    val surfaceColor by animateColorAsState(
        targetValue = if (isExpanded) {
            Color.Gray
        } else {
            Color.Red
        }
    )

    //Add padding around the message
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.primaryVariant, CircleShape)
        )

        //Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        //We toggle the isExpanded variable when we click on this column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.width(8.dp))

            androidx.compose.material.Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                //SurfaceColor color will be change here:
                color = MaterialTheme.colors.primary,
                //animateContentSize
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(4.dp),
                    maxLines = test,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun conversation(message: List<Message>) {
    LazyColumn {
        items(message) {message ->
            MessageCard(msg = message)
        }
    }
}


