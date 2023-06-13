package com.treetrack.api.data.profile

import com.treetrack.profile.User

data class UserResponse(
    val ID: Int,
    val department_id: Int,
    val email: String,
    var first_name: String,
    var last_name: String,
    var location: String?,
    var phone_number: String?,
    val type: Int,
    var image: String?
) {
    //like static in Java
    companion object {
        fun getUserFromUserResponse(userResponse: UserResponse): User {
            return User(
                ID = userResponse.ID,
                email = userResponse.email,
                first_name = userResponse.first_name,
                last_name = userResponse.last_name,
                location = userResponse.location,
                phone_number = userResponse.phone_number,
                image = userResponse.image
            )
        }
    }
}
