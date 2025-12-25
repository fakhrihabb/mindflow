package com.fawapps.mindflow.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fawapps.mindflow.domain.models.MiloEmotion
import com.fawapps.mindflow.domain.models.MiloState
import com.fawapps.mindflow.theme.DeepBrown
import com.fawapps.mindflow.theme.GoldenAmber
import com.fawapps.mindflow.theme.OliveGreen
import com.fawapps.mindflow.theme.TextOnBrown
import com.fawapps.mindflow.theme.WarmRedBrown


@Composable
fun Milo(
    emotion: MiloEmotion = MiloEmotion.HAPPY,
    state: MiloState = MiloState.IDLE,
    modifier: Modifier = Modifier
) {
    // Placeholder for Milo Assets
    // In Phase 2, we will replace these boxes with actual Images/Animations
    
    val color = when (emotion) {
        MiloEmotion.HAPPY -> OliveGreen
        MiloEmotion.SAD -> DeepBrown
        MiloEmotion.EXCITED -> GoldenAmber
        MiloEmotion.CALM -> OliveGreen
        MiloEmotion.THOUGHTFUL -> DeepBrown
        MiloEmotion.CONCERNED -> WarmRedBrown
        MiloEmotion.NEUTRAL -> DeepBrown
    }
    
    val size = if (state == MiloState.CELEBRATING) 64.dp else 48.dp
    
    Box(
        modifier = modifier
            .size(size)
            .background(color, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        val label = when (emotion) {
            MiloEmotion.HAPPY -> "^_^"
            MiloEmotion.SAD -> ";_;"
            MiloEmotion.EXCITED -> "!_!"
            MiloEmotion.CALM -> "~_~"
            MiloEmotion.THOUGHTFUL -> "?_?"
            MiloEmotion.CONCERNED -> "o_o"
            MiloEmotion.NEUTRAL -> "-_-"
        }
        Text(text = label, color = TextOnBrown)
    }
}
