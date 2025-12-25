package com.fawapps.mindflow.data.ai

import com.fawapps.mindflow.domain.models.ChatMessage
import com.fawapps.mindflow.domain.models.MiloEmotion
import com.fawapps.mindflow.domain.models.MiloState

/**
 * Response metadata from AI containing emotion and state information
 */
data class AiResponseMetadata(
    val emotion: MiloEmotion,
    val state: MiloState
)

/**
 * Complete AI response with content and metadata
 */
data class AiResponse(
    val content: String,
    val metadata: AiResponseMetadata
)

/**
 * Client interface for AI interactions
 */
interface AiClient {
    /**
     * Generates a response based on conversation history and new user message
     * @param history Previous messages in the conversation
     * @param userMessage New message from the user
     * @return AI response with content and metadata (emotion + state)
     */
    suspend fun generateResponse(
        history: List<ChatMessage>,
        userMessage: String
    ): AiResponse
}
