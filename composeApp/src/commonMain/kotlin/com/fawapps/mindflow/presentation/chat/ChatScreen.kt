package com.fawapps.mindflow.presentation.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fawapps.mindflow.components.Milo
import com.fawapps.mindflow.domain.models.ChatMessage
import com.fawapps.mindflow.domain.models.MessageRole
import com.fawapps.mindflow.theme.DeepBrown
import com.fawapps.mindflow.theme.Cream
import com.fawapps.mindflow.theme.TextDarkBrown
import com.fawapps.mindflow.theme.TextOnBrown
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

// TODO: Inject ViewModel properly using Koin. For now using basic viewModel() which might not work in KMP pure common without factory.
// We will assume Koin is set up in App.kt or platform entry point.
// Passing ViewModel as param for preview/testing ease.

@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    userId: String,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    // Auto-start session for MVP demo
    LaunchedEffect(Unit) {
        if (state.sessionId == null) {
            viewModel.startSession(userId)
        }
    }
    
    // Scroll to bottom on new message
    LaunchedEffect(state.messages.size) {
        if (state.messages.isNotEmpty()) {
            listState.animateScrollToItem(state.messages.size - 1)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding() // Handle safe area
    ) {
        // Header with Milo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Milo(
                emotion = state.currentMiloEmotion,
                state = state.currentMiloState
            )
        }

        // Chat List
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(state.messages) { message ->
                MessageBubble(message)
            }
            
            if (state.isAiTyping) {
                item {
                    Text(
                        "Milo is thinking...",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

        // Input Area
        ChatInput(
            onSend = { content -> viewModel.sendMessage(content, userId) },
            isLoading = state.isAiTyping
        )
    }
}

@Composable
fun MessageBubble(message: ChatMessage) {
    val isUser = message.role == MessageRole.USER
    val alignment = if (isUser) Alignment.End else Alignment.Start
    val bgColor = if (isUser) DeepBrown else Cream
    val textColor = if (isUser) TextOnBrown else TextDarkBrown
    val shape = if (isUser) {
        RoundedCornerShape(20.dp, 20.dp, 4.dp, 20.dp)
    } else {
        RoundedCornerShape(20.dp, 20.dp, 20.dp, 4.dp)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment
    ) {
        Surface(
            color = bgColor,
            shape = shape,
            shadowElevation = 2.dp,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Text(
                text = message.content,
                color = textColor,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
        
        // Timestamp (minimal)
        /*
        Text(
            text = "${message.timestamp}", // TODO: Format nicely
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp)
        )
        */
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatInput(
    onSend: (String) -> Unit,
    isLoading: Boolean
) {
    var text by remember { mutableStateOf("") }

    Surface(
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 8.dp, // Lift up input area
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .navigationBarsPadding(), // Handle bottom nav bar area
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("What's on your mind?") },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.outline
                ),
                maxLines = 4
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Button(
                onClick = {
                    if (text.isNotBlank()) {
                        onSend(text)
                        text = ""
                    }
                },
                enabled = text.isNotBlank() && !isLoading,
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(12.dp)
            ) {
                Text("Send")
            }
        }
    }
}
