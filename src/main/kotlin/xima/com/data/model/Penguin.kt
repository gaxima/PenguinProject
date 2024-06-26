package xima.com.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Penguin(
    var id: Int,
    var name: String,
    var description: String,
    var imageUrl: String
)
