package fr.esgi.taskflow.data.network.dto

import com.google.gson.annotations.SerializedName
import fr.esgi.taskflow.domain.model.User

/*
  {
    "id": 1,
    "name": "Leanne Graham",
    "user_name": "Bret",
    "email": "Sincere@april.biz",
  },
 */
data class UserDto(
    val id: Int?,
    val name: String?,
    @SerializedName("user_name") val userName: String?,
    val email: String?
)


fun UserDto.toDomainOrNull(): User? {
    val validId = id?.takeIf { it > 0 } ?: return null
    val validName = name?.takeIf { it.isNotBlank() } ?: return null

    // Tout est ok -> mapper la data
    return User(validId, validName, userName.orEmpty(), email.orEmpty())
}