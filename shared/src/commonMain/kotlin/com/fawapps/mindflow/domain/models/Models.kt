package com.fawapps.mindflow.domain.models

import kotlinx.serialization.Serializable

/**
 * Role of the message sender
 */
@Serializable
enum class MessageRole {
    USER,
    AI,
    SYSTEM
}

/**
 * Milo's emotional states
 */
@Serializable
enum class MiloEmotion {
    HAPPY,
    SAD,
    EXCITED,
    CALM,
    THOUGHTFUL,
    CONCERNED,
    NEUTRAL
}

/**
 * Milo's animation/interaction states
 */
@Serializable
enum class MiloState {
    IDLE,
    THINKING,
    SPEAKING,
    LISTENING,
    CELEBRATING
}

/**
 * A single chat message
 */
@Serializable
data class ChatMessage(
    val id: String,
    val sessionId: String,
    val role: MessageRole,
    val content: String,
    val timestamp: Long,
    val userId: String? = null,
    val emotion: MiloEmotion? = null,
    val emotionState: MiloState? = null
)

/**
 * A chat session
 */
@Serializable
data class ChatSession(
    val id: String,
    val userId: String,
    val createdAt: Long,
    val updatedAt: Long
)
