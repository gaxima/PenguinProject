package xima.com.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Penguin(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String
)
