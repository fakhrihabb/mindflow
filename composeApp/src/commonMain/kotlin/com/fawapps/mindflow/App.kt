package com.fawapps.mindflow
 
 import androidx.compose.animation.AnimatedVisibility
 import androidx.compose.foundation.Image
 import androidx.compose.foundation.background
 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.foundation.layout.fillMaxWidth
 import androidx.compose.foundation.layout.safeContentPadding
 import androidx.compose.material3.Button
 import androidx.compose.material3.MaterialTheme
 import androidx.compose.material3.Text
 import androidx.compose.runtime.*
 import androidx.compose.ui.Alignment
 import androidx.compose.ui.Modifier
 import org.jetbrains.compose.resources.painterResource
 import org.jetbrains.compose.ui.tooling.preview.Preview
 
 import mindflow.composeapp.generated.resources.Res
 import mindflow.composeapp.generated.resources.compose_multiplatform
 import com.fawapps.mindflow.theme.MindFlowTheme
 import com.fawapps.mindflow.components.Milo
 import com.fawapps.mindflow.domain.models.MiloEmotion
 
 @Composable
 @Preview
 fun App() {
     MindFlowTheme {
         var showContent by remember { mutableStateOf(false) }
         Column(
             modifier = Modifier
                 .background(MaterialTheme.colorScheme.background)
                 .safeContentPadding()
                 .fillMaxSize(),
             horizontalAlignment = Alignment.CenterHorizontally,
         ) {
             Milo(emotion = MiloEmotion.HAPPY)
             Text("Welcome to MindFlow", style = MaterialTheme.typography.headlineMedium)
             
             Button(onClick = { showContent = !showContent }) {
                 Text("Start Session")
             }
             AnimatedVisibility(showContent) {
                 val greeting = remember { Greeting().greet() }
                 Column(
                     modifier = Modifier.fillMaxWidth(),
                     horizontalAlignment = Alignment.CenterHorizontally,
                 ) {
                     Image(painterResource(Res.drawable.compose_multiplatform), null)
                     Text("Compose: $greeting")
                 }
             }
         }
     }
 }