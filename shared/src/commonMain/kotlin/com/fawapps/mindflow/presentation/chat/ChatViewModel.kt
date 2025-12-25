package com.fawapps.mindflow.presentation.chat

import com.fawapps.mindflow.data.repository.ChatRepository
import com.fawapps.mindflow.data.ai.AiClient
import com.fawapps.mindflow.domain.models.ChatMessage
import com.fawapps.mindflow.domain.models.MiloEmotion
import com.fawapps.mindflow.domain.models.MiloState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fawapps.mindflow.domain.models.MessageRole

data class ChatUiState(
    val messages: List<ChatMessage> = emptyList(),
    val isLoading: Boolean = false,
    val isAiTyping: Boolean = false,
    val currentMiloEmotion: MiloEmotion = MiloEmotion.HAPPY,
    val currentMiloState: MiloState = MiloState.IDLE,
    val sessionId: String? = null
)

class ChatViewModel(
    private val chatRepository: ChatRepository,
    private val aiClient: AiClient
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChatUiState())
    val uiState: StateFlow<ChatUiState> = _uiState.asStateFlow()

    fun startSession(userId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                // Check if we have an active session or create new
                // Simplified: Always create new for now or load based on ID passed in constructor (TODO)
                val session = chatRepository.createSession(userId)
                val sessionId = session.id
                
                _uiState.value = _uiState.value.copy(
                    sessionId = sessionId, 
                    isLoading = false
                )
                
                // Greeting
                // We could just add a fake message or let AI generate one
                // Let's add a fake system message from Milo
                val greeting = chatRepository.saveAiResponse(
                    sessionId, 
                    "Hi! I'm Milo. What's on your mind today?",
                    MiloEmotion.HAPPY.name,
                    MiloState.IDLE.name
                )
                
                // Start listening to messages
                observeMessages(sessionId)
                
            } catch (e: Exception) {
                // Handle error
                e.printStackTrace()
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
    
    private fun observeMessages(sessionId: String) {
        viewModelScope.launch {
            chatRepository.getMessages(sessionId).collect { messages ->
                // Update UI messages
                // Also update Milo's emotion based on the last AI message (if it has metadata)
                val lastAiMsg = messages.lastOrNull { it.role == MessageRole.AI }
                val newEmotion = lastAiMsg?.emotion ?: _uiState.value.currentMiloEmotion
                val newState = lastAiMsg?.emotionState ?: _uiState.value.currentMiloState
                
                _uiState.value = _uiState.value.copy(
                    messages = messages,
                    currentMiloEmotion = newEmotion,
                    currentMiloState = newState
                )
            }
        }
    }

    fun sendMessage(content: String, userId: String) {
        val sessionId = uiState.value.sessionId ?: return
        if (content.isBlank()) return

        viewModelScope.launch {
            // Optimistic update? No, let's wait for DB echo from Realtime or just insert.
            // Insert User Msg
            try {
                // Set AI typing
                _uiState.value = _uiState.value.copy(isAiTyping = true)
                
                chatRepository.sendMessage(sessionId, content, userId)
                
                // Trigger AI
                val history = uiState.value.messages
                val aiResponse = aiClient.generateResponse(history, content)
                
                // Save AI Msg
                chatRepository.saveAiResponse(
                    sessionId,
                    aiResponse.content,
                    aiResponse.metadata.emotion.name,
                    aiResponse.metadata.state.name
                )
                
                _uiState.value = _uiState.value.copy(isAiTyping = false)
                
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = _uiState.value.copy(isAiTyping = false)
            }
        }
    }
}
