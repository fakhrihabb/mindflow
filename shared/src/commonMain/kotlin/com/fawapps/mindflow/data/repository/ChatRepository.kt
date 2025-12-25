package com.fawapps.mindflow.data.repository

import com.fawapps.mindflow.domain.models.ChatMessage
import com.fawapps.mindflow.domain.models.ChatSession
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for chat operations
 */
interface ChatRepository {
    /**
     * Creates a new chat session for a user
     * @param userId The ID of the user
     * @return The created chat session
     */
    suspend fun createSession(userId: String): ChatSession
    
    /**
     * Observes messages in a chat session
     * @param sessionId The ID of the session
     * @return Flow of messages list
     */
    fun getMessages(sessionId: String): Flow<List<ChatMessage>>
    
    /**
     * Sends a user message to the chat
     * @param sessionId The ID of the session
     * @param content The message content
     * @param userId The ID of the user
     * @return The created message
     */
    suspend fun sendMessage(
        sessionId: String,
        content: String,
        userId: String
    ): ChatMessage
    
    /**
     * Saves an AI response to the chat
     * @param sessionId The ID of the session
     * @param content The AI response content
     * @param emotion The emotion state name
     * @param emotionState The emotion intensity/state name
     * @return The created message
     */
    suspend fun saveAiResponse(
        sessionId: String,
        content: String,
        emotion: String,
        emotionState: String
    ): ChatMessage
}
